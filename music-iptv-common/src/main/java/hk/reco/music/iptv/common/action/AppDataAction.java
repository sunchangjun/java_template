package hk.reco.music.iptv.common.action;

import hk.reco.music.iptv.common.ctrl.stb.RestResponse;
import hk.reco.music.iptv.common.dao.IptvResVerDao;
import hk.reco.music.iptv.common.domain.*;
import hk.reco.music.iptv.common.enums.IptvObjectEnum;
import hk.reco.music.iptv.common.enums.IptvPlatform;
import hk.reco.music.iptv.common.layout.CatesCoverter;
import hk.reco.music.iptv.common.layout.Layout;
import hk.reco.music.iptv.common.layout.LayoutFactory;
import hk.reco.music.iptv.common.service.stb.IptvStbCommonService;
import hk.reco.music.iptv.common.utils.*;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 〈用于前端页面的跳转〉
 *
 * @author comelk
 * @create 2019/4/12
 */
public abstract class AppDataAction  {
    /**
     * 栏目之间的间隙
     */
    final private static Integer column_height = 10;
    /**
     * 1行规则布局
     */
    final  private static Integer show_title_height = 45;

	@Autowired
	protected IptvStbCommonService stbService;
    @Autowired
    protected IptvResVerDao iptvResVerDao;


    @RequestMapping("show/index.htm")
    public String show(HttpServletRequest request, HttpServletResponse response,Model model,Boolean test,String extra,ExtraInfo info)
    		throws Exception {
        request.setCharacterEncoding("UTF-8");
        //处理用户信息
        Object o = request.getSession().getAttribute(Constant.USER_IN_SESSION);
        WebSessionUser user;
        if(o==null){
            user = dealWithUserInfo(request);
            request.getSession().setAttribute(Constant.USER_IN_SESSION,user);
        }else{
            user = (WebSessionUser)o;
        }
        //获取系统环境
        boolean sysenv = getSystemev(test,request);
        //处理额外参数（聚焦定位）
        if(StringUtils.isNotBlank(extra)){
             info = JsonUtil.toObjectByJson(extra,ExtraInfo.class);
        }
        model.addAttribute("extroInfo",info);
        System.out.println("sysenv==>"+sysenv);
        //查询布局参数
        List<IptvResVer> vers = this.stbService.get_layouts_impl(user.getPlatform(), NetworkUtils.getIpAddress(request), null,user.getUserId(), sysenv);
        if(vers!=null&&vers.size()>0){
            IptvResVer ver = vers.get(info.getLayout_index());
            model.addAttribute("curenntLayout",ver);
            //处理聚焦,及栏目信息
            dealWithFocusAndColumnData(request,ver,sysenv,info,model,user.getPlatform(),user.getUserId());
        }
        model.addAttribute("layouts",vers);
        return "show/index";
    }
    
    /**
     * 处理聚焦问题和栏目媒资问题
     * @param request
     * @throws Exception 
     */
    private  void   dealWithFocusAndColumnData(HttpServletRequest request,IptvResVer ver,boolean sysenv,
                      ExtraInfo info,Model model,IptvPlatform platform, String userId) throws Exception{
          Long rid = ver.getRid();
          if(ver.getChild_type().equals(IptvObjectEnum.layout_top)){
               //获取所有榜单分类
              List<IptvResVer> vers = this.stbService.get_top_category_impl(platform, NetworkUtils.getIpAddress(request), null, userId, 0l, rid, sysenv);
              List<IptvResVer> childVers = new ArrayList<>();
              for (IptvResVer iptvResVer : vers) {
                  childVers.addAll(iptvResVer.getList());
              }
              List<IptvResVerForPage> cates = CatesCoverter.coverCates(childVers);
              model.addAttribute("cates",cates);
              model.addAttribute("defaultLayout",false);
          }else if(ver.getChild_type().equals(IptvObjectEnum.layout_diss)){
               //获取所有歌单分类
        	  List<IptvResVer> vers = stbService.get_diss_category_impl(platform, NetworkUtils.getIpAddress(request), null, userId, 0l, rid, IptvMsicUtils.parseTest(sysenv));
              List<IptvResVerForPage> cates = CatesCoverter.coverCates(vers);
              model.addAttribute("cates",cates);
              model.addAttribute("defaultLayout",false);
          }else if(ver.getChild_type().equals(IptvObjectEnum.layout_singer)){
               //获取所有歌手分类
        	  List<IptvResVer> vers = stbService.get_singer_category_impl(platform, NetworkUtils.getIpAddress(request), null, userId, 0l, rid, IptvMsicUtils.parseTest(sysenv));
              List<IptvResVerForPage> cates = CatesCoverter.coverCates(vers);
              model.addAttribute("cates",cates);
              model.addAttribute("defaultLayout",false);
          } else{
            //自定义布局
        	List<IptvResVer> vers = stbService.get_layout_by_rid_impl(platform, NetworkUtils.getIpAddress(request), null, userId, 0l, rid, IptvMsicUtils.parseTest(sysenv));
            List<IptvResVerForPage> pagevers = caculateColumnData(vers);
            model.addAttribute("columns",pagevers);
            model.addAttribute("defaultLayout",true);
           }
    };



