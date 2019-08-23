package hk.reco.music.iptv.common.stats.chart;

import hk.reco.music.iptv.common.domain.IptvStatsUser;
import hk.reco.music.iptv.common.stats.chart.msic.IptvJdbcDao;

import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 统计用户报表
 * @author zhangsl
 * @date 2019年7月23日
 */
@Service
public class IptvStatsUserChart extends IptvStatsAbs{
	
	@Autowired
	private IptvJdbcDao iptvJdbcDao;
	@Value("${logging.path}")
	private String loggingPath;
	
	@Override
	public void run(String from, String to, String fileName, Integer top, long[] ids) throws Exception {
		XSSFWorkbook book = new XSSFWorkbook();
		XSSFSheet sheet = book.createSheet("用户统计表");
		XSSFRow row0 = sheet.createRow(0);
		createHeadCell(row0,0,"日期");
		createHeadCell(row0,1,"当日活跃用户/uv");
		createHeadCell(row0,2,"当日新增用户");
		createHeadCell(row0,3,"用户总访问量/pv");
		createHeadCell(row0,4,"点播用户数");
		createHeadCell(row0,5,"点播总次数");
		createHeadCell(row0,6,"点播总时长/秒");
    	
		List<IptvStatsUser> stat_users = this.iptvJdbcDao.findStatsUser(from, to);
		int distinct_user_visit_num = this.iptvJdbcDao.findDsitinctVisitUser(from, to);
		int distinct_play_user_num = this.iptvJdbcDao.findDsitinctPlayUser(from, to);
		int sum_user_new_num = 0;
		int sum_user_visit_total = 0;
		int sum_play_times_num = 0;
		int sum_play_duration_total = 0;
		int i=0;
		for(;i<stat_users.size();i++){
			IptvStatsUser stat_user = stat_users.get(i);
			XSSFRow row = sheet.createRow(i+1);
			createHeadCell(row, 0, String.valueOf(stat_user.getDate()));
			createHeadCell(row, 1, String.valueOf(stat_user.getUser_visit_num()));
			createHeadCell(row, 2, String.valueOf(stat_user.getUser_new_num()));
			sum_user_new_num += stat_user.getUser_new_num();
			createHeadCell(row, 3, String.valueOf(stat_user.getUser_visit_total()));
			sum_user_visit_total += stat_user.getUser_visit_total();
			createHeadCell(row, 4, String.valueOf(stat_user.getPlay_user_num()));
			createHeadCell(row, 5, String.valueOf(stat_user.getPlay_times_num()));
			sum_play_times_num += stat_user.getPlay_times_num();
			createHeadCell(row, 6, String.valueOf(stat_user.getPlay_duration_total()));
			sum_play_duration_total += stat_user.getPlay_duration_total();
		}
		//创建合计页
		XSSFRow total_row = sheet.createRow(i+1);
		createHeadCell(total_row, 0, "合计");
		createHeadCell(total_row, 1, String.valueOf(distinct_user_visit_num));
		createHeadCell(total_row, 2, String.valueOf(sum_user_new_num));
		createHeadCell(total_row, 3, String.valueOf(sum_user_visit_total));
		createHeadCell(total_row, 4, String.valueOf(distinct_play_user_num));
		createHeadCell(total_row, 5, String.valueOf(sum_play_times_num));
		createHeadCell(total_row, 6, String.valueOf(sum_play_duration_total));
        FileOutputStream os = new FileOutputStream(this.getOutputExcelPath(loggingPath, from, to, fileName));
		book.write(os);
		os.close();
		book.close();
	}
	
	public void setCellStyleAndFont(XSSFCell cell) throws Exception{
		XSSFWorkbook book = cell.getSheet().getWorkbook();
		CellStyle style = book.createCellStyle();
		Font font = book.createFont();
		font.setFontName("微软雅黑");
		font.setFontHeightInPoints((short) 9);
		style.setFont(font);
		cell.setCellStyle(style);
	}
	
	private XSSFCell createHeadCell(XSSFRow row, int colNum, String value) throws Exception{
		XSSFCell cell0 = row.createCell(colNum);
    	setCellStyleAndFont(cell0);
		cell0.setCellValue(value);
		return cell0;
	}

}
