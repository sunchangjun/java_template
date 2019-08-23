package hk.reco.music.iptv.common.stats.chart;

import hk.reco.music.iptv.common.stats.chart.msic.IptvEntity;
import hk.reco.music.iptv.common.stats.chart.msic.IptvJdbcDao;
import hk.reco.music.iptv.common.stats.chart.msic.IptvNode;
import hk.reco.music.iptv.common.stats.chart.msic.IptvNodeManager;
import hk.reco.music.iptv.common.stats.chart.msic.IptvPoiRow;
import hk.reco.music.iptv.common.stats.chart.msic.IptvStatsResVer;
import hk.reco.music.iptv.common.utils.DateUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
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
 * 频道专题报表
 * @author zhangsl
 * @date 2019年7月18日
 */
@Service
public class IptvStatsChannelChart extends IptvStatsAbs{
	
	@Autowired
	private IptvJdbcDao iptvJdbcDao;
	@Value("${logging.path}")
	private String loggingPath;
	
	public void init_user_login(String from, String to) throws Exception{
		Date start_date = DateUtils.getDate(from);
		Date end_date = DateUtils.getDate(to);
		File log_dir = new File(loggingPath);
		Map<String,Integer> uv = new HashMap<String,Integer>();
		Map<String,Integer> vod_uv = new HashMap<String,Integer>();
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
						String userid = o.getUserId();
						if(!uv.containsKey(userid)){
							uv.put(userid, null);
						}
						if(o.getAction().equals("play")){
							if(!vod_uv.containsKey(userid)){
								vod_uv.put(userid, null);
							}
						}
					}
			        reader.close();
				}
			}
		}
		System.out.println(uv.size());
		System.out.println(vod_uv.size());
	}
	
	@Override
	public void run(String from, String to, String fileName, Integer top, long[] ids) throws Exception{
		//1-开始构建结点
		List<IptvStatsResVer> vers = this.iptvJdbcDao.findAllResVer();
		for(IptvStatsResVer ver : vers){
			IptvNode node = new IptvNode();
			node.setRid(ver.getRid());
			node.setType(ver.getType());
			node.setName(ver.getName());
			if(ver.getType().equals("link")){//关系
				if(ver.getLink_crid()==null || ver.getLink_prid()==null)continue;
				long prid = ver.getLink_prid();
				long crid = ver.getLink_crid();
				IptvNode pNode = IptvNodeManager.getNode(prid);
				IptvNode cNode = IptvNodeManager.getNode(crid);
				if(cNode==null || pNode==null){
					//System.out.println("资源:"+crid+"不存在,或许是被人为删除了");//不处理这种上下关系
				}else{
					cNode.addParent(prid);
					pNode.addChild(crid);
				}
			}else{//结点
				IptvNodeManager.addNode(node);
			}
		}
		//2-完成后,读取播放的资源
		Date start_date = DateUtils.getDate(from);
		Date end_date = DateUtils.getDate(to);
		File log_dir = new File(loggingPath);
		for(File file : log_dir.listFiles()){
			if(file.isFile()){
				String file_name = file.getName();
				System.out.println(file_name);
				Date file_date = DateUtils.getDate(file_name.substring(0, 10));
				System.out.println(file_date.getTime());
				System.out.println(end_date.getTime());
				if(file_date.getTime()>=start_date.getTime() && file_date.getTime()<=end_date.getTime()){
					System.out.println("begin to parse file:"+file_name);
					BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			        String line = null;
					while((line=reader.readLine())!=null){
						IptvEntity o = IptvEntity.parse(line);
						if(o.getAction().equals("play") ){
							IptvNode media = IptvNodeManager.getNode(o.getRid());//song or mv's node
							if(media!=null){//有可能后台对象补删除了
								media.addPlayTimes();
								media.addPlayDuration(o.getDuration());
								media.addPlayUser(o.getUserId(), true);
							}
						}
					}
			        reader.close();
				}
			}
		}
		
		//3-统计完成后,打印出内容
        XSSFWorkbook book = new XSSFWorkbook();
        long[] layouts = new long[]{516510l,516511l,516512l,516513l,516514l,518425l};
        for(long rid : layouts){
        	IptvNode n1 = IptvNodeManager.getNode(rid);
        	if(n1!=null){
        		this.recursive2(n1, book.createSheet(n1.getName()));
        	}
        }
        FileOutputStream os = new FileOutputStream(this.getOutputExcelPath(loggingPath, from, to, fileName));
		book.write(os);
		os.close();
		book.close();
	}
	
	public void recursive2(IptvNode pNode, XSSFSheet sheet) throws Exception{
		if(sheet!=null){
			XSSFRow row = sheet.createRow(0);
        	XSSFCell cell = row.createCell(0);
        	setCellStyleAndFont(cell);
        	cell.setCellValue(pNode.getName()+"[rid="+pNode.getRid()+",type="+pNode.getType()+",visit:"+pNode.getPlay_times()+"]");
		}
		this.recursiveKernel2(pNode, sheet, new IptvPoiRow(1), 1);
	}
	
	public void recursiveKernel2(IptvNode pNode, XSSFSheet sheet, IptvPoiRow prow, int colNum) throws Exception{
		List<Long> crids = pNode.getCrids();
		List<IptvNode> nodes = new ArrayList<IptvNode>();
		for(long crid : crids){
			IptvNode node = IptvNodeManager.getNode(crid);
			if(node.getPlay_times()>0){
				nodes.add(node);
			}
		}
		Collections.sort(nodes);
		//nodes = nodes.subList(0, nodes.size()>=10?9:nodes.size());//限定只输出前10个
		for(IptvNode cNode : nodes){
			if(!cNode.getType().equals("mv") && !cNode.getType().equals("song")){
				XSSFRow row = sheet.createRow(prow.addRowNum());
	        	XSSFCell cell = row.createCell(colNum);
	        	setCellStyleAndFont(cell);
				cell.setCellValue(cNode.getName()+"[点播用户="+cNode.getPlay_user_num()+",点播次数="+cNode.getPlay_times()+",点播时长:"+cNode.getPlay_duration()+"]");
				recursiveKernel2(cNode, sheet, prow, colNum+1);
			}
		}
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
	
}