    /**
     *  计算栏目信息
     */
    public List<IptvResVerForPage>  caculateColumnData(List<IptvResVer> vers){
        int top = 0;
        List<IptvResVerForPage> re = new ArrayList<>();
        for (IptvResVer ver : vers) {
            IptvResVerForPage page = new IptvResVerForPage(ver);
            page.setTop(top);
             if(page.getLayout_regular()){
                 Layout layout = new LayoutFactory(page).getLayout();
                 Integer integer = layout.doSetLayoutInfo();
                 page.setColumn_height(integer+column_height+50);
                 top = top + integer +column_height;
                 re.add(page);
             }else{
                 int tmpheight = 0;
                 IptvResVer tmp= null;
                 if(page.getList()!=null){
                     for (IptvResVer iptvResVer : page.getList()) {
                         String start =  iptvResVer.getLayout_start();
                         Integer height = Integer.valueOf(start.split(" ")[1]);
                         if(height>=tmpheight){
                             tmpheight = height;
                             tmp = iptvResVer;
                         }
                     }
                     if(tmp!=null){
                         String end = tmp.getLayout_end();
                         Integer height = Integer.valueOf(end.split(" ")[1]);
                         tmpheight = tmpheight+height;
                         top = top +tmpheight + column_height;
                         page.setColumn_height(tmpheight);
                     }
                     re.add(page);
                 }
             }
             if(page.getLayout_show_title()){
                 top = top +show_title_height;
             }
         }
         return re;
    }

    /**
     * 获取当前的系统环境
     * @return
     */
    private boolean getSystemev(Boolean env,HttpServletRequest request){
        if(env != null){
            request.getSession().setAttribute(Constant.SYSTEM_ENV,env);
            return env;
        }else{
            Object re = request.getSession().getAttribute(Constant.SYSTEM_ENV);
            if(re==null){
                request.getSession().setAttribute(Constant.SYSTEM_ENV,true);
                return true;
            }
            return (boolean)re;
        }
    }

