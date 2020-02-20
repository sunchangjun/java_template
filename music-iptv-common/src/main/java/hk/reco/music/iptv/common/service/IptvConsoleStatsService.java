package hk.reco.music.iptv.common.service;

import hk.reco.music.iptv.common.dao.IptvStatsUserDao;
import hk.reco.music.iptv.common.dao.IptvStatsUserDetailDao;
import hk.reco.music.iptv.common.domain.IptvStatsUser;
import hk.reco.music.iptv.common.stats.view.IptvStatsUserView;
import hk.reco.music.iptv.common.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 统计报表服务
 * @author zhangsl
 * @date 2019年2月22日
 */
@Service
public class IptvConsoleStatsService {

	@Autowired
	private IptvStatsUserDao iptvStatsUser;
	@Autowired
	private IptvStatsUserDetailDao iptvStatsUserDetailDao;
	
	public List<IptvStatsUserView> statsUserView(String from, String to) throws Exception {
		//1-创建title
		List<IptvStatsUserView> views = new ArrayList<IptvStatsUserView>();
		IptvStatsUserView head = new IptvStatsUserView();
		head.setDate("日期");
		head.setUser_visit_num("当日活跃用户/uv");
		head.setUser_new_num("当日新增用户");
		head.setUser_visit_total("用户总访问量/pv");
		head.setPlay_duration_total("点播总时长/秒");
		head.setPlay_times_num("点播总次数");
		head.setPlay_user_num("点播用户数");
		views.add(head);
		
		//2-创建中间数据
		List<IptvStatsUser> stat_users = this.iptvStatsUser.findStatsUser(from, to);
		int sum_play_duration_total = 0;
		int sum_play_times_num = 0;
		int sum_user_new_num = 0;
		int sum_user_visit_total = 0;
		for(IptvStatsUser stat_user:stat_users){
			sum_play_duration_total += stat_user.getPlay_duration_total();
			sum_play_times_num += stat_user.getPlay_times_num();
			sum_user_new_num += stat_user.getUser_new_num();
			sum_user_visit_total += stat_user.getUser_visit_total();
		}
		for(IptvStatsUser stat_user:stat_users){
			views.add(IptvStatsUserView.instance(stat_user));
		}
		//3-创建合计数据
		IptvStatsUserView sum = new IptvStatsUserView();
		sum.setDate("合计");
		sum.setPlay_duration_total(String.valueOf(sum_play_duration_total));
		sum.setPlay_times_num(String.valueOf(sum_play_times_num));
		sum.setPlay_user_num(String.valueOf(this.iptvStatsUserDetailDao.findDistinctPlayUser(from, to)));
		sum.setUser_new_num(String.valueOf(sum_user_new_num));
		sum.setUser_visit_num(String.valueOf(this.iptvStatsUserDetailDao.findDistinctActiveUser(from, to)));
		sum.setUser_visit_total(String.valueOf(sum_user_visit_total));
		views.add(sum);
		return views;
	}

}
