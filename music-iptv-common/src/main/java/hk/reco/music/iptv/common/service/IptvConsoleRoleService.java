package hk.reco.music.iptv.common.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hk.reco.music.iptv.common.dao.IptvConsoleRoleDao;
import hk.reco.music.iptv.common.domain.EasyUiBaseQuery;
import hk.reco.music.iptv.common.domain.EasyUiListResult;
import hk.reco.music.iptv.common.domain.IptvConsoleRole;
import hk.reco.music.iptv.common.utils.BaseDaoSupport;

@Service
public class IptvConsoleRoleService extends BaseDaoSupport{
	
	@Autowired
	private IptvConsoleRoleDao roleDao;

	public List<IptvConsoleRole> findAllRoles() {
		// TODO Auto-generated method stub
		return roleDao.findAllRoles();
	}

	public EasyUiListResult<IptvConsoleRole> findAllRoleForList() {
		// TODO Auto-generated method stub
		return this.getListPageSupportByWeb("hk.reco.music.iptv.common.dao.IptvConsoleRoleDao.findAllRoles", new EasyUiBaseQuery());
	}

	public void saveRole(IptvConsoleRole role) {
		// TODO Auto-generated method stub
		roleDao.saveRole(role);
	}

}
