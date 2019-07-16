package hk.reco.music.iptv.common.service;

import hk.reco.music.iptv.common.dao.IptvUserDao;
import hk.reco.music.iptv.common.domain.IptvUser;
import hk.reco.music.iptv.common.exception.NotImplException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
/**
 * iptv终端盒子服务
 * @author zhangsl
 * @date 2019年2月21日
 */
@Service
public class IptvUserService{

	@Autowired
	private IptvUserDao iptvUserDao;
	
	public IptvUser findById(int id){
		return this.iptvUserDao.findById(id);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public String insert(IptvUser user){
		this.iptvUserDao.insert(user);
		return user.getUser_id();
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public int update(IptvUser user){
		//return this.iptvUserDao.update(user);
		throw new NotImplException("not impl");
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public int delete(int id){
		throw new NotImplException("not impl");
	}
}
