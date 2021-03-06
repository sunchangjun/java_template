package hk.reco.music.iptv.common.service;

import hk.reco.music.iptv.common.ctrl.stb.RestResponse;

/**
 * 资源定价服务,待项目实现
 *
 * @author zhangsl
 * @date 2019年7月10日
 */
public interface IptvNoticeService {

    /**
     * 设置资源的免费状态
     *
     * @param rid
     * @param isfree true收费,false免费
     * @return true成功, false失败
     */
    void setFree(long rid, boolean isfree) throws Exception;

    /**
     * 未来资源下线通知
     *
     * @return hk.reco.music.iptv.common.ctrl.stb.RestResponse
     * @Author wangpq
     * @Param rid
     * @Param operation
     * @Date 2019/7/22 14:14
     */
    RestResponse newTVResUpOrDown(long rid, int operation);

    /**
     * 功能描述:
     * 〈媒资上下线通知〉
     *
     * @param rid rid
     * @return : void
     * @author : wangpq
     * @date : 2019/9/27 15:17
     */
    void setGlobalDisable(long rid);

    /**
     * 功能描述:
     * 〈用户收藏〉
     *
     * @param userId 用户ID
     * @param rid    rid
     * @param action 操作类型 1 收藏 0 取消收藏
     * @return : void
     * @author : wangpq
     * @date : 2019/10/9 14:43
     */
    void userCollect(String userId, long rid, int action);

    /**
     * 功能描述:
     * 〈用户播放记录〉
     *
     * @param userId   用户ID
     * @param rid      rid
     * @param duration 播放时长单位秒
     * @return : void
     * @author : wangpq
     * @date : 2019/10/9 14:43
     */
    void userHistory(String userId, long rid, int duration);

}
