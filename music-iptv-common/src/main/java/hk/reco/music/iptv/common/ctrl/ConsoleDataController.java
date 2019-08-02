package hk.reco.music.iptv.common.ctrl;

import hk.reco.music.iptv.common.domain.EasyUiBaseQuery;
import hk.reco.music.iptv.common.domain.EasyUiListResult;
import hk.reco.music.iptv.common.domain.IptvResVer;
import hk.reco.music.iptv.common.enums.IptvObjectEnum;
import hk.reco.music.iptv.common.exception.IptvBusinessException;
import hk.reco.music.iptv.common.service.IptvConsoleResService;
import hk.reco.music.iptv.common.service.IptvResVerService;
import hk.reco.music.iptv.common.utils.IptvFileUtils;
import hk.reco.music.iptv.common.utils.JsonResult;
import hk.reco.music.iptv.common.utils.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 〈用于后台管理的公有方法〉
 *
 * @author comelk
 * @create 2019/2/22
 */
@RequestMapping("console")
@Controller
public class ConsoleDataController {

    @Value("${iptv.file.ssh.ip}")
    private String ip;
    @Value("${iptv.file.ssh.user}")
    private String user;
    @Value("${iptv.file.ssh.pwd}")
    private String pwd;
    @Autowired
    private IptvResVerService iptvResVerService;
    @Autowired
    private IptvConsoleResService iptvConsoleResService;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    // ==========================以下是公有的方法====================================

    /**
     * 创建父资源和子资源之间的link连接 prid 父资源 crids 子集对应的id以','隔开 done
     *
     * @return
     */
    @RequestMapping("addChildLink")
    @ResponseBody
    public Object addChildLink(Long prid, String crids) {
        if (StringUtils.isNotBlank(crids)) {
            iptvConsoleResService.consoleAddChildLink(prid, crids);
        }
        return new JsonResult();
    }

    /**
     * 编辑link属性,如sort值等 done
     *
     * @param link
     * @return
     */
    @RequestMapping("modifyChildLink")
    @ResponseBody
    public Object modifyChildLink(Long link_vid, Long link_prid, Float sort) {
        long new_link_vid = this.iptvConsoleResService.consoleModifyChildLink(link_vid, link_prid, sort);
        return new JsonResult(true, new_link_vid);
    }

    /**
     * 禁用link,使其脱离与父的联系 done
     *
     * @param link_vid
     * @return
     */
    @RequestMapping("deleteChildLink")
    @ResponseBody
    public Object deleteChildLink(Long link_vid) {
        iptvConsoleResService.consoleDeleteChildLink(link_vid);
        return new JsonResult();
    }

    /**
     * 批量将子link提交到生产环境(应用场景为cate下的子资源提交生产版本) done
     *
     * @param vids link的vid,多个以逗号隔开
     * @return
     */
    @RequestMapping("submitChildLink")
    @ResponseBody
    public Object submitChildLink(String vids) {
        int i = 0;
        if (StringUtils.isNotBlank(vids)) {
            String[] vidArray = vids.split(",");
            List<Long> link_vids = new ArrayList<Long>();
            for (String vid : vidArray) {
                link_vids.add(Long.parseLong(vid));
            }
            i = this.iptvConsoleResService.consoleCommitChildLinkToProd(link_vids);
        }
        return new JsonResult(true, "成功将&nbsp;<span style='color:red'>" + i + "</span>&nbsp;条数据提交到生产环境", null);
    }

    /**
     * 将指定资源下的所有资源(包括看不见的)发布到生产环境 prid
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("submitAllChild")
    @ResponseBody
    public Object submitAllChild(Long prid) {
        try {
            this.iptvConsoleResService.consoleSubmitAllChildToProd(prid);
            return new JsonResult();
        } catch (Exception e) {
            return new JsonResult(e.getMessage());
        }
    }

    /**
     * 提交资源的test到生产环境(应用场景为资源管理时,对song,mv,singer,)  done
     *
     * @param vids res的vid,多个以逗号隔开
     * @return
     */
    @RequestMapping("submitVer")
    @ResponseBody
    public Object submitVer(String vids) {
        int i = 0;
        if (StringUtils.isNotBlank(vids)) {
            String[] vid_arr = vids.split(",");
            for (String vid : vid_arr) {
                if (this.iptvConsoleResService.consoleCommitResVerToProd(Long.parseLong(vid))==1) {
                }
                i++;
            }
        }
        return new JsonResult(true, "成功将&nbsp;<span style='color:red'>" + i + "</span>&nbsp;条数据提交到生产环境", null);
    }

