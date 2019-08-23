package hk.reco.music.iptv.common.stats.chart;

import hk.reco.music.iptv.common.domain.IptvResVer;
import hk.reco.music.iptv.common.stats.chart.msic.IptvEntity;
import hk.reco.music.iptv.common.stats.chart.msic.IptvJdbcDao;
import hk.reco.music.iptv.common.utils.DateUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
 * 注入资源统计报表
 * @author zhangsl
 * @date 2019年8月9日
 */
@Service
public class IptvStatsInjectChart{
	
	@Autowired
	private IptvJdbcDao iptvJdbcDao;
	@Value("${logging.path}")
	private String loggingPath;
	
	public void run(String from, String to, String path) throws Exception{
		Map<Long, Map<String,Object>> map = new HashMap<Long, Map<String,Object>>();
		//init片单
		List<String> res_ids = new ArrayList<String>();
		BufferedReader r1 = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
		String str = null;
		int index = 0;
		while((str=r1.readLine())!=null){
			index++;
			if(!res_ids.contains(str)){
				long res_id = Long.parseLong(str);
				IptvResVer res = this.iptvJdbcDao.findResByResid(res_id);
				if(res!=null){
					long rid = res.getRid();
					Map<String, Object> stat = new HashMap<String, Object>();
					stat.put("name", res.getName());
					stat.put("rid", res.getRid());
					stat.put("time", res.getCreate_time());
					map.put(rid, stat);
					System.out.println(index+" query res_id:"+res_id);
				}
			}
		}
        r1.close();
        System.out.println(map.size());
		
        //2-完成后,读取播放的资源
		Date start_date = DateUtils.getDate(from);
		Date end_date = DateUtils.getDate(to);
		File log_dir = new File(loggingPath);
		for(File file : log_dir.listFiles()){
			if(file.isFile()){
				String file_name = file.getName();
				System.out.println(file_name);
				Date file_date = DateUtils.getDate(file_name.substring(0, 10));
				if(file_date.getTime()>=start_date.getTime() && file_date.getTime()<=end_date.getTime()){
					System.out.println("begin to parse file:"+file_name);
					BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			        String line = null;
					while((line=reader.readLine())!=null){
						IptvEntity o = IptvEntity.parse(line);
						if(o.getAction().equals("play") ){
							long rid = o.getRid();//播放资源的id
							String user = o.getUserId();
							int duration = o.getDuration();
							if(map.containsKey(rid)){
								Map<String, Object> stat = map.get(rid);
								//处理用户
								@SuppressWarnings("unchecked")
								List<String> users = (List<String>)stat.get("users");
								if(users == null){
									users = new ArrayList<String>();
									stat.put("users", users);
								}
								if(!users.contains(user)){
									users.add(user);
								}
								//处理播放量
								Integer play_num = (Integer)stat.get("play_num");
								if(play_num==null){
									play_num = 0;
									stat.put("play_num", play_num);
								}
								stat.put("play_num", play_num+1);
								//处理播放时长
								Integer play_duration = (Integer)stat.get("play_duration");
								if(play_duration==null){
									play_duration = 0;
									stat.put("play_duration", play_duration);
								}
								stat.put("play_duration", play_duration+duration);
							}
						}
					}
			        reader.close();
				}
			}
		}
		
		//3-统计完成后,打印出内容
        XSSFWorkbook book = new XSSFWorkbook();
        XSSFSheet sheet = book.createSheet("上架资源统计表");
        //3-处理表头
        XSSFRow row0 = sheet.createRow(0);
        XSSFCell c0 = row0.createCell(0);
        setCellStyleAndFont(c0);
        c0.setCellValue("rid");
        
        XSSFCell c1 = row0.createCell(1);
        setCellStyleAndFont(c1);
        c1.setCellValue("名称");
        
        XSSFCell c2 = row0.createCell(2);
        setCellStyleAndFont(c2);
        c2.setCellValue("用户(去重)");
        
        XSSFCell c3 = row0.createCell(3);
        setCellStyleAndFont(c3);
        c3.setCellValue("播放次数");
        
        XSSFCell c4 = row0.createCell(4);
        setCellStyleAndFont(c4);
        c4.setCellValue("播放时长/秒");
        
        int row_num = 1;
        Iterator<Long> it = map.keySet().iterator();
        while(it.hasNext()){
        	Long key = it.next();
        	Map<String, Object> stat = map.get(key);
        	XSSFRow row = sheet.createRow(row_num);
        	//0
        	XSSFCell cell0 = row.createCell(0);
        	setCellStyleAndFont(cell0);
            cell0.setCellValue((Long)stat.get("rid"));
        	//1
        	XSSFCell cell1 = row.createCell(1);
        	setCellStyleAndFont(cell1);
            cell1.setCellValue((String)stat.get("name"));
        	//2
        	XSSFCell cell2= row.createCell(2);
        	setCellStyleAndFont(cell2);
        	@SuppressWarnings("unchecked")
			List<String> users = (List<String>)stat.get("users");
        	int size = users==null?0:users.size();
            cell2.setCellValue(String.valueOf(size));
        	//3
        	XSSFCell cell3= row.createCell(3);
        	setCellStyleAndFont(cell3);
        	Integer play_num = (Integer)stat.get("play_num");
        	play_num = (play_num==null)?0:play_num;
            cell3.setCellValue(String.valueOf(play_num));
        	//4
        	XSSFCell cell4= row.createCell(4);
        	setCellStyleAndFont(cell4);
        	Integer play_duration = (Integer)stat.get("play_duration");
        	play_duration = (play_duration==null)?0:play_duration;
            cell4.setCellValue(String.valueOf(play_duration));
        	row_num++;
        }
        
        FileOutputStream os = new FileOutputStream(this.getOutputExcelPath(loggingPath, from, to, "福建移动注入资源统计"));
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
	
	private String getOutputExcelPath(String logpath, String from, String to, String fileName){
		from = from.substring(5).replaceAll("-", "");
		to = to.substring(5).replaceAll("-", "");
		return logpath + "/chart/" + fileName +"("+ from +"-"+to+").xlsx";
	}
	
}
