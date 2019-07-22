package hk.reco.music.iptv.common.ctrl;

import hk.reco.music.iptv.common.ctrl.stb.RestResponse;
import hk.reco.music.iptv.common.service.IptvConsoleResService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName RemoteCallController
 * @Description TODO
 * @Author wangpq
 * @Date 2019/7/19 19:01
 * @Version 1.0
 */
@RequestMapping("remoteCall")
@RestController
public class RemoteCallController {

    @Autowired
    private IptvConsoleResService iptvConsoleResService;

    /**
     *
     *  未来省份下线通知
     *
     *
     * @Author wangpq
     * @Param [resId]
     * @Date 2019/7/19 11:44
     * @return hk.reco.music.iptv.common.ctrl.stb.RestResponse
     */
    @RequestMapping(value = "/newTVResOffline", method = RequestMethod.POST)
    public RestResponse newTVResOffline(@RequestParam("resId") Long resId) {
        return iptvConsoleResService.newTVResOffline(resId);
    }
}
