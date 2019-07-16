package hk.reco.music.iptv.common.utils;
/**
 * qq音乐图片工具
 * @author zhangsl
 * @date 2019年05月24日
 */
public class QmPicUtils {
	
	public static final String default_tag = "M000";
	public static final String default_suffix = ".jpg";
	public static final String default_album_prefix = "http://y.gtimg.cn/music/photo_new/T002R";
	public static final String default_singer_image = "http://imgcache.qq.com/music/photo/singer_300/singerpic_0_null.jpg";
	public static final String default_singer_prefix = "http://y.gtimg.cn/music/photo_new/T001R";
	public static final String default_mv_prefix = "http://puui.qpic.cn/qqvideo_ori/0/";
	
//	mv图片格式
//	http://puui.qpic.cn/qqvideo_ori/0/m0027k01sls_228_128/0   小
//	http://puui.qpic.cn/qqvideo_ori/0/m0027k01sls_360_204/0   中
//	http://puui.qpic.cn/qqvideo_ori/0/m0027k01sls_496_280/0   大
	
	public static String getMvMidUrl(String mv_vid){
		return default_mv_prefix+mv_vid+"_360_204/0";
	}
	
	public static String getMvBigUrl(String mv_vid){
		return default_mv_prefix+mv_vid+"_496_280/0";
	}
	
	/**
	 * 获取专辑300图片
	 * @param singerMid 专辑mid
	 * @return
	 */
	public static String getAlbumImg300x300(String albumMid){
		if(albumMid==null){
    		return null;
    	}
    	return buildString(default_album_prefix,"300x300",
				default_tag,albumMid,default_suffix);
	}
	
	/**
	 * 获取专辑500图片
	 * @param singerMid 专辑mid
	 * @return
	 */
	public static String getAlbumImg500x500(String albumMid){
		if(albumMid==null){
    		return null;
    	}
    	return buildString(default_album_prefix,"500x500",
				default_tag,albumMid,default_suffix);
	}
	
	/**
	 * 获取歌手300图片
	 * @param singerMid 歌手mid
	 * @return
	 */
	public static String getSingerImg300x300(String singerMid){
		if(singerMid==null){
    		return null;
    	}
		return buildString(default_singer_prefix,"300x300",
				default_tag,singerMid,default_suffix);
	}
	
	/**
	 * 获取歌手500图片
	 * @param singerMid 歌手mid
	 * @return
	 */
	public static String getSingerImg500x500(String singerMid){
		if(singerMid==null){
    		return null;
    	}
		return buildString(default_singer_prefix,"500x500",
				default_tag,singerMid,default_suffix);
	}
	
	/**
	 * 拼接字符串
	 * @param strs
	 * @return
	 */
	private static String buildString(String... strs){
		StringBuilder result = new StringBuilder();
		for(String str : strs){
			result.append(str);
		}
		return result.toString();
	}
	
	public static void main(String[] args){
		String t = "http://puui.qpic.cn/qqvideo_ori/0/m0027k01sls_";
		System.out.println(t.length());
	}

}
