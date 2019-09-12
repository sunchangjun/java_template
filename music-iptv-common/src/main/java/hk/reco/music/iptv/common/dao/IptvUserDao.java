package hk.reco.music.iptv.common.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import hk.reco.music.iptv.common.domain.IptvUser;

@Mapper
public interface IptvUserDao extends IptvGenericDao<IptvUser>{
	
	@Override
	public IptvUser findById(@Param("id")int id);
	
	@Override
	public void insert(IptvUser user);
	
	public void ignoreInsertWhenExist(IptvUser user);
	
	@Override
	public int update(IptvUser user);
	
	@Override
	public int delete(@Param("id")int id);
	
	/**
	 * 统计当天创建的用户
	 * @param date
	 * @return
	 */
	public int countUserByDate(@Param("date")String date);
	
	public IptvUser findByUserId(@Param("user_id")String user_id,@Param("test")int test);
}