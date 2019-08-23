package hk.reco.music.iptv.common.domain;

public class IptvStatsUser {
	
	private String date;
	private int user_visit_num;
	private int user_new_num;
	private int user_visit_total;
	private int play_user_num;
	private int play_times_num;
	private int play_duration_total;
	
	public int getUser_visit_num() {
		return user_visit_num;
	}
	public void setUser_visit_num(int user_visit_num) {
		this.user_visit_num = user_visit_num;
	}
	public int getUser_new_num() {
		return user_new_num;
	}
	public void setUser_new_num(int user_new_num) {
		this.user_new_num = user_new_num;
	}
	public int getUser_visit_total() {
		return user_visit_total;
	}
	public void setUser_visit_total(int user_visit_total) {
		this.user_visit_total = user_visit_total;
	}
	public int getPlay_user_num() {
		return play_user_num;
	}
	public void setPlay_user_num(int play_user_num) {
		this.play_user_num = play_user_num;
	}
	public int getPlay_times_num() {
		return play_times_num;
	}
	public void setPlay_times_num(int play_times_num) {
		this.play_times_num = play_times_num;
	}
	public int getPlay_duration_total() {
		return play_duration_total;
	}
	public void setPlay_duration_total(int play_duration_total) {
		this.play_duration_total = play_duration_total;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
