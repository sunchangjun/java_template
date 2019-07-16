package hk.reco.music.iptv.common.wthx;

public class QmDiss extends QmRes{
	
	private Long diss_id;
	private String diss_name;
	private String pic_url;
	private String pic_local;
	private Long listen_num;
	
	public Long getDiss_id() {
		return diss_id;
	}
	public void setDiss_id(Long diss_id) {
		this.diss_id = diss_id;
	}
	public String getDiss_name() {
		return diss_name;
	}
	public void setDiss_name(String diss_name) {
		this.diss_name = diss_name;
	}
	public String getPic_url() {
		return pic_url;
	}
	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}
	public Long getListen_num() {
		return listen_num;
	}
	public void setListen_num(Long listen_num) {
		this.listen_num = listen_num;
	}
	public String getPic_local() {
		return pic_local;
	}
	public void setPic_local(String pic_local) {
		this.pic_local = pic_local;
	}
}
