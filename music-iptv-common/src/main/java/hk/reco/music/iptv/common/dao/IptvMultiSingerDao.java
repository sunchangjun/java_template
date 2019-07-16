package hk.reco.music.iptv.common.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IptvMultiSingerDao {
	
	public void insert(@Param("singer_rid")Long singer_rid, @Param("child_rid")Long child_rid);
	
}