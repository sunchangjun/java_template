package hk.reco.music.iptv.common.rainbow.jsyd;

public class Duration {
	private String id;
	private long start;
	private long end;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public long getStart() {
		return start;
	}
	public void setStart(long start) {
		this.start = start;
	}
	public long getEnd() {
		return end;
	}
	public void setEnd(long end) {
		this.end = end;
	}
	public long getDur(){
		if(this.end==0){
			return 5*60*1000;
		}else{
			return end-start;
		}
	}
	
}
