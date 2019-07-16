package hk.reco.music.iptv.common.utils;

import javax.servlet.http.HttpServletRequest;

import hk.reco.music.iptv.common.domain.IptvPage;
import hk.reco.music.iptv.common.exception.IptvBusinessException;
import hk.reco.music.iptv.common.exception.IptvError;

/**
 * 杂项工具
 * @author zhangsl
 * @date 2019年3月25日
 */
public class IptvMsicUtils {

	/**
	 * 解析页index和页容量
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public static IptvPage parsePage(Integer pageIndex, Integer pageSize) throws IptvBusinessException{
		if(pageIndex == null){
			throw new IptvBusinessException(IptvError.PAGEINDEX_EMPTY_ERROR);
		}else if(pageSize == null || pageSize < 1){
			throw new IptvBusinessException(IptvError.PAGESIZE_EMPTY_ERROR);
		}else if(pageIndex < 0){
			throw new IptvBusinessException(IptvError.PAGEINDEX_EMPTY_ERROR);
		}
		return new IptvPage(pageIndex*pageSize, pageSize);
	}
	
	public static boolean parseTest(Boolean test){
		return (test==null)?false:test;
	}
	
	/**
	 * 判断是否是本地在访问(开发阶段使用,一便在内网上传图片)
	 * @param req
	 * @return
	 */
	public static boolean isLocalHost(HttpServletRequest req){
		String remote = req.getRemoteHost();
		if(remote.equals("0:0:0:0:0:0:0:1") || remote.equals("127.0.0.1") || remote.startsWith("192.168.3.")){
			return true;
		}else{
			return false;
		}
	}
	
}
