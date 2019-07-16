package hk.reco.music.iptv.common.domain;

import hk.reco.music.iptv.common.enums.IptvPlatform;

/**
 * 〈web端的用户信息〉
 * @author comelk
 * @create 2019/5/27
 */
public class WebSessionUser {

    private String userId;

    private IptvPlatform platform;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public IptvPlatform getPlatform() {
        return platform;
    }

    public void setPlatform(IptvPlatform platform) {
        this.platform = platform;
    }
}
