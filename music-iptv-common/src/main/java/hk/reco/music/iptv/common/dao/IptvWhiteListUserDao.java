package hk.reco.music.iptv.common.dao;

import hk.reco.music.iptv.common.domain.IptvWhiteListUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author ：suncj
 * @date ：2019/8/16 10:06
 */
@Mapper
public interface IptvWhiteListUserDao {

    @Select(value="select *  from iptv_whitelist_user where end_time>now() and test=#{test}")
    List<IptvWhiteListUser>  findValidUser(@Param("test") Boolean test);

    @Select(value="select *  from iptv_whitelist_user")
    List<IptvWhiteListUser>  findAllUser();

    @Select(value="select user_id  from iptv_whitelist_user where end_time>now() and test=#{test}")
    List<String>  findValidUserId(@Param("test") Boolean test);

    @Update(value="update iptv_whitelist_user set user_id=#{user_id},mac=#{mac},begin_time=#{begin_time},end_time=#{end_time},test=#{test} where id=#{id}")
    void updateWhiteListUser(IptvWhiteListUser iptvWhiteListUser);

    @Insert(value="INSERT INTO iptv_whitelist_user(user_id,mac,begin_time,end_time,test) values(#{user_id},#{mac},#{begin_time},#{end_time},#{test})")
    void insetIptvWhiteListUser(IptvWhiteListUser iptvWhiteListUser);
}
