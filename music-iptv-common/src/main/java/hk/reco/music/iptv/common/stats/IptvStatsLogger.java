package hk.reco.music.iptv.common.stats;

import hk.reco.music.iptv.common.enums.IptvObjectEnum;

/**
 * 日志行产生器
 * @author zhangsl
 * @date 2019年5月28日
 */
public class IptvStatsLogger {
	
	//创建统计数据
	public static String data(IptvStatsAction action, String ip, String mac, String userId, Long prid, Long rid, Long extrid, 
			String pinyin, IptvObjectEnum type, Integer duration, String method, String platform, long now, boolean test, String path) throws Exception{
		if(mac!=null && mac.indexOf(":")!=-1){
			mac = mac.replaceAll(":", "-");
		}
		if(action == null){
			throw new Exception("action不能为空!");
		}
		StringBuffer sb = new StringBuffer(action.name());
		colonAppend(sb, now);//时间
		colonAppend(sb, ip);//ip地址
		colonAppend(sb, mac);//用户mac
		colonAppend(sb, userId);//用户uid
		colonAppend(sb, prid);//父场景rid
		colonAppend(sb, rid);//资源rid
		colonAppend(sb, extrid);//扩展rid
		colonAppend(sb, null == type ? type : type.name());//参数--类型
		colonAppend(sb, pinyin);//参数--拼音
		colonAppend(sb, duration);//参数--时长
		colonAppend(sb, method);//方法标识
		colonAppend(sb, platform);//平台标识
		colonAppend(sb, test);//test|prod标识
		colonAppend(sb, path);//接口访问全路径
		colonAppend(sb, "v2");//日志接口版本
		return sb.toString();
	}

    public static String order(IptvStatsAction action, String ip, String mac, String userId, Long prid, Long rid,
                               String order_id,String product_id, String product_price, Integer result_code,String other_desc,
                             String method, String platform, long now, boolean test, String path) throws Exception{
        if(mac!=null && mac.indexOf(":")!=-1){
            mac = mac.replaceAll(":", "-");
        }
        if(action == null){
            throw new Exception("action不能为空!");
        }
        StringBuffer sb = new StringBuffer(action.name());
        colonAppend(sb, now);//时间
        colonAppend(sb, ip);//ip地址
        colonAppend(sb, mac);//用户mac
        colonAppend(sb, userId);//用户uid
        colonAppend(sb, prid);//父场景rid
        colonAppend(sb, rid);//资源rid
        colonAppend(sb, order_id);//订单编号
        colonAppend(sb, product_id);//产品编号
        colonAppend(sb, product_price);//产品价格
        colonAppend(sb, result_code);//返回code码(成功/错误码,统计失败原因)
        colonAppend(sb, other_desc);//其他补充
        colonAppend(sb, method);//方法标识
        colonAppend(sb, platform);//平台标识
        colonAppend(sb, test);//test|prod标识
        colonAppend(sb, path);//接口访问全路径
        colonAppend(sb, "v2");//日志接口版本
        return sb.toString();
    }
	
	/**
	 * 对象以','连接
	 * @param sb
	 * @param o
	 */
	private static void colonAppend(StringBuffer sb, Object o){
		sb.append(',');
		sb.append(o);
	}

}
