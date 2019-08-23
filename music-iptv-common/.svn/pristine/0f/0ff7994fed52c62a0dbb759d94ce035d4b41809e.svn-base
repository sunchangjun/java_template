package hk.reco.music.iptv.common.stats.chart;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public abstract class IptvStatsAbs {
	
	/**
	 * 跑统计报表抽象方法
	 * @param from 日期开始
	 * @param to 日期结束
	 * @param fileName 文件名
	 * @param top 限制数据
	 * @throws Exception
	 */
	public abstract void run(String from, String to, String fileName, Integer top, long[] ids) throws Exception;

	protected String getOutputExcelPath(String logpath, String from, String to, String fileName){
		from = from.substring(5).replaceAll("-", "");
		to = to.substring(5).replaceAll("-", "");
		return logpath + "/chart/" + fileName +"("+ from +"-"+to+").xlsx";
	}
	
	public XSSFCell createDefaultCell(XSSFRow row, CellStyle style, int colNum, String value) throws Exception{
		XSSFCell cell = row.createCell(colNum);
		cell.setCellStyle(style);
		cell.setCellValue(value);
		return cell;
	}
	
	public CellStyle createDefaultStyle(XSSFWorkbook book){
		CellStyle style = book.createCellStyle();
		Font font = book.createFont();
		font.setFontName("微软雅黑");
		font.setFontHeightInPoints((short) 9);
		style.setFont(font);
		return style;
	}
	
}
