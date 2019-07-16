package hk.reco.music.iptv.common.wthx;

public class QmTop{
	
	private Long id;
	private Integer top_id;
	private String top_name;
	private Long listen_num;
	private String top_header_pic;
	private Integer group_id;
	private String group_name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTop_name() {
		return top_name;
	}
	public void setTop_name(String top_name) {
		this.top_name = top_name;
	}
	public Long getListen_num() {
		return listen_num;
	}
	public void setListen_num(Long listen_num) {
		this.listen_num = listen_num;
	}
	public String getTop_header_pic() {
		return top_header_pic;
	}
	public void setTop_header_pic(String top_header_pic) {
		this.top_header_pic = top_header_pic;
	}
	public Integer getGroup_id() {
		return group_id;
	}
	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	public Integer getTop_id() {
		return top_id;
	}
	public void setTop_id(Integer top_id) {
		this.top_id = top_id;
	}
}