    /**
     * 将当前资源的本身及其link资源全部提交到生产
     *
     * @return
     */
    @RequestMapping("submitParentAndLinkVer")
    @ResponseBody
    public Object submitParentAndLinkVer(Long rid,Long vid) {

        try {
               this.iptvConsoleResService.consoleCommitResVerToProd(vid);
               this.iptvConsoleResService.consoleSubmitAllChildToProd(rid);
            return new JsonResult();
        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult(e.getMessage());
        }
    }

    /**
     * 文件上传  done
     *
     * @return
     */
    @RequestMapping("fileUpLoad")
    @ResponseBody
    public Object fileUpLoad(MultipartFile iptvBigPoster, HttpServletRequest req) throws IptvBusinessException {
        String path = "";
        if (iptvBigPoster != null && iptvBigPoster.getSize() > 0) {
            // IptvFileUtils.saveFile(iptvBigPoster);
            path = IptvFileUtils.saveFile(iptvBigPoster, req, ip, user, pwd);
        } else {
            return new JsonResult("海报为空");
        }
        Map<String, String> map = new HashMap<>();
        map.put("path", path);
        map.put("bposter", IptvFileUtils.http(path));
        return new JsonResult(true, map);
    }

    /**
     * 更新资源海报图 done
     *
     * @return
     */
    @RequestMapping("updateResPoster")
    @ResponseBody
    public Object updateResPoster(Long vid, MultipartFile iptvBigPoster, HttpServletRequest req) throws IptvBusinessException {
        IptvResVer ver = new IptvResVer();
        if (iptvBigPoster != null && iptvBigPoster.getSize() > 0) {
            // IptvFileUtils.saveFile(iptvBigPoster);
            String path = IptvFileUtils.saveFile(iptvBigPoster, req, ip, user, pwd);
            ver.setBposter(path);
            ver.setSposter(path);
        } else {
            return new JsonResult("海报为空");
        }
        ver.setVid(vid);
        iptvConsoleResService.consoleUpdateResVer(ver);
        return new JsonResult();
    }

    /**
     * 改变指定资源的全局禁用状态 done
     * 全局禁用需要立刻生效,所以要清除掉相关的缓存
     * 如singer禁用,则他所有的song会禁用,推荐位也会禁用
     * 如song禁用,则包含他的diss,推荐位,专栏也会禁用
     * TODO: 禁用后,[不规则推荐位会出现空缺,因此要检查非规则的位置]
     *
     * @return
     */
    @RequestMapping("globalOnline")
    @ResponseBody
    public Object globalOnline(String rids) {
        int i = 0;
        if (StringUtils.isNotBlank(rids)) {
            String[] rid = rids.split(",");
            for (String id : rid) {
                iptvConsoleResService.consoleResChangeGlobalStatus(Long.parseLong(id));
                i++;
            }
        }
        return new JsonResult(true, "成功改变&nbsp;<span style='color:red'>" + i + "</span>&nbsp;条数据资源状态", null);
    }

    /**
     * 根据对象类型分页查询,目前支持'歌曲管理song'
     * EasyUiBaseQuery 列表查询对象
     *
     * @return
     */
    @RequestMapping("listData")
    @ResponseBody
    public Object listData(EasyUiBaseQuery query, HttpServletRequest req) {
        EasyUiListResult<IptvResVer> result = new EasyUiListResult<IptvResVer>();
        int i = iptvConsoleResService.consoleResListByTypeCount(query);
        result.setTotal(i);
        if (i > 0) {
            List<IptvResVer> iptvResVers = iptvConsoleResService.consoleResListByType(query, (query.getPage() - 1) * query.getRows(), query.getRows());
            result.setRows(iptvResVers);
        }
        return result;
    }

    /**
     * 查询拥有子资源的资源列表
     * EasyUiBaseQuery 列表查询对象
     *
     * @return
     */
    @RequestMapping("listDataForParentResource")
    @ResponseBody
    public Object listDataForParentResource(EasyUiBaseQuery query, HttpServletRequest req) {
        EasyUiListResult<IptvResVer> result = new EasyUiListResult<IptvResVer>();
        int i = iptvConsoleResService.consoleResListByTypeCount(query);
        result.setTotal(i);
        if (i > 0) {
            List<IptvResVer> iptvResVers = iptvConsoleResService.consoleResListByType(query, (query.getPage() - 1) * query.getRows(), query.getRows());
            for (IptvResVer iptvResVer : iptvResVers) {
                //借用此方法判定查询资源及其子资源是否已提交
                iptvConsoleResService.recurisiveLayout(iptvResVer,1,2,false);
            }
            result.setRows(iptvResVers);
        }
        return result;
    }




    // ==========================以下是program相关的方法====================================
    @RequestMapping("programlist")
    public String list(String type, Model model) {
        model.addAttribute("type", type);
        return "program/list";
    }

    /**
     * 编辑并保存修改后的ver,所有的[资源管理]下的对象类型,在编辑后进行保存时都使用了这个方法
     *
     * @param ver
     * @param iptvBigPoster
     * @param req
     * @return
     * @throws IptvBusinessException
     */
    @RequestMapping("addProgram")
    @ResponseBody
    public Object addProgram(IptvResVer ver, MultipartFile iptvBigPoster, MultipartFile iptvSmallPoster, HttpServletRequest req) throws IptvBusinessException {
        if (iptvSmallPoster != null && iptvSmallPoster.getSize() > 0) {
            String path = IptvFileUtils.saveFile(iptvSmallPoster, req, ip, user, pwd);
            ver.setSposter(path);
        }
        String path = IptvFileUtils.saveFile(iptvBigPoster, req, ip, user, pwd);
        ver.setBposter(path);
        if (StringUtils.isBlank(ver.getSposter())) {
            ver.setSposter(path);
        }
        ver.setVersion_disable(false);
        ver.setNewCreate(true);
        this.iptvConsoleResService.consoleCreateResVer(ver);
        return new JsonResult();
    }


    /**
     * 编辑并保存修改后的ver,所有的[资源管理]下的对象类型,在编辑后进行保存时都使用了这个方法
     *
     * @param ver
     * @param iptvBigPoster
     * @param req
     * @return
     * @throws IptvBusinessException
     */
    @RequestMapping("editorProgram")
    @ResponseBody
    public Object editorProgram(IptvResVer ver, MultipartFile iptvBigPoster, MultipartFile iptvSmallPoster, HttpServletRequest req) throws IptvBusinessException {
        if (iptvSmallPoster != null && iptvSmallPoster.getSize() > 0) {
            String path = IptvFileUtils.saveFile(iptvSmallPoster, req, ip, user, pwd);
            ver.setSposter(path);
        } else if (StringUtils.isNotBlank(ver.getBposter()) && ver.getBposter().startsWith("http")) {
            ver.setBposter(IptvFileUtils.httpToRelative(ver.getBposter()));
        }

        if (iptvBigPoster != null && iptvBigPoster.getSize() > 0) {
            String path = IptvFileUtils.saveFile(iptvBigPoster, req, ip, user, pwd);
            ver.setBposter(path);
            if (StringUtils.isBlank(ver.getSposter())) {
                ver.setSposter(path);
            }
        } else if (StringUtils.isNotBlank(ver.getBposter()) && ver.getBposter().startsWith("http")) {
            ver.setBposter(IptvFileUtils.httpToRelative(ver.getBposter()));
        }
        ver.setVersion_disable(false);
        ver.setNewCreate(true);
        this.iptvConsoleResService.consoleModifyResVer(ver.getVid(), ver);
        return new JsonResult();
    }

    // =============================以下是专辑相关的逻辑=========================================
    @RequestMapping("disslist")
    public String disslist(String type, Model model) {
        model.addAttribute("type", type);
        return "program/disslist";
    }

    // =============================以下是歌手相关的逻辑=========================================
    @RequestMapping("singerlist")
    public String singerlist(String type, Model model) {
        model.addAttribute("type", type);
        return "program/singerlist";
    }

    // =============================以下是栏目相关的逻辑=========================================

    /**
     * 跳转到栏目列表页
     *
     * @param type
     * @return
     */
    @RequestMapping("columnlist")
    public String column(String type, Model model) {
        model.addAttribute("type", type);
        return "column/list";
    }

    @RequestMapping("columnlistata")
    @ResponseBody
    public Object childColumnData(String type) {
        List<IptvResVer> iptvResVers = iptvConsoleResService.consoleResListByTypeAndChildType(IptvObjectEnum.column, IptvObjectEnum.valueOf(type));
        EasyUiListResult<IptvResVer> result = new EasyUiListResult<IptvResVer>();
        result.setTotal(iptvResVers.size());
        result.setRows(iptvResVers);
        return result;
    }

    /**
     * 栏目资源列表页
     *
     * @param sourceType
     * @return
     */
    @RequestMapping("column/source")
    public String column(String sourceType) {
        // 获取资源列表
        //String type = sourceType.split("_")[0];
        return "column/singnerList";
    }

    // 布局管理
    @RequestMapping("layout")
    public String layout(String type, Integer layoutId, Boolean layout, HttpServletRequest request, Model model) {
        model.addAttribute("type", type);
        model.addAttribute("layoutId", layoutId);
        if (!layout) {
            // 不规则布局
            return "layout/irregular";
        } else {
            // 规则布局
            return "layout/regular";
        }
    }

    /**
     * 新增布局
     *
     * @param link
     * @param prid
     * @return
     */
    @RequestMapping("addLayOut")
    @ResponseBody
    public Object addlayout(IptvResVer link, Long prid) {
        if (prid != null) {
            iptvConsoleResService.consoleCreateResVer(link, prid);
        } else {
            iptvConsoleResService.consoleCreateResVer(link);
        }
        return new JsonResult(true, link);
    }

    /**
     * 修改布局
     *
     * @param link
     * @return
     */
    @RequestMapping("editLayOut")
    @ResponseBody
    public Object editIptvRes(IptvResVer link,IptvObjectEnum type) {
        if(IptvObjectEnum.column.equals(type)){
            link.setVersion_disable(false);
            iptvResVerService.appendResVer(link,false);
            IptvResVer ver = new IptvResVer();
            ver.setSort(link.getSort());
            ver.setLayout_show_child_title(link.getLayout_show_child_title());
            ver.setLayout_show_title(link.getLayout_show_title());
            ver.setLayout_line(link.getLayout_line());
            iptvConsoleResService.consoleModifyResVer(link.getLink_vid(),ver);
        }else{
            iptvConsoleResService.consoleUpdateLayoutInfo(link);
        }
        return new JsonResult(true, link);
    }

    /**
     * 获取某个pid下的所有资源
     * 1-此方法没有分页,拿test的list到页面进行逻辑分页,毕竟一个资源下的子资源不会太多
     *
     * @param prid
     * @return
     */
    @RequestMapping("getResList")
    @ResponseBody
    public Object getResList(Long prid) {
        /*
         * 比较算法分析
         * 1-当以前有的资源被global_disable时,两个环境都不会查到
         * 2-当test有,生产无,这是运营平台新增link,此时test_vid与prod_vid不同,prod_vid为0
         * 3-当test有,生产有,这时候要比较版本是不是相同,判断是否有修改
         * 3-当test无,生产有,这是test进行了删除,此时test_vid和prod_vid一定不同
         */
        List<IptvResVer> vers = this.iptvResVerService.findListExcludeGlobalDisable(null, null, prid, true);//找测试环境的
        EasyUiListResult<IptvResVer> result = new EasyUiListResult<IptvResVer>();
        result.setTotal(vers.size());
        result.setRows(vers);
        // add by zhangsl at 2019-06-03 begin
        result.setStatus(true);
        List<IptvResVer> prod_vers = this.iptvResVerService.findListExcludeGlobalDisable(null, null, prid, false);//找生产环境的
        if (prod_vers.size() != vers.size()) {
            result.setStatus(false);
        } else {
            for (IptvResVer ver : vers) {
                if (ver.getLink_test_vid().longValue() != ver.getLink_prod_vid().longValue()) {
                    result.setStatus(false);
                    return result;
                }
            }
            for (IptvResVer ver : prod_vers) {
                if (ver.getLink_test_vid().longValue() != ver.getLink_prod_vid().longValue()) {
                    result.setStatus(false);
                    return result;
                }
            }
        }
        // add by zhangsl at 2019-06-03 end
        return result;
    }

