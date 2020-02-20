package hk.reco.music.iptv.common.domain;

import java.util.Date;

public class IptvStatsUserDetail {

	private String user_id;//用户唯一标识
	private String date;//yy-mm-dd
	private int login_times;//登陆次数
	private int play_times;//点播次数
	private Date first_time;//用户首次使用的时间
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getLogin_times() {
		return login_times;
	}
	public void setLogin_times(int login_times) {
		this.login_times = login_times;
	}
	public int getPlay_times() {
		return play_times;
	}
	public void setPlay_times(int play_times) {
		this.play_times = play_times;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Date getFirst_time() {
		return first_time;
	}
	public void setFirst_time(Date first_time) {
		this.first_time = first_time;
	}

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (user_id == null ? 0 : user_id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;//地址相等
        }

        if(obj == null){
            return false;//非空性：对于任意非空引用x，x.equals(null)应该返回false。
        }

        if(obj instanceof IptvStatsUserDetail){
            IptvStatsUserDetail other = (IptvStatsUserDetail) obj;
            //需要比较的字段相等，则这两个对象相等
            if(other .getUser_id().equals(this.user_id)){
                return true;
            }
        }

        return false;
    }
}
