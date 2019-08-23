package hk.reco.music.iptv.common.stats.chart;

import hk.reco.music.iptv.common.stats.chart.msic.IptvEntity;
import hk.reco.music.iptv.common.stats.chart.msic.IptvJdbcDao;
import hk.reco.music.iptv.common.stats.chart.msic.IptvStatsObject;
import hk.reco.music.iptv.common.stats.chart.msic.IptvStatsResVer;
import hk.reco.music.iptv.common.utils.DateUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 大厅推荐位统计报表
 * @author zhangsl
 * @date 2019年7月23日
 */
@Service
public class IptvStatsHallChart extends IptvStatsAbs {
	
	@Autowired
	private IptvJdbcDao iptvJdbcDao;
	@Value("${logging.path}")
	private String loggingPath;
	
	public void run(String from, String to, String fileName, Integer top, long[] ids) throws Exception {
		List<Long> idsList = Arrays.asList(ArrayUtils.toObject(ids));
		Date start_date = DateUtils.getDate(from);
		Date end_date = DateUtils.getDate(to);
		File log_dir = new File(loggingPath);
		Map<Long,IptvStatsObject> topics = new LinkedHashMap<Long,IptvStatsObject>();
		for(File file : log_dir.listFiles()){
			if(file.isFile()){
				String file_name = file.getName();
				Date file_date = DateUtils.getDate(file_name.substring(0, 10));
				if(file_date.getTime()>=start_date.getTime() && file_date.getTime()<=end_date.getTime()){
					System.out.println("begin to parse file:"+file_name);
					BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			        String line = null;
					while((line=reader.readLine())!=null){
						IptvEntity o = IptvEntity.parse(line);
						Long prid = o.getPrid();
						Long rid = o.getRid();
						if(prid!=null && rid!=null){
							if(prid == -1 && idsList.contains(rid)){
								IptvStatsObject topic = topics.get(rid);
								if(topic==null){
									IptvStatsResVer ver = this.iptvJdbcDao.findResVer(rid);
									System.out.println("query--->"+rid);
									topic = new IptvStatsObject();
									topic.setRid(rid);
									topic.setAccess_num(1);
									topic.setName(ver.getName());
									topic.setType(ver.getType());
									topics.put(rid, topic);
								}else{
									topic.addAccess_num();
								}
								topic.addUser(o.getUserId());
							}else if(topics.containsKey(prid) && o.getAction().equals("play")){
								IptvStatsObject topic = topics.get(prid);
								List<IptvStatsObject> childs = topic.getChilds();
								IptvStatsObject child = null;
								if(childs.contains(new IptvStatsObject(rid))){
									child = get(childs, rid);
									child.addAccess_num();
								}else{//从数据库查出
									IptvStatsResVer ver = this.iptvJdbcDao.findResVer(rid);
									child = new IptvStatsObject();
									child.setRid(rid);
									child.setAccess_num(1);
									child.setName(ver.getName());
									child.setType(ver.getType());
									childs.add(child);
								}
								child.addUser(o.getUserId());
								topic.addAccess_num();
								topic.addUser(o.getUserId());
								topic.setDuration_total(topic.getDuration_total()+o.getDuration());
							}
						}
					}
			        reader.close();
				}
			}
		}
		
		XSSFWorkbook book = new XSSFWorkbook();
		XSSFSheet sheet = book.createSheet("外部推荐专题统计表");
		CellStyle style = createDefaultStyle(book);
		XSSFRow row0 = sheet.createRow(0);
		createDefaultCell(row0, style, 0, "日期");
    	
		int rowNum = 0;
		Iterator<Long> it = topics.keySet().iterator();
		while(it.hasNext()){
			IptvStatsObject topic = topics.get(it.next());
			XSSFRow row = sheet.createRow(rowNum++);
			createDefaultCell(row, style, 0, topic.getName()+":[类型="+topic.getType()+",用户="+topic.getUsers().size()+",访问量="+topic.getAccess_num()+",总时长="+topic.getDuration_total()+"]");
			for(IptvStatsObject child : topic.getChilds()){
				XSSFRow row1 = sheet.createRow(rowNum++);
				createDefaultCell(row1, style, 1, child.getName()+":[类型="+child.getType()+",用户="+child.getUsers().size()+",访问量="+child.getAccess_num()+"]");
			}
		}
        FileOutputStream os = new FileOutputStream(getOutputExcelPath(loggingPath, from, to, fileName));
		book.write(os);
		os.close();
		book.close();
	}
	
	public IptvStatsObject get(List<IptvStatsObject> os, long rid){
		for(IptvStatsObject o : os){
			if(o.getRid() == rid){
				return o;
			}
		}
		return null;
	}
	
}
