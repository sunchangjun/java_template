package hk.reco.music.iptv.common.dao;

import hk.reco.music.iptv.common.domain.IptvWhiteListUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ：suncj
 * @date ：2019/8/16 10:06
 */
@Mapper
public interface IptvWhiteListUserDao {

    @Select(value="select *  from iptv_whitelist_user where end_time>now() and test=#{test}")
    List<IptvWhiteListUser>  findValidUser(@Param("test") Boolean test);

    @Select(value="select user_id  from iptv_whitelist_user where end_time>now() and test=#{test}")
    List<String>  findValidUserId(@Param("test") Boolean test);


}
