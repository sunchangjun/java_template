package hk.reco.music.iptv.common.dao;

import hk.reco.music.iptv.common.domain.IptvStats;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface IptvStatsDao {

	public String findMaxDate();
	
	public void insert(IptvStats stats);
	
}
