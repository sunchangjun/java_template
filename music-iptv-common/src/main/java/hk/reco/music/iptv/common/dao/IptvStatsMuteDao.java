package hk.reco.music.iptv.common.dao;

import hk.reco.music.iptv.common.domain.IptvStatsMute;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface IptvStatsMuteDao {

	public void insert(IptvStatsMute mute);
	
	
}
