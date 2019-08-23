package hk.reco.music.iptv.common.domain;

import java.util.Date;

/**
 * @author ：suncj
 * @date ：2019/8/16 9:44
 */
public class IptvWhiteListUser implements  java.io.Serializable{

    private int id;

    private String user_id;

    private String mac;

    private Date begin_time;

    private Date end_time;

    private Boolean test;

    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public  String  getUser_id(){
        return user_id;
    }
    public void setUser_id(String user_id){
        this.user_id=user_id;
    }
    public  String  getMac(){
        return mac;
    }
    public void setMac(String mac){
        this.mac=mac;
    }
    public  Date  getBegin_time(){
        return begin_time;
    }
    public void setBegin_time(Date begin_time){
        this.begin_time=begin_time;
    }
    public  Date  getEnd_time(){
        return begin_time;
    }
    public void setEnd_time(Date end_time){
        this.end_time=end_time;
    }
    public Boolean getTest(){
        return test;
    }
    public void setTest(Boolean test){
        this.test=test;
    }

}
