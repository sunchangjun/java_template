package hk.reco.music.iptv.common.ctrl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hk.reco.music.iptv.common.domain.IptvConsoleRole;
import hk.reco.music.iptv.common.service.IptvConsoleRoleService;
import hk.reco.music.iptv.common.utils.JsonResult;

@Controller
@RequestMapping("console/role")
public class IptvConsoleRoleController {
	
	@Autowired
	private IptvConsoleRoleService roleService;
	
	
	@RequestMapping("list")
	public String list() {
		return "role/list";
	}
	
	@RequestMapping("listData")
	@ResponseBody
	public Object listData() {
		return roleService.findAllRoleForList();
	}
	
	
	
	@RequestMapping("allRoles")
	@ResponseBody
	public Object allRoles(){
		return roleService.findAllRoles();
	}
	
	
	@RequestMapping("addRole")
	@ResponseBody
	public Object addRole(IptvConsoleRole role){
	   roleService.saveRole(role);
	   return new JsonResult();
	}

}
