package hk.reco.music.iptv.common.enums;

public enum IptvObjectEnum {
	song, // 歌曲
	mv, // mv
	singer, // 歌手
	diss, // 歌单
	auto_play_diss,//自动播放歌单(只能放mv)
	top, // 榜单
	link, // 链接
	column, // 栏目
	reco, // 推荐
	layout, // 布局
	theme, layout_singer, //
	layout_diss, //
	layout_top, // 榜单布局
	layout_custom, // 自定义
	func, // 功能
	func_history, // 最近播放
	func_collect, // 我的收藏
	func_personal, // 个人中心
	func_order, // 我的订购
	func_search, // 搜索
	func_theme, // 专辑
	func_free, // 免费区定义
	func_activity,//活动
	func_more_mv,//mv更多
	func_more_singer,//mv更多
	cate, // 分类
	other, // 可以理解为自建项目
	sub, // 专题
	// 以下web端专用
	func_collect_media, // 媒体收藏 包含mv和song
	func_collect_diss, // 歌单收藏
	func_play_all_theme, // 播放全部的主题（专题）媒体
	func_return_from_theme,// 从主题（专题）页返回
	func_auto_play,//主题页自动播放框
	theme_speical,//特殊主题包含下面的所有的特殊主题 需要配合resVer的模板来进行操作
	//主题子类型（列表模板主题）
	theme_list,
	//(福建移动活动主题)
	theme_fjyd,
	//带有自动播放的主题
	theme_auto_play;
	public static IptvObjectEnum getType(String name) {
		if (name == null)
			return null;
		IptvObjectEnum[] values = values();
		for (IptvObjectEnum v : values) {
			if (v.name().equals(name)) {
				return v;
			}
		}
		return null;
	}
}