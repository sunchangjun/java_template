package hk.reco.music.iptv.common.ctrl;

import hk.reco.music.iptv.common.service.IptvConsoleStatsService;
import hk.reco.music.iptv.common.stats.view.IptvStatsUserView;
import hk.reco.music.iptv.common.utils.HttpDownloadUtil;
import hk.reco.music.iptv.common.utils.JsonResult;
import hk.reco.music.iptv.common.utils.excel.CsvUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
    public String userStats() {
        return "stats/userstats";
    }

    @RequestMapping("userStatsData")
    @ResponseBody
    public Object userStatsData(String startTime, String endTime) {
        try {
            List<IptvStatsUserView> stats = iptvConsoleStatsService.statsUserView(startTime, endTime);
            return new JsonResult(true, stats);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(e.getMessage());
        }
    }

    @GetMapping("exportUserStatsData")
    public void exportUserStatsData(HttpServletResponse response, HttpServletRequest request, String startTime, String endTime) {
        //1.创建csv文件
        File csvFile = new File("/data/excel/" + startTime + "_between_" + endTime + "_exportUserStatsData.csv");
        File parent=csvFile.getParentFile();
        if(parent!=null&&!parent.exists()){
            parent.mkdirs();
        }
        try {
            //2.查询数据
            List<IptvStatsUserView> stats = iptvConsoleStatsService.statsUserView(startTime, endTime);
            //3.写入数据
            BufferedWriter csvWtriter = new BufferedWriter(new OutputStreamWriter((new FileOutputStream(csvFile)), "GB2312"), 1024);
            // 写入文件内容
            for (IptvStatsUserView iptvStatsUserView : stats) {
                CsvUtils.writeRow(new ArrayList<>(Arrays.asList(
                        iptvStatsUserView.getDate(),//日期
                        iptvStatsUserView.getUser_visit_num(),//当日活跃用户/uv
                        iptvStatsUserView.getUser_new_num(),//当日新增用户
                        iptvStatsUserView.getUser_visit_total(),//用户总访问量/pv
                        iptvStatsUserView.getPlay_user_num(),//点播用户数
                        iptvStatsUserView.getPlay_times_num(),//点播总次数
                        iptvStatsUserView.getPlay_duration_total()//播放总时长
                        //点播总时长/秒
                )), csvWtriter);
            }
            csvWtriter.flush();
            //4.导出
            HttpDownloadUtil.downloadFile(csvFile.getAbsolutePath(), csvFile.getName(), response, request);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            csvFile.delete();
        }
    }


}
