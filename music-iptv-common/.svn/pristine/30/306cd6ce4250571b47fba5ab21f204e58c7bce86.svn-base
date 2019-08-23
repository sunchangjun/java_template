package hk.reco.music.iptv.common.dao;

import hk.reco.music.iptv.common.domain.IptvStatsUserDetail;

import java.util.Collection;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IptvStatsUserDetailDao{
	
	public void insert(IptvStatsUserDetail o);
	
	/**
	 * 批量插入数据
	 * @param details
	 */
	public void insertBatch(@Param("details") Collection<IptvStatsUserDetail> details);
	
	public void addLoginAndPlayTimes(@Param("user_id")String user_id, @Param("date")String date,
			@Param("login_times")int login_times, @Param("play_times")int play_times);
	
	/**
	 * 去重活跃用户
	 * @param from
	 * @param to
	 * @return
	 */
	public int findDistinctActiveUser(@Param("from")String from, @Param("to")String to);
	/**
	 * 去重点播用户
	 * @param from
	 * @param to
	 * @return
	 */
	public int findDistinctPlayUser(@Param("from")String from, @Param("to")String to);
}