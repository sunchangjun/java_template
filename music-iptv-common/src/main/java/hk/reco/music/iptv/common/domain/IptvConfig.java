package hk.reco.music.iptv.common.domain;

/**
 * @ClassName IptvConfig
 * @Description TODO 配置文件
 * @Author wangpq
 * @Date 2019/5/10 11:16
 * @Version 1.0
 */
public class IptvConfig {

    /**
     *  主键
     */
    private Long id;

    /**
     *  key
     */
    private String key;

    /**
     *  value
     */
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
