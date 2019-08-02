package hk.reco.music.iptv.common.domain;


/**
 * 〈web端的用户信息〉
 * @author comelk
 * @create 2019/5/27
 */
public class WebSessionUser {

    private String userId;

    private String platform;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
