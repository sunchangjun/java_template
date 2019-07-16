package hk.reco.music.iptv.common.wthx;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName WthxMedia
 * @Description TODO 万泰资源
 * @Author wangpq
 * @Date 2019/5/17 11:44
 * @Version 1.0
 */
public class WthxMedia implements Serializable {

    private static final long serialVersionUID = -4480720942462496667L;

    /**
     *  资源ID
     */
    private Long res_id;

    /**
     * music id
     */
    private Long media_id;

    /**
     * 资源类别
     */
    private int res_type;

    /**
     * 资源名称
     */
    private String media_name;

    /**
     * 歌手ID
     */
    private Long singer_id;

    /**
     * 歌手名称
     */
    private String singer_name;

    /**
     * 发布时间
     */
    private String public_time;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 2通过,1初始状态,0审核中,-1不通过,-2下线
     */
    private int status;

    /**
     * 图片地址
     */
    private String img_url;

    /**
     * 资源地址
     */
    private String file_url;

    /**
     * 资源标签
     */
    private String wthx_tags;

    /**
     * 资源歌词
     */
    private String lyric_url;

    /**
     * 转码地址
     */
    private String ts_url;

    /**
     *
     */
    private String mp4_url;

    /**
     * 清晰度
     */
    private String definition;

    public Long getRes_id() {
        return res_id;
    }

    public void setRes_id(Long res_id) {
        this.res_id = res_id;
    }

    public Long getMedia_id() {
        return media_id;
    }

    public void setMedia_id(Long media_id) {
        this.media_id = media_id;
    }

    public int getRes_type() {
        return res_type;
    }

    public void setRes_type(int res_type) {
        this.res_type = res_type;
    }

    public String getMedia_name() {
        return media_name;
    }

    public void setMedia_name(String media_name) {
        this.media_name = media_name;
    }

    public Long getSinger_id() {
        return singer_id;
    }

    public void setSinger_id(Long singer_id) {
        this.singer_id = singer_id;
    }

    public String getSinger_name() {
        return singer_name;
    }

    public void setSinger_name(String singer_name) {
        this.singer_name = singer_name;
    }

    public String getPublic_time() {
        return public_time;
    }

    public void setPublic_time(String public_time) {
        this.public_time = public_time;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    public String getWthx_tags() {
        return wthx_tags;
    }

    public void setWthx_tags(String wthx_tags) {
        this.wthx_tags = wthx_tags;
    }

    public String getLyric_url() {
        return lyric_url;
    }

    public void setLyric_url(String lyric_url) {
        this.lyric_url = lyric_url;
    }

    public String getTs_url() {
        return ts_url;
    }

    public void setTs_url(String ts_url) {
        this.ts_url = ts_url;
    }

    public String getMp4_url() {
        return mp4_url;
    }

    public void setMp4_url(String mp4_url) {
        this.mp4_url = mp4_url;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}
