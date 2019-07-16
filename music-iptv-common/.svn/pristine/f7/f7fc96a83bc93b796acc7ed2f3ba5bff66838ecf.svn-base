package hk.reco.music.iptv.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hk.reco.music.iptv.common.dao.IptvConsolePrivilegeDao;
import hk.reco.music.iptv.common.domain.EasyUiBaseQuery;
import hk.reco.music.iptv.common.domain.EasyUiListResult;
import hk.reco.music.iptv.common.domain.IptvConsolePrivilege;
import hk.reco.music.iptv.common.utils.BaseDaoSupport;

@Service
public class IptvConsolePrivilegeService extends BaseDaoSupport{
	
	@Autowired
	private IptvConsolePrivilegeDao privilegeDao;
	
	public List<IptvConsolePrivilege> findPrivilegeByRoleId(Integer roleId){
		List<IptvConsolePrivilege> topPrivileges = privilegeDao.findTopPrivileges();
		List<IptvConsolePrivilege> rolePrivileges =  privilegeDao.findPrivilegesByRoleId(roleId);
		boolean layout = false;
		for(int i=0;i<topPrivileges.size();i++){
			IptvConsolePrivilege top = topPrivileges.get(i);
			int j = 0;
			for (IptvConsolePrivilege p : rolePrivileges) {
				if(p.getParent_id()==null&&"layout".equals(p.getUrl())){
                    //栏目管理
					layout = true;
					continue;
				}
				if(p.getParent_id()==top.getId()){
					j++;
					top.getChilds().add(p);
				}
			}
			if(j==0&&!layout){
				topPrivileges.remove(top);
				i--;
			}
		}
		return topPrivileges;
	}
	

	public EasyUiListResult<IptvConsolePrivilege> findAllPrivilegeForList() {
		// TODO Auto-generated method stub
		return this.getListPageSupportByWeb("hk.reco.music.iptv.common.dao.IptvConsolePrivilegeDao.findAllPrivilegeForList", new EasyUiBaseQuery());
	}

}
