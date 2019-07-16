package hk.reco.music.iptv.common.dao;

import hk.reco.music.iptv.common.domain.IptvConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName IptvConfigDao
 * @Description TODO
 * @Author wangpq
 * @Date 2019/5/10 11:19
 * @Version 1.0
 */
@Mapper
public interface IptvConfigDao {

    /**
     * 根据KEY 获取数据
     *
     * @return hk.reco.music.iptv.common.domain.IptvConfig
     * @Author wangpq
     * @Param [key]
     * @Date 2019/5/10 11:21
     */
    IptvConfig findByKey(@Param("key") String key);


    /**
     * 根据keys 获取数据
     *
     * @return java.util.List<hk.reco.music.iptv.common.domain.IptvConfig>
     * @Author wangpq
     * @Param [keys]
     * @Date 2019/5/10 11:21
     */
    List<IptvConfig> findByKeys(@Param("keys") List<String> keys);
}
