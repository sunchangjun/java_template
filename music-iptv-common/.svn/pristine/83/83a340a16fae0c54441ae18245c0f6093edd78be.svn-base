package hk.reco.music.iptv.common.exception;
/**
 * iptv的业务异常定义
 * @author zhangsl
 * @date 2019年3月5日
 */
public enum IptvError {
	
	PAGEINDEX_EMPTY_ERROR(1001, "pageIndex不能为空!"),
	PAGEINDEX_ERROR(1002, "pageIndex从0开始编号!"),
	PAGESIZE_EMPTY_ERROR(1003, "pageSize不能为空!"),
	P_MISS(1004,"指定参数为空"),
	UPLOAD_IMAGE_FAIL(1005,"图片上传失败"),
    REPEATED_SUBMIT(1010,"重复提交"),
	TOP_LAYOUT_PARAM_ERROR(8001,"顶层布局的入参为0"),
	
	
	
	//终端错误消息占用8000以上
	SEARCH_TYPE_ERROR(8001,"song|mv|singer is allowed"),
	USER_COLLECT_ERROR(8002,"用户id或资源rid出错,收藏失败!"),
	USER_COLLECT_LIMIT_ERROR(8003,"用户收藏超过上限!"),
	USER_COLLECT_TYPE_ERROR(8004,"用户收藏类型只能为song|mv|diss|column"),
	RES_NOT_EXIST(8005,"资源不存在"),//资源下线
	RES_SINGER_OFFLINE(8006,"资源已下线"),//歌手下线导致资源下线
	
	SYSTEM_ERROR(9999, "系统错误,请稍后再试");

	public int code;
	public String msg;

	private IptvError(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}