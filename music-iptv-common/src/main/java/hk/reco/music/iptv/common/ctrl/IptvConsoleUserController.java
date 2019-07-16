package hk.reco.music.iptv.common.ctrl;

import hk.reco.music.iptv.common.domain.IptvConsoleUser;
import hk.reco.music.iptv.common.service.IptvConsoleUserService;
import hk.reco.music.iptv.common.utils.JsonResult;
import hk.reco.music.iptv.common.utils.Md5Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("console/user")
public class IptvConsoleUserController {

	final private String DEFAULTPASSWORD = "123456";

	@Autowired
	private IptvConsoleUserService userService;

	@RequestMapping("list")
	public String list() {
		return "user/list";
	}

	@RequestMapping("listData")
	@ResponseBody
	public Object listData() {
		return userService.findAllUserForList();
	}

	@RequestMapping("addUser")
	@ResponseBody
	public Object addUser(IptvConsoleUser user) {
		if (StringUtils.isBlank(user.getPassword())) {
			user.setPassword(DEFAULTPASSWORD);
		}
		user.setPassword(Md5Utils.encode(user.getPassword()));
		userService.saveUser(user);
		return new JsonResult();
	}
	
	
	@RequestMapping("findUser")
	@ResponseBody
	public Object findUser(Integer id) {
		return userService.findUserById(id);
				
	}
	
	@RequestMapping("deleteUser")
	@ResponseBody
	public Object deleteUser(Integer id) {
	   userService.deleteUser(id);
	   return new JsonResult();
	}

	public static void main(String[] args) {

	}

}
