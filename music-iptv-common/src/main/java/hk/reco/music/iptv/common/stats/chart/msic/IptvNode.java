package hk.reco.music.iptv.common.stats.chart.msic;

import java.util.ArrayList;
import java.util.List;

public class IptvNode implements Comparable<IptvNode> {

	public long rid;
	public String name;
	public String type;
	public int play_times;// 播放总次数
	public int play_duration;// 播放总秒数
	public int play_user_num;// 播放用户数
	private List<String> play_user = new ArrayList<String>();//去重用户数
	private List<Long> prids = new ArrayList<Long>();
	private List<Long> crids = new ArrayList<Long>();

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

	public List<Long> getPrids() {
		return prids;
	}

	public void setPrids(List<Long> prids) {
		this.prids = prids;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPlay_times() {
		return play_times;
	}

	public void setPlay_times(int play_times) {
		this.play_times = play_times;
	}

	public List<Long> getCrids() {
		return crids;
	}

	public void setCrids(List<Long> crids) {
		this.crids = crids;
	}

	public int getPlay_duration() {
		return play_duration;
	}

	public void setPlay_duration(int play_duration) {
		this.play_duration = play_duration;
	}

	public int getPlay_user_num() {
		return play_user_num;
	}

	public void setPlay_user_num(int play_user_num) {
		this.play_user_num = play_user_num;
	}

	public List<String> getPlay_user() {
		return play_user;
	}

	public void setPlay_user(List<String> play_user) {
		this.play_user = play_user;
	}

	public void addParent(long prid) {
		if (!prids.contains(prid)) {
			prids.add(prid);
		}
	}

	public void addChild(long crid) {
		if (!crids.contains(crid)) {
			crids.add(crid);
		}
	}

	public void addPlayTimes() {
		this.play_times++;
		for (long prid : prids) {
			IptvNode p = IptvNodeManager.getNode(prid);
			if (p != null) {
				p.addPlayTimes();
			}
		}
	}
	
	public void addPlayDuration(int sec) {
		this.play_duration += sec;
		for (long prid : prids) {
			IptvNode p = IptvNodeManager.getNode(prid);
			if (p != null) {
				p.addPlayDuration(sec);
			}
		}
	}
	
	public void addPlayUser(String user, boolean check) {
		if(check){
			if(!this.play_user.contains(user)){
				this.play_user.add(user);
				this.play_user_num++;
				for (long prid : prids) {
					IptvNode p = IptvNodeManager.getNode(prid);
					if (p != null) {
						p.addPlayUser(user, false);
					}
				}
			}
		}else{
			this.play_user_num++;
			for (long prid : prids) {
				IptvNode p = IptvNodeManager.getNode(prid);
				if (p != null) {
					p.addPlayUser(user, false);
				}
			}
		}
	}

	@Override
	public int compareTo(IptvNode o) {
		if(this.play_times>o.play_times){
			return -1;
		}else if(this.play_times<o.play_times){
			return 1;
		}else{
			return 0;
		}
	}

}
