package hk.reco.music.iptv.common.domain;

import java.util.Date;

/**
 * iptv用户
 * @author zhangsl
 * @date 2019年2月21日
 */
public class IptvUser {

	private String user_id;
	private String mac;
	private Date create_time;
	private boolean test;
	//add by comelk 抽奖活动记录用户电话便与联系发奖
	private String phone_num;
	private String carry_id;
	public String getPhone_num() {
		return phone_num;
	}

	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public boolean isTest() {
		return test;
	}
	public void setTest(boolean test) {
		this.test = test;
	}
	public String getCarry_id() {
		return carry_id;
	}

	public void setCarry_id(String carry_id) {
		this.carry_id = carry_id;
	}
}
