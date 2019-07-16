package hk.reco.music.iptv.common.utils;

/**
 * 用于返回json数据
 * @author comelk
 * created in 2016年8月23日
 */
public class JsonResult {
	
	private boolean success = true;
	
	private String backmsg;
	
	private Integer code = 9999;
	
	private Object data;

	public boolean isSuccess() {
		return success;
	}
	

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}



	public void setSuccess(boolean success) {
		this.success = success;
	}


	public String getBackmsg() {
		return backmsg;
	}


	public void setBackmsg(String backmsg) {
		this.backmsg = backmsg;
	}


	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public JsonResult() {
		super();
	}

	public JsonResult(String  backmsg) {
		this.success = false;
		this.backmsg = backmsg;
	}


	public JsonResult(boolean success, String backmsg, Object data) {
		super();
		this.success = success;
		this.backmsg = backmsg;
		this.data = data;
	}
	
	
	public JsonResult(boolean success,Object data) {
		super();
		this.success = success;
		this.data = data;
	}


	@Override
	public String toString() {
		return JsonUtil.toJsonString(this);
	}
	
	
}
