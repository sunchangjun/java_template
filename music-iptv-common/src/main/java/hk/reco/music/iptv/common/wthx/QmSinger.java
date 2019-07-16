package hk.reco.music.iptv.common.wthx;

public class QmSinger extends QmRes{
	
	private Integer type;//0男,1女,2组合
	private Long singer_id;
	private String singer_mid;
	private String singer_name;
	private String country;
	private String singer_pic_local;
	
	public Long getSinger_id() {
		return singer_id;
	}
	public void setSinger_id(Long singer_id) {
		this.singer_id = singer_id;
	}
	public String getSinger_name() {
		return singer_name;
	}
	public void setSinger_name(String singer_name) {
		this.singer_name = singer_name;
	}
	public String getSinger_pic_local() {
		return singer_pic_local;
	}
	public void setSinger_pic_local(String singer_pic_local) {
		this.singer_pic_local = singer_pic_local;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getSinger_mid() {
		return singer_mid;
	}
	public void setSinger_mid(String singer_mid) {
		this.singer_mid = singer_mid;
	}
}
