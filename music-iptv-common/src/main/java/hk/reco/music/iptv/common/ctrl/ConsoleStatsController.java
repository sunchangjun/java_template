package hk.reco.music.iptv.common.ctrl;

import hk.reco.music.iptv.common.service.IptvConsoleStatsService;
import hk.reco.music.iptv.common.stats.view.IptvStatsUserView;
import hk.reco.music.iptv.common.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 〈统计相关逻辑的控制器〉
 *
 * @author comelk
 * @create 2019/8/1
 */
@RequestMapping("console")
@Controller
public class ConsoleStatsController {

    @Autowired
    private IptvConsoleStatsService iptvConsoleStatsService;

    @RequestMapping("userStats")
    public String userStats(){
         return "stats/userstats";
    }

    @RequestMapping("userStatsData")
    @ResponseBody
    public Object userStatsData(String startTime,String endTime){
        try {
            List<IptvStatsUserView> stats = iptvConsoleStatsService.statsUserView(startTime, endTime);
            return new JsonResult(true,stats);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(e.getMessage());
        }
    }

}
