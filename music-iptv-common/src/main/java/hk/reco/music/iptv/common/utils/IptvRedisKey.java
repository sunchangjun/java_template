package hk.reco.music.iptv.common.utils;

public class IptvRedisKey {
	
	//首页布局缓存
	public static final String layoutName = "iptv-layout";
	public static final String layoutKey = "'root'";

	//list类型的缓存
	public static final String listName = "iptv-list";
	public static final String listKey = "#prid+'_'+#offset+'_'+#size";
	public static final String listMvAndSongKey = "#prid+'_mv_song_'+#offset+'_'+#size";
	public static final String listPyKey = "#prid+'_'+#pinyin+'_'+#offset+'_'+#size";
	
	//类型缓存
	public static final String typeName = "iptv-type";
	public static final String typeKey = "#type+'_'+#childtype";
	public static final String typePageKey = "#type+'_'+#offset+'_'+#size";
	
	//对象缓存
	public static final String resName = "iptv-res";
	public static final String resKey = "#rid";
	
	//免费缓存(分页)
	public static final String freeName = "iptv-free";
	public static final String freeKey = "#type+'_'+#offset+'_'+#size";
	
}
