package hk.reco.music.iptv.common.ctrl;

import hk.reco.music.iptv.common.service.IptvConsoleResService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
/**
 * 管理后台的基类
 * @author DELL
 *
 */
public abstract class IptvBaseManageCtrl {
	
	@Autowired
	private IptvConsoleResService iptvConsoleResService;
	
	/**
	 * 获取测试环境节目的列表
	 * @param pageindex 从1开始计数
	 * @param pagesize  
	 * @return
	 */
	@RequestMapping("/get_program_list")
	public ModelAndView list_program(Integer poid, Integer pageindex, Integer pagesize) {
		//int offset = (pageindex == null)?0:(pageindex-1);
		//int size = (pagesize == null)?30:pageindex;
		return new ModelAndView("program_list", "programs", null);
	}
}