    /**
     * web 端搜索
     * @param request
     * @return
     */
    @RequestMapping("search")
    @ResponseBody
    public RestResponse search(Boolean test,String pinyin,HttpServletRequest request,IptvPlatform platform,
                          String userId,Long prid,Long rid){
        RestResponse result = new RestResponse();
        result.setCode(0);
        try {
            IptvPageResult mv = this.stbService.get_search_list_impl(platform, NetworkUtils.getIpAddress(request), null, userId, prid, rid, pinyin.toLowerCase(), "mv", 0, 10000, test);
            IptvPageResult song = this.stbService.get_search_list_impl(platform, NetworkUtils.getIpAddress(request), null, userId, prid, rid, pinyin.toLowerCase(), "song", 0, 10000, test);
            result.setTotal(mv.getTotal()+song.getTotal());
            List<IptvResVer> list= new ArrayList<IptvResVer>();
            List<IptvResVer> mvList = mv.getVers();
            List<IptvResVer> songList = song.getVers();
            list.addAll(mvList);
            list.addAll(songList);
            result.setTotal(mvList.size()+songList.size());
            result.setData(list);
        }catch (Exception e){
            result.setCode(1);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    /**
     * web 端热门（推荐）搜索
     * @param request
     * @return
     */
    @RequestMapping("hotSearch")
    @ResponseBody
    public RestResponse hotSearch(Boolean test,HttpServletRequest request,IptvPlatform platform, 
                             String userId,Long prid,Long rid){
        RestResponse result = new RestResponse();
        result.setCode(0);
        try {
            List<IptvResVer> mv = this.stbService.get_search_reco_list_impl(platform, NetworkUtils.getIpAddress(request), null, userId, prid, rid, "mv", 5, test);
            List<IptvResVer> song = this.stbService.get_search_reco_list_impl(platform, NetworkUtils.getIpAddress(request), null, userId, prid, rid,"song", 5, test);
            List<IptvResVer> list= new ArrayList<>();
            list.addAll(mv);
            list.addAll(song);
            result.setTotal(mv.size()+song.size());
            result.setData(list);
        }catch (Exception e){
            result.setCode(1);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    /**
     * 获取免费媒资
     * @param request
     * @return
     */
    @RequestMapping("get_free_media")
    @ResponseBody
    public RestResponse get_free_media(Boolean test,HttpServletRequest request,IptvPlatform platform, 
                             String userId,Long prid,Long rid){
        RestResponse result = new RestResponse();
        result.setCode(0);
        try {
           // String mac, String userId, Long prid, Long rid, IptvObjectEnum type, int offset, int size, boolean test
            IptvPage page = IptvMsicUtils.parsePage(0, 10000);
            IptvPageResult mv =  this.stbService.get_free_impl(platform, NetworkUtils.getIpAddress(request), null, userId, prid, rid, IptvObjectEnum.mv,
                    page.getOffset(),page.getSize(), test);
            IptvPageResult song =  this.stbService.get_free_impl(platform, NetworkUtils.getIpAddress(request), null, userId, prid, rid,IptvObjectEnum.song,
                    page.getOffset(), page.getSize(), test);
            List<IptvResVer> list= new ArrayList<>();
            List<IptvResVer> mvList = mv.getVers();
            List<IptvResVer> songList = song.getVers();
            list.addAll(mvList);
            list.addAll(songList);
            result.setTotal(mvList.size()+songList.size());
            result.setData(list);
        }catch (Exception e){
            result.setCode(1);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    /**
     * 获取用户收藏
     * @param request
     * @return
     */
    @RequestMapping("user_collect_list")
    @ResponseBody
    public RestResponse user_collect_list(Boolean test,HttpServletRequest request,IptvPlatform platform, 
                             String userId,Long prid,Long rid){//zsltodo
        RestResponse result = new RestResponse();
        result.setCode(0);
        try {
            List<IptvResVer> vers = this.stbService.get_user_collect_list_impl(platform, NetworkUtils.getIpAddress(request), null, userId, prid, rid, test);
            for (IptvResVer ver : vers) {
                int count = iptvResVerDao.findResListPageableCount(IptvObjectEnum.diss, null, ver.getRid(), null, test);
                ver.setChild_num(count);
            }
            result.setData(vers);
        }catch (Exception e){
            result.setCode(1);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    /**
     * 获取指定分类下的歌单
     * @param request
     * @return
     */
    @RequestMapping("get_cate_content_list")
    @ResponseBody
    public RestResponse get_cate_content_list(Boolean test,HttpServletRequest request,Integer pageIndex
                                          ,Integer pageSize,IptvPlatform platform, String userId,Long prid,Long rid){
        RestResponse response = new RestResponse();
        response.setCode(0);
        try {
            IptvPage page = IptvMsicUtils.parsePage(pageIndex, pageSize);
            IptvPageResult result = this.stbService.get_cate_content_list_impl(platform, NetworkUtils.getIpAddress(request), null, userId, prid, rid,null,page, test);
            List<IptvResVer> vers = result.getVers();
            for (IptvResVer ver : vers) {
                int count = iptvResVerDao.findResListPageableCount(IptvObjectEnum.diss, null, ver.getRid(), null, test);
                ver.setChild_num(count);
            }
            response.setData(vers);
        }catch (Exception e){
        	response.setCode(1);
        	response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * 鉴权
     * @param request
     * @return
     */
    @RequestMapping("authorization")
    @ResponseBody
    public RestResponse authorization(Boolean test,HttpServletRequest request,IptvPlatform platform, 
                             String userId,Long prid,Long rid,String type){
        RestResponse result = new RestResponse();
        result.setCode(0);
        try {
            IptvResVer data = null;
            switch (type){
                case "mv":
                    data = (IptvResVer)this.stbService.get_mv_info_impl(platform, NetworkUtils.getIpAddress(request), null, userId, prid, rid, test);
                   break;
                case "song":
                    data = (IptvResVer)this.stbService.get_song_info_impl(platform, NetworkUtils.getIpAddress(request), null, userId, prid, rid, test);
                    break;
                    default:
            }
            IptvWebAuthResult authResult = getAuthResult(request, userId,data);
            authResult.setData(data);
            result.setData(authResult);
        }catch (Exception e){
            result.setCode(1);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    /**
     * 页面日志
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("log")
    public void log(HttpServletRequest request,HttpServletResponse response){
        try {
            //String queryString = request.getQueryString();
            Map map = request.getParameterMap();
            Set<String> keys = map.keySet();
            System.out.println("");
            for(String key:keys){
                System.out.println(key+"==========="+request.getParameter(key));
            }
            System.out.println("");
            response.setHeader("Access-Control-Allow-Origin", "*");
        }catch (Exception e){
        }
    }

    /**
     * 专题页
     * @param test 环境
     * @param request 请求
     * @param platform 平台信息
     * @param userId 用户id
     * @param prid
     * @param rid 主题的rid
     * @return
     * @throws Exception
     */
    @RequestMapping("show/theme")
    public String theme( Boolean test,HttpServletRequest request,IptvPlatform platform,
                         String userId,Long prid,Long rid,String mac,Model model) throws Exception {
        request.setCharacterEncoding("UTF-8");
        //处理用户信息
        Object o = request.getSession().getAttribute(Constant.USER_IN_SESSION);
        WebSessionUser user;
        if(o==null){
            user = dealWithUserInfo(request);
            user = new WebSessionUser();
            user.setUserId(userId);
            user.setPlatform(platform);
            request.getSession().setAttribute(Constant.USER_IN_SESSION,user);
        }else{
            user = (WebSessionUser)o;
        }
        //获取系统环境
        boolean sysenv = getSystemev(test,request);
        IptvResVer theme = this.stbService.find_html_theme_by_rid(user.getPlatform(), NetworkUtils.getIpAddress(request), mac, user.getUserId(), prid, rid, sysenv);
        List<IptvResVer> list = theme.getList();
        List<IptvResVerForPage> items = new ArrayList<>();
        if(list!=null&&list.size()>0){
            for (IptvResVer ver : list) {
                IptvResVerForPage page = new IptvResVerForPage(ver);
                items.add(page);
            }
        }
        model.addAttribute("prid",prid);
        model.addAttribute("theme",theme);
        model.addAttribute("jsonString",JsonUtils.toJson(items));
        model.addAttribute("items",items);
        return "show/theme";
    }


    @RequestMapping("getThemeList")
    @ResponseBody
    public Object  getThemeList( Boolean test,HttpServletRequest request,IptvPlatform platform,
                               String userId,Long prid,Long rid,String mac,Model model) throws Exception {
        IptvResVer theme = this.stbService.find_html_theme_by_rid(platform, NetworkUtils.getIpAddress(request), mac,userId, prid, rid, test);
        List<IptvResVer> list = theme.getList();
        RestResponse response = new RestResponse();
        response.setData(list);
        response.setTotal(list.size());
        return response;
    }

    /**
     * 获取鉴权结果（只需填写鉴权auth字段）
     * @param request
     * @param userId
     * @return
     */
    public abstract IptvWebAuthResult getAuthResult(HttpServletRequest request,String userId,IptvResVer data) throws Exception;

    /**
     * 处理用户信息，每个地方不同，信息从requet中获取
     * @param request
     */
    public  abstract WebSessionUser   dealWithUserInfo(HttpServletRequest request) throws Exception;
}

