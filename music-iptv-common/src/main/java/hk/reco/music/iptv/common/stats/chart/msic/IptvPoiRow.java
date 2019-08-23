package hk.reco.music.iptv.common.stats.chart.msic;

public class IptvPoiRow {

	private int rowNum;

	public IptvPoiRow(int rowNum) {
		this.rowNum = rowNum;
	}

	public int getRowNum() {
		return rowNum;
	}

	public int addRowNum() {
		this.rowNum = rowNum + 1;
		return this.rowNum;
	}
}