    /**
     * 关联布局和已存在的某个栏目
     *
     * @param prid 布局rid
     * @param crid 栏目rid
     */
    @RequestMapping("contactColumn")
    @ResponseBody
    public Object contactColumn(IptvResVer link, Long crid, Long prid) {
        IptvResVer cver = this.iptvConsoleResService.consoleResByRid(crid);
        IptvResVer linknew = IptvResVer.createLink(prid, crid, -1);
        linknew.setLayout_regular(cver.getLayout_regular());
        linknew.setLayout_show_child_title(link.getLayout_show_child_title());
        linknew.setLayout_show_title(link.getLayout_show_title());
        linknew.setLayout_line(link.getLayout_line());
        linknew.setMedia_ok(true);
        linknew.setSort(link.getSort());
        this.iptvConsoleResService.consoleCreateLinkResVer(linknew, false);
        return new JsonResult(true, linknew);
    }

    /**
     * 栏目下新增一个子资源
     *
     * @param prid     栏目id
     * @param crid     子资源id
     * @param position 拖动到的位置
     * @return
     */
    @RequestMapping("addColumnListItem")
    @ResponseBody
    public Object addColumnListItem(Long prid, Long crid, Integer position) {
        Long link_vid = iptvConsoleResService.consoleAddListItem(prid, crid, position);
        return new JsonResult(true, link_vid);
    }

    /**
     * 修改栏目详情信息
     *
     * @param prid
     * @return
     */
    @RequestMapping("modifyColumnListItem")
    @ResponseBody
    public Object modifyColumnListItem(Long prid, Long link_vid, Boolean layout_big, Integer position, Float sort, HttpServletRequest request,
                                       MultipartFile iptvBigPoster) throws IptvBusinessException {
        String path = "";
        if (iptvBigPoster != null && iptvBigPoster.getSize() > 0) {
            // IptvFileUtils.saveFile(iptvBigPoster);
            path = IptvFileUtils.saveFile(iptvBigPoster, request, ip, user, pwd);
        }
        Long re = iptvConsoleResService.consoleModifyListItem(prid, link_vid, sort, layout_big, position, path);
        return new JsonResult(true, re);
    }

    /**
     * 保存不规则布局
     *
     * @param layout
     * @return
     */
    @RequestMapping("saveIrregularLaout")
    @ResponseBody
    public Object saveIrregularLaout(String layout, Long prid) {
        List<IptvResVer> iptvResVers = JsonUtil.toList(layout.replaceAll("\"", "\\\""), IptvResVer.class);
        iptvConsoleResService.consoleSaveIrregularLaout(prid, iptvResVers);
        return new JsonResult();
    }

    /**
     * 提交布局到生产环境
     *
     * @param prid
     * @return
     */
    @RequestMapping("submitLayOut")
    @ResponseBody
    public Object submitLayOut(Long prid, Long rid, Long link_vid) {
        //iptvConsoleResService.consoleSubmitLayOut(prid, rid, link_vid, "");
        iptvConsoleResService.consoleSubmitLayOut(prid, rid);
        return new JsonResult();
    }

    /**
     * 提交布局到生产环境
     *
     * @param prid
     * @return
     */
    @RequestMapping("deleteLayOut")
    @ResponseBody
    public Object deleteLayOut(Long link_vid) {
        iptvConsoleResService.consoleDeleteLayout(link_vid);
        return new JsonResult();
    }

    // 以下是分类相关逻辑

    /**
     * 分类管理界面,包括(歌手分类 ,歌曲分类,MV分类,歌单分类,榜单分类)
     *
     * @param type
     * @return
     */
    @RequestMapping("catelist")
    public Object catelist(String type, Model model) {
        model.addAttribute("type", type);
        return "cate/list";
    }

    /**
     * 分类列表数据
     *
     * @param type
     * @return
     */
    @RequestMapping("catelistata")
    @ResponseBody
    public Object catelistata(String type, Model model) {
        List<IptvResVer> iptvResVers = iptvConsoleResService.consoleResListByTypeAndChildType(IptvObjectEnum.cate, IptvObjectEnum.valueOf(type));
        EasyUiListResult<IptvResVer> result = new EasyUiListResult<IptvResVer>();
        result.setTotal(iptvResVers.size());
        result.setRows(iptvResVers);
        return result;
    }

    /**
     * 更新跟类的排序
     *
     * @param catesStr
     * @return
     */
    @RequestMapping("updateCateSort")
    @ResponseBody
    public Object updateCateSort(String catesStr) {
        List<IptvResVer> iptvResVers = JsonUtil.toList(catesStr.replaceAll("\"", "\\\""), IptvResVer.class);
        for (IptvResVer iptvResVer : iptvResVers) {
            iptvConsoleResService.consoleUpdateResVer(iptvResVer);
        }
        return new JsonResult();
    }


