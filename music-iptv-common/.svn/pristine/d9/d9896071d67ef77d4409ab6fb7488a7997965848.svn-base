package hk.reco.music.iptv.common.stats.chart.msic;

import java.util.ArrayList;
import java.util.List;

public class IptvStatsObject {

	private long rid;
	private String name;
	private int access_num;
	private int duration_total;
	private String type;
	private List<IptvStatsObject> childs = new ArrayList<IptvStatsObject>();
	private List<String> users = new ArrayList<String>();
	
	public IptvStatsObject() {
	}
	
	public IptvStatsObject(long rid) {
		this.rid = rid;
	}
	
	public void addUser(String user){
		if(!users.contains(user)){
			users.add(user);
		}
	}
	
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
	public int getAccess_num() {
		return access_num;
	}
	public void setAccess_num(int access_num) {
		this.access_num = access_num;
	}
	public void addAccess_num(){
		this.access_num++;
	}
	
	public List<IptvStatsObject> getChilds() {
		return childs;
	}
	public void setChilds(List<IptvStatsObject> childs) {
		this.childs = childs;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<String> getUsers() {
		return users;
	}
	public void setUsers(List<String> users) {
		this.users = users;
	}
	public int getDuration_total() {
		return duration_total;
	}
	public void setDuration_total(int duration_total) {
		this.duration_total = duration_total;
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
		IptvStatsObject other = (IptvStatsObject) obj;
		if (rid != other.rid)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "IptvStatsObject [rid=" + rid+"]";
	}
	
}
