package hk.reco.music.iptv.common.action;

import hk.reco.music.iptv.common.ctrl.stb.RestResponse;
import hk.reco.music.iptv.common.domain.IptvAcctivityRecord;
import hk.reco.music.iptv.common.domain.IptvActivity;
import hk.reco.music.iptv.common.domain.IptvUser;
import hk.reco.music.iptv.common.exception.IptvError;
import hk.reco.music.iptv.common.service.IptvActivityService;
import hk.reco.music.iptv.common.service.IptvUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈iptv抽奖活动相关接口〉
 *
 * @author comelk
 * @create 2019/8/27
 */
public abstract class IptvActivityAction {

    @Autowired
    private IptvActivityService iptvActivityService;
    @Autowired
    private IptvUserService iptvUserService;

    /**
     * 因为下发的是url，id下发需要终端拼接麻烦，此处拼接好后下发减少终端更改
     */
    final private static String ACTIVITY_PARAM="?activityId=";

    final public static String ACTIVITYLIST_PATH = "show/activityList.htm";

    @RequestMapping(value = "activity/getActivityUrl",method = RequestMethod.POST)
    @ApiOperation(value = "获取活动的url", notes = "获取到活动页面的url(activityUrl活动页路径会包含activityId参数，" +
            "recordUrl参与的历史活动列表url,times剩余抽奖次数)", response = RestResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 0, message = "调用成功")})
    @ResponseBody
    @CrossOrigin
    public RestResponse getActivityUrl(@RequestParam(required = true)String userId) {
        try {
            Map<String, Object> map = new HashMap<>();
            IptvActivity activity = iptvActivityService.findActiveActivtity();
            //当前存在活动，返回活动的url
            if(activity!=null){
                Integer activityId = activity.getId();
                map.put("activityUrl", activity.getUrl()+ACTIVITY_PARAM+activityId);
                map.put("times",iptvActivityService.findUserActivityTimes(activityId,userId));
            }
            //返回的是用户参与过的活动的url
            map.put("recordUrl", ACTIVITYLIST_PATH);
            RestResponse response = new RestResponse();
            response.setData(map);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return new RestResponse(IptvError.SYSTEM_ERROR);
        }
    }


    @RequestMapping(value = "activity/getActivityList",method = RequestMethod.POST)
    @ApiOperation(value = "获取用户参与过的活动列表", notes = "获取用户参与过的活动列表", response = RestResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 0, message = "调用成功")})
    @ResponseBody
    @CrossOrigin
    public RestResponse getActivityList(@RequestParam(required = true)String userId) {
        try {
            List<IptvActivity> list = iptvActivityService.findActivityListByUserId(userId);
            RestResponse response = new RestResponse();
            response.setData(list);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return new RestResponse(IptvError.SYSTEM_ERROR);
        }
    }


    @RequestMapping(value = "activity/getUserActivityRecord",method = RequestMethod.POST)
    @ApiOperation(value = "获取用户参与的活动参与记录", notes = "获取用户参与的活动参与记录", response = RestResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 0, message = "调用成功")})
    @ResponseBody
    @CrossOrigin
    public RestResponse getUserActivityRecord(@RequestParam(required = true)String userId,@RequestParam(required = true) Integer activityId) {
        try {
            List<IptvAcctivityRecord> list = iptvActivityService.findUserActivityRecord(userId, activityId);
            RestResponse response = new RestResponse();
            response.setData(list);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return new RestResponse(IptvError.SYSTEM_ERROR);
        }
    }


    @RequestMapping(value = "activity/recordUserPhone",method = RequestMethod.POST)
    @ApiOperation(value = "上报用户电话", notes = "上报用户电话", response = RestResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 0, message = "调用成功")})
    @ResponseBody
    @CrossOrigin
    public RestResponse getUserActivityRecord(@RequestParam(required = true)String userId, @RequestParam(required = true)String phoneNum) {
        try {
            RestResponse response = new RestResponse();
            // iptvUserService.update();
            IptvUser user = new IptvUser();
            user.setUser_id(userId);
            user.setPhone_num(phoneNum);
            iptvUserService.update(user);
            response.setData(user);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return new RestResponse(IptvError.SYSTEM_ERROR);
        }
    }


    @RequestMapping(value = "activity/getActivityDetail",method = RequestMethod.POST)
    @ApiOperation(notes = "获取指定活动详细信息,activty表示目前活动的对象，active表示活动是否正在进行(终端只加载一次url出现app并未关闭但是活动已经结束)" +
            ",times表示当前用户剩余的抽奖次数", value = "获取指定活动详细信息", response = RestResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 0, message = "调用成功")})
    @ResponseBody
    @CrossOrigin
    public RestResponse getActivityDetail(@RequestParam(required = true)String userId,@RequestParam(required = true)Integer activityId) {
        try {
            RestResponse response = new RestResponse();
            Map<String,Object> map = new HashMap<>();
            IptvActivity activity = iptvActivityService.findActivityById(activityId);
            map.put("activty",activity);
            map.put("active",iptvActivityService.findActiveActivtity()!=null);
            map.put("times",iptvActivityService.findUserActivityTimes(activityId,userId));
            response.setData(map);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return new RestResponse(IptvError.SYSTEM_ERROR);
        }
    }

    /**
     * 此接口会在后台计算奖品，并入库，只返回中奖结果给页面展示
     * @param activityId
     * @param userId
     * @return
     */
    @RequestMapping(value = "activity/getActivityResult",method = RequestMethod.POST)
    @ApiOperation(notes = "抽奖，点击抽奖按钮，返回中奖奖品（award）和剩余的抽奖次数（times）", value = "抽奖接口", response = RestResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 0, message = "调用成功")})
    @ResponseBody
    @CrossOrigin
    public RestResponse getActivityResult(@RequestParam(required = true)Integer activityId,
                                          @RequestParam(required = true) String userId,
                                          @RequestParam(required = false)Integer times) {
        try {
            RestResponse response = new RestResponse();
            Map<String,Object> map = new HashMap<>();
            if(times==null){
                times =  iptvActivityService.findUserActivityTimes(activityId,userId);
            }
            map.put("times",times);
            if(times>0){
                map.put("award",iptvActivityService.caculateActivityResult(activityId,userId));
                map.put("times",times-1);
            }
            response.setData(map);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return new RestResponse(IptvError.SYSTEM_ERROR);
        }
    }


    @RequestMapping(value = "activity/getActivityTimes",method = RequestMethod.POST)
    @ApiOperation(value = "获取剩余的抽奖次数", notes = "获取剩余的抽奖次数", response = RestResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 0, message = "调用成功")})
    @ResponseBody
    @CrossOrigin
    public RestResponse getActivityTimes(@RequestParam(required = true)Integer activityId,
                                         @RequestParam(required = true)String userId) {
        try {
            RestResponse response = new RestResponse();
            int times = iptvActivityService.findUserActivityTimes(activityId,userId);
            response.setData(times);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return new RestResponse(IptvError.SYSTEM_ERROR);
        }
    }


    @RequestMapping(value = "activity/getActivityRecord",method = RequestMethod.POST)
    @ApiOperation(value = "获取活动的中奖记录", notes = "获取活动的中奖记录（默认取20条数据）", response = RestResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 0, message = "调用成功")})
    @ResponseBody
    @CrossOrigin
    public RestResponse getActivityRecord(@RequestParam(required = true)Integer activityId) {
        try {
            RestResponse response = new RestResponse();
            List<IptvAcctivityRecord> list = iptvActivityService.findActivityRecord(activityId);
            response.setData(list);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return new RestResponse(IptvError.SYSTEM_ERROR);
        }
    }


}
