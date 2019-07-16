package hk.reco.music.iptv.common.ctrl.stb;

import hk.reco.music.iptv.common.exception.IptvError;
import io.swagger.annotations.ApiModelProperty;

public class RestResponse {

    @ApiModelProperty(value = "返回状态码", required = true, allowableValues = "0", dataType = "Integer")
    private Integer code = 0;
    @ApiModelProperty(value = "返回描述说明", required = true, allowableValues = "成功")
    private String msg = "调用成功";
    private Object data;
    private Integer total;

    public RestResponse() {
    }

    public RestResponse(IptvError error) {
        this.code = error.code;
        this.msg = error.msg;
    }

    public RestResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public RestResponse(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public RestResponse(int code, Object data,String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public static RestResponse e() {
        return new RestResponse();
    }

    public RestResponse(Object data) {
        this.data = data;
    }

    public static RestResponse e(IptvError error) {
        return new RestResponse(error);
    }

    public static RestResponse e(Object data) {
        return new RestResponse(data);
    }

    public static RestResponse e(int code, String msg) {
        return new RestResponse(code, msg);
    }

    public static RestResponse e(int code, Object data) {
        return new RestResponse(code, data);
    }

    public static RestResponse e(int code, Object data,String msg) {
        return new RestResponse(code, data,msg);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}