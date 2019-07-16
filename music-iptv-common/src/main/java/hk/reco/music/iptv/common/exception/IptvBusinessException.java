package hk.reco.music.iptv.common.exception;
/**
 * iptv业务异常
 * @author zhangsl
 * @date 2019年2月21日
 */
public class IptvBusinessException extends Exception{
	private IptvError error;
	private static final long serialVersionUID = -7656000163144863803L;

	public IptvBusinessException(IptvError error) {
		super();
		this.error = error;
	}

	public int getCode(){
		return this.error.code;
	}
	
	public String getMsg(){
		return this.error.msg;
	}
	
	public IptvError getError(){
		return this.error;
	}
}
