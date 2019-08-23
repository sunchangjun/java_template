package hk.reco.music.iptv.common.domain;

import java.util.Date;

public class IptvStats {
	
	private String date;
	private Date done_time;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Date getDone_time() {
		return done_time;
	}
	public void setDone_time(Date done_time) {
		this.done_time = done_time;
	}
}
