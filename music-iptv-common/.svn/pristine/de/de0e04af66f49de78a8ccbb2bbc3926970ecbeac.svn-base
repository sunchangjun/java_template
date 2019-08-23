package hk.reco.music.iptv.common.stats.chart.msic;

public class IptvStatsResPlay implements Comparable<IptvStatsResPlay>{

	private long rid;
	private String name;
	private String type;
	private int play_num;
	private String daily;
	private boolean free;
	private int total;
	private String daily_play_num;
	
	public long getRid() {
		return rid;
	}
	public void setRid(long rid) {
		this.rid = rid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPlay_num() {
		return play_num;
	}
	public void setPlay_num(int play_num) {
		this.play_num = play_num;
	}
	public String getDaily() {
		return daily;
	}
	public void setDaily(String daily) {
		this.daily = daily;
	}
	public boolean isFree() {
		return free;
	}
	public void setFree(boolean free) {
		this.free = free;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getDaily_play_num() {
		return daily_play_num;
	}
	public void setDaily_play_num(String daily_play_num) {
		this.daily_play_num = daily_play_num;
	}
	@Override
	public int compareTo(IptvStatsResPlay o) {
		if(this.total>o.getTotal()){
			return -1;
		}else if(this.total<o.getTotal()){
			return 1;
		}else{
			return 0;
		}
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (rid ^ (rid >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IptvStatsResPlay other = (IptvStatsResPlay) obj;
		if (rid != other.rid)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "IptvStatsResPlay [rid=" + rid + ", name=" + name + ", type=" + type + ", play_num=" + play_num + ", daily=" + daily + ", free=" + free
				+ ", total=" + total + ", daily_play_num=" + daily_play_num + "]";
	}

	
}
