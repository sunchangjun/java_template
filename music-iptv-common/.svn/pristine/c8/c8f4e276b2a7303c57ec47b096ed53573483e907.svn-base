package hk.reco.music.iptv.common.dao;

import hk.reco.music.iptv.common.domain.IptvStatsUser;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface IptvStatsUserDao {

	public void insert(IptvStatsUser stats_user);
	
	public List<IptvStatsUser> findStatsUser(@Param("from")String from, @Param("to")String to);
	
	
}
