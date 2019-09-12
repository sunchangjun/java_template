package hk.reco.music.iptv.common.ctrl;

import hk.reco.music.iptv.common.domain.*;
import hk.reco.music.iptv.common.exception.IptvBusinessException;
import hk.reco.music.iptv.common.service.IptvActivityService;
import hk.reco.music.iptv.common.utils.IptvFileUtils;
import hk.reco.music.iptv.common.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 〈抽奖活动后台控制器〉
 *
 * @author comelk
 * @create 2019/8/29
 */
@Controller
@RequestMapping("console")
public class IptvConsoleActivityController {


    @Value("${iptv.file.ssh.ip}")
    private String ip;
    @Value("${iptv.file.ssh.user}")
    private String user;
    @Value("${iptv.file.ssh.pwd}")
    private String pwd;

    @Autowired
    private IptvActivityService iptvActivityService;

    @RequestMapping("activityList")
    public String list(){
       return "activity/list";
    }


    @RequestMapping("activityListData")
    @ResponseBody
    public Object activityListData(){
        List<IptvActivity> iptvActivities = iptvActivityService.consolefindActivityList();
        EasyUiListResult<IptvActivity> object = new EasyUiListResult<>();
        object.setRows(iptvActivities);
        object.setTotal(iptvActivities.size());
        return object;
    }

    @RequestMapping("getRecordList")
    @ResponseBody
    public Object getRecordList(Integer activityId,String userId,String phone,Boolean record,EasyUiBaseQuery query){
        EasyUiListResult<IptvAcctivityRecord> iptvActivities = iptvActivityService.consolefindActivityRecordList(activityId,userId,phone,record,query);
        return iptvActivities;
    }

    @RequestMapping("addActivity")
    @ResponseBody
    public Object addActivity(IptvActivity activity){
        iptvActivityService.consoleInsertNewActivity(activity);
        return new JsonResult();
    }

    @RequestMapping("addAward")
    @ResponseBody
    public Object addAward(IptvActivityAward award, MultipartFile iptvPoster, HttpServletRequest req) throws IptvBusinessException {
        if (iptvPoster != null && iptvPoster.getSize() > 0) {
            // IptvFileUtils.saveFile(iptvBigPoster);
            String path = IptvFileUtils.saveFile(iptvPoster, req, ip, user, pwd);
            award.setPoster(path);
        } else {
            return new JsonResult("海报为空");
        }
        iptvActivityService.consoleInsertNewAward(award);
        return new JsonResult();
    }


    @RequestMapping("findActivittyAwards")
    @ResponseBody
    public Object findActivittyAwards(Integer id){
        EasyUiListResult<IptvActivityAward> result = new EasyUiListResult();
        List<IptvActivityAward> awards = iptvActivityService.findActivityAwardById(id);
        result.setTotal(awards.size());
        result.setRows(awards);
        return result;
    }

    @RequestMapping("deleteAward")
    @ResponseBody
    public Object deleteAward(Integer awardId){
        iptvActivityService.consoleDeleteAwardById(awardId);
        return new JsonResult();
    }

    @RequestMapping("onlineActivity")
    @ResponseBody
    public Object onlineActivity(Integer activityId){
        iptvActivityService.consoleOnlineActivity(activityId);
        return new JsonResult();
    }

    @RequestMapping("delineActivity")
    @ResponseBody
    public Object delineActivity(Integer activityId){
        iptvActivityService.consoleDelineActivity(activityId);
        return new JsonResult();
    }

    @RequestMapping("editorAward")
    @ResponseBody
    public Object editorAward(IptvActivityAward award, MultipartFile iptvPoster, HttpServletRequest req) throws IptvBusinessException {
        if (iptvPoster != null && iptvPoster.getSize() > 0) {
            // IptvFileUtils.saveFile(iptvBigPoster);
            String path = IptvFileUtils.saveFile(iptvPoster, req, ip, user, pwd);
            award.setPoster(path);
        }
        iptvActivityService.consoleUpdateAward(award);
        return new JsonResult();
    }

}
