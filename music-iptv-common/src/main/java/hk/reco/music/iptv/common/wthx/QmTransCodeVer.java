package hk.reco.music.iptv.common.wthx;

import java.util.Date;

/**
 * @ClassName QmTransCodeVer
 * @Description TODO
 * @Author wangpq
 * @Date 2019/6/12 11:30
 * @Version 1.0
 */
public class QmTransCodeVer {

    private long id;

    private long res_id;

    private String dst_url;

    private Date create_time;

    private long config_id;

    private long parent_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRes_id() {
        return res_id;
    }

    public void setRes_id(long res_id) {
        this.res_id = res_id;
    }

    public String getDst_url() {
        return dst_url;
    }

    public void setDst_url(String dst_url) {
        this.dst_url = dst_url;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public long getConfig_id() {
        return config_id;
    }

    public void setConfig_id(long config_id) {
        this.config_id = config_id;
    }

    public long getParent_id() {
        return parent_id;
    }

    public void setParent_id(long parent_id) {
        this.parent_id = parent_id;
    }
}
