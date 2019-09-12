package hk.reco.music.iptv.common.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 〈iptv活动记录〉
 *
 * @author comelk
 * @create 2019/8/27
 */
public class IptvAcctivityRecord {

    private Integer id;

    private String user_id;

    private Integer award_id;

    private Integer activity_id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date create_time;
    //非本表字段
    private String award_name;
    private String phone_num;

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public IptvAcctivityRecord() {
    }

    public IptvAcctivityRecord(String user_id, Integer award_id, Integer activity_id) {
        this.user_id = user_id;
        this.award_id = award_id;
        this.activity_id = activity_id;
    }

    public Integer getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(Integer activity_id) {
        this.activity_id = activity_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Integer getAward_id() {
        return award_id;
    }

    public void setAward_id(Integer award_id) {
        this.award_id = award_id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getAward_name() {
        return award_name;
    }

    public void setAward_name(String award_name) {
        this.award_name = award_name;
    }
}
