package hk.reco.music.iptv.common.stats.chart.msic;

import java.util.HashMap;
import java.util.Map;

public class IptvNodeManager {

	private static Map<Long,IptvNode> nodes = new HashMap<Long,IptvNode>();
	
	public static void addNode(IptvNode node){
		if(!nodes.containsKey(node.rid)){
			nodes.put(node.rid, node);
		}
	}
	
	public static IptvNode getNode(Long rid){
		return nodes.get(rid);
	}
	
}
