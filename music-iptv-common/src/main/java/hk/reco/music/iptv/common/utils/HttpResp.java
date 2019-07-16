package hk.reco.music.iptv.common.utils;

/**
 * @ClassName HttpResp
 * @Description HttpUtils 请求相应类
 * @Author Alistair.Chow
 * @date 2018/8/29 13:52
 * @Version 1.0
 */
public class HttpResp {
    /**
     * 调用结果
     */
    private String errorMsg;

    /**
     * 响应码
     */
    private Integer statusCode;

    /**
     * 接口响应报文
     */
    private String resp;

    /**
     * 开始调用时间
     */
    private long start;

    /**
     * 接口调用耗时
     */
    private long cost;


    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getResp() {
        return resp;
    }

    public void setResp(String resp) {
        this.resp = resp;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append(resp);
        return builder.toString();
    }
}
