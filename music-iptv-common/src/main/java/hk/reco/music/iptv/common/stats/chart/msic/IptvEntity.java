package hk.reco.music.iptv.common.stats.chart.msic;

public class IptvEntity {
	private String action;
	private long timestamp;
	private String ip;
	private String mac;
	private String userId;
	private Long prid;
	private Long rid;
	private Long extrid;
	private String type;
	private String pinyin;
	private Integer duration;
	private String method;
	private String platform;
	private boolean test;
	private String path;
	private String version;
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Long getPrid() {
		return prid;
	}
	public void setPrid(Long prid) {
		this.prid = prid;
	}
	public Long getRid() {
		return rid;
	}
	public void setRid(Long rid) {
		this.rid = rid;
	}
	public Long getExtrid() {
		return extrid;
	}
	public void setExtrid(Long extrid) {
		this.extrid = extrid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public boolean isTest() {
		return test;
	}
	public void setTest(boolean test) {
		this.test = test;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
	public static IptvEntity parse(String data) throws Exception{
		if(data == null){
			throw new Exception("empty data");
		}
		String[] ps = data.split(",");
		IptvEntity o = new IptvEntity();
		o.setAction(ps[0]);
		o.setTimestamp(IptvLogUtils.parseLong(ps[1]));
		o.setIp(ps[2]);
		o.setMac(ps[3]);
		o.setUserId(ps[4]);
		o.setPrid(IptvLogUtils.parseLong(ps[5]));
		o.setRid(IptvLogUtils.parseLong(ps[6]));
		o.setExtrid(IptvLogUtils.parseLong(ps[7]));
		o.setType(ps[8]);
		o.setPinyin(ps[9]);
		o.setDuration(IptvLogUtils.parseInteger(ps[10]));
		o.setMethod(ps[11]);
		o.setPlatform(ps[12]);
		o.setTest(IptvLogUtils.parseBoolean(ps[13]));
		if(ps.length>14){
			o.setPath(ps[14]);
			o.setVersion(ps[15]);
		}
		return o;
	}
	
	@Override
	public String toString() {
		return "IptvEntity [action=" + action + ", timestamp=" + timestamp + ", ip=" + ip + ", mac=" + mac
				+ ", userId=" + userId + ", prid=" + prid + ", rid=" + rid + ", extrid=" + extrid + ", type=" + type
				+ ", pinyin=" + pinyin + ", duration=" + duration + ", method=" + method + ", platform=" + platform
				+ ", test=" + test + ", path=" + path + ", version=" + version + "]";
	}

	
}
