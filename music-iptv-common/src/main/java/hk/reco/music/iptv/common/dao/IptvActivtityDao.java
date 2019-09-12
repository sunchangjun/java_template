package hk.reco.music.iptv.common.dao;

import hk.reco.music.iptv.common.domain.EasyUiBaseQuery;
import hk.reco.music.iptv.common.domain.IptvAcctivityRecord;
import hk.reco.music.iptv.common.domain.IptvActivity;
import hk.reco.music.iptv.common.domain.IptvActivityAward;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IptvActivtityDao {

    /**
     * 查询当前激活的活动条数
     *
     * @return 当前激活活动列表
     */
    public List<IptvActivity> findActiveActivtity();

    /**
     * 根据用户id查询用户参与过的活动信息
     *
     * @param userId 用户id
     * @return 活动列表
     */
    public List<IptvActivity> findActivityListByUserId(String userId);


    /**
     * 根据用户id和活动id查询用户抽奖记录
     *
     * @param userId     用户id
     * @param activityId 活动id
     * @return 指定用户参与指定活动的抽奖记录
     */
    public List<IptvAcctivityRecord> findUserActivityRecord(@Param("userId") String userId, @Param("activityId") Integer activityId);

    /**
     * 根据活动id查询活动下的奖品信息
     *
     * @param activityId 活动id
     * @return 奖品列表
     */
    public List<IptvActivityAward> findActivityAwardById(Integer activityId);

    /**
     * 根据活动id查询活动的信息
     *
     * @param activityId 活动id
     * @return 活动信息
     */
    public IptvActivity findActivityById(Integer activityId);

    /**
     * 查询指定奖品、用户、活动、时间段内的活动次数
     *
     * @param start_time 时间段开始时间
     * @param end_time   时间段结束时间 格式
     * @param userId     活动id
     * @param award_id    奖品
     * @param activityId 活动id
     * @return 活动信息
     */
    public int findActivityTimesInDuration(@Param("userId") String userId, @Param("activityId") Integer activityId, @Param("award_id") Integer award_id,
                                           @Param("start_time") String start_time, @Param("end_time") String end_time);

    /**
     * 插入抽奖记录
     * @param record
     */
    public void insertNewRecord(IptvAcctivityRecord record);

    /**
     * 查询互动列表，平台使用
     * @return
     */
    public List<IptvActivity> consolefindActivityList();

    /**
     * 查询制定活动的参与数
     * @param activityId 活动id
     * @param userId  用户id
     * @param phone 电话号码
     * @param record 是否中奖
     * @return
     */
    public int consolefindActivityRecordListCount(@Param("activityId")Integer activityId, @Param("userId")String userId,
                                                  @Param("phone")String phone,@Param("record") Boolean record);
    /**
     * 查询制定活动的参与数据
     * @param activityId 活动id
     * @param userId  用户id
     * @param phone 电话号码
     * @param record 是否中奖
     * @param query 分页数据
     * @return
     */
    public List<IptvAcctivityRecord> consolefindActivityRecordList(@Param("activityId")Integer activityId, @Param("userId")String userId,
                                                            @Param("phone")String phone,@Param("record") Boolean record, @Param("query")EasyUiBaseQuery query);

    /**
     * 平台新增活动
     * @param activity
     */
    public  void consoleInsertNewActivity(IptvActivity activity);

    /**
     * 平台新增活动奖品
     * @param award 活动奖品实体
     */
    public void consoleInsertNewAward(IptvActivityAward award);

    /**
     * 更新奖品状态
     * @param awardId 奖品id
     */
    public void updateAwardStatus(Integer awardId);

    /**
     * 下线所有活动
     */
    public void consoleDelineAllActivity();

    /**
     * 上线指定活动
     * @param activityId
     */
    public void consoleOnlineActivity(Integer activityId);

    /**
     * 下线指定活动
     * @param activityId
     */
    public void consoleDelineActivity(Integer activityId);
    /**
     * 更新奖品信息
     * @param award
     */
    public void consoleUpdateAward(IptvActivityAward award);
}