    /**
     * 更新column的海报
     *
     * @return
     */
    @RequestMapping("updateColumnPoster")
    @ResponseBody
    public Object updateColumnPoster(IptvResVer resVer, MultipartFile iptvBigPoster, MultipartFile iptvSmallPoster, HttpServletRequest request) throws IptvBusinessException {
        if (iptvBigPoster != null && iptvBigPoster.getSize() > 0) {
            // IptvFileUtils.saveFile(iptvBigPoster);
            String path = IptvFileUtils.saveFile(iptvBigPoster, request, ip, user, pwd);
            resVer.setBposter(path);
        }
        if (iptvSmallPoster != null && iptvSmallPoster.getSize() > 0) {
            // IptvFileUtils.saveFile(iptvBigPoster);
            String path = IptvFileUtils.saveFile(iptvSmallPoster, request, ip, user, pwd);
            resVer.setSposter(path);
        }
        String bposter = resVer.getBposter();
        String sposter = resVer.getBposter();
        if (bposter.startsWith("http")) {
            resVer.setBposter(IptvFileUtils.httpToRelative(bposter));
        }
        if (sposter.startsWith("http")) {
            resVer.setSposter(IptvFileUtils.httpToRelative(sposter));
        }
        iptvConsoleResService.consoleUpdateResVer(resVer);
        return new JsonResult();
    }


    /**
     * 修改专题下面的link属性（此时并未保存）
     *
     * @param rid           资源id
     * @param iptvBigPoster 大海报
     * @param xstart        横轴起始位置
     * @param ystart        纵轴起始位置
     * @param xsize         横轴宽度
     * @param ysize         纵轴宽度
     * @param request
     * @throws IptvBusinessExceptionf
     */
    @RequestMapping("updateThemeLinkinfo")
    @ResponseBody
    public Object updateSubLinkinfo(Integer rid, String xstart, String ystart, String xsize, String ysize, MultipartFile iptvBigPoster,
                                    Integer sort,HttpServletRequest request) throws IptvBusinessException {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("rid", rid);
        resultMap.put("xstart", xstart);
        resultMap.put("ystart", ystart);
        resultMap.put("xsize", xsize);
        resultMap.put("ysize", ysize);
        resultMap.put("sort", sort);
        if (iptvBigPoster != null && iptvBigPoster.getSize() > 0) {
            String path = IptvFileUtils.saveFile(iptvBigPoster, request, ip, user, pwd);
            resultMap.put("bposter", IptvFileUtils.http(path));
        }
        return new JsonResult(true, resultMap);
    }

    /**
     * 专题管理页
     *
     * @return
     */
    @RequestMapping("themeList")
    public String specialsubject(String type, Model model) {
        model.addAttribute("type", type);
        return "theme/list";
    }

    /**
     * 获取非规则布局的资源列表
     *
     * @return
     */
    @RequestMapping("getIrregularResList")
    @ResponseBody
    public Object getIrregularResList(Long prid) {
        List<IptvResVer> iptvResVers = iptvConsoleResService.consoleIrregularResList(prid);
        EasyUiListResult<IptvResVer> result = new EasyUiListResult<IptvResVer>();
        result.setTotal(iptvResVers.size());
        result.setRows(iptvResVers);
        return result;
    }


    /**
     * 修改资源免费状态
     * @return
     */
    @RequestMapping("editorResfreeStatus")
    @ResponseBody
    public Object editorResfreeStatus(Long rid,Integer free) {
        try {
			iptvConsoleResService.consoleReschangefreeStatus(rid,1-free);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(false, "操作失败:"+e.getMessage(), null);
		}
        return new JsonResult(true, "操作成功!!!!", null);
    }

    /**
     * 刷新缓存页
     * @return
     */
    @RequestMapping("freshcachepage")
    public String freshcachePage(Long rid,Integer free) {
       return "cache/freshcachepage";
    }

    /**
     * 刷新缓存方法
     * @return
     */
    @RequestMapping("freshcache")
    @ResponseBody
    public Object freshcache() {
        try {
            Set<String> keys = redisTemplate.keys("*");
            redisTemplate.delete(keys);
            return new JsonResult();
        }catch (Exception e){
            return new JsonResult(e.getMessage());
        }
    }

}
