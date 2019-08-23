package hk.reco.music.iptv.common.stats.view;

import hk.reco.music.iptv.common.domain.IptvStatsUser;

public class IptvStatsUserView {
	private String date;
	private String user_visit_num;//访问用户,已经去重(uv)
	private String user_new_num;//新增用户
	private String user_visit_total;//用户访问总次数(pv)
	private String play_user_num;//点播用户数,已经去重
	private String play_times_num;//点播次数合计
	private String play_duration_total;//合计点播时长(秒)
	
	public static IptvStatsUserView instance(IptvStatsUser u){
		IptvStatsUserView v = new IptvStatsUserView();
		v.setDate(u.getDate());
		v.setPlay_duration_total(String.valueOf(u.getPlay_duration_total()));
		v.setPlay_times_num(String.valueOf(u.getPlay_times_num()));
		v.setPlay_user_num(String.valueOf(u.getPlay_user_num()));
		v.setUser_new_num(String.valueOf(u.getUser_new_num()));
		v.setUser_visit_num(String.valueOf(u.getUser_visit_num()));
		v.setUser_visit_total(String.valueOf(u.getUser_visit_total()));
		return v;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getUser_visit_num() {
		return user_visit_num;
	}
	public void setUser_visit_num(String user_visit_num) {
		this.user_visit_num = user_visit_num;
	}
	public String getUser_new_num() {
		return user_new_num;
	}
	public void setUser_new_num(String user_new_num) {
		this.user_new_num = user_new_num;
	}
	public String getUser_visit_total() {
		return user_visit_total;
	}
	public void setUser_visit_total(String user_visit_total) {
		this.user_visit_total = user_visit_total;
	}
	public String getPlay_user_num() {
		return play_user_num;
	}
	public void setPlay_user_num(String play_user_num) {
		this.play_user_num = play_user_num;
	}
	public String getPlay_times_num() {
		return play_times_num;
	}
	public void setPlay_times_num(String play_times_num) {
		this.play_times_num = play_times_num;
	}
	public String getPlay_duration_total() {
		return play_duration_total;
	}
	public void setPlay_duration_total(String play_duration_total) {
		this.play_duration_total = play_duration_total;
	}
}
