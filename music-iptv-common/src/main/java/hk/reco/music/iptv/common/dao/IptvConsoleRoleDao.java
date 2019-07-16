package hk.reco.music.iptv.common.dao;

import java.util.List;

import hk.reco.music.iptv.common.domain.IptvConsoleRole;

public interface IptvConsoleRoleDao {

	public List<IptvConsoleRole> findAllRoles();

	public void saveRole(IptvConsoleRole role);

}
