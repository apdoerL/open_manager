package org.apdoer.manager.service;

import org.apdoer.manager.model.pojo.BannerPo;
import org.apdoer.manager.model.vo.BannerVo;

import java.util.List;

/**
 * @author apdoer
 * @version 1.0
 * @date 2018/9/20 14:21
 */
public interface BannerService {
    /**
     * 添加banner
     * @param build po
     */
    void createBanner(BannerPo build);

    /**
     * 查询banner列表
     * @param bannerVo vo
     * @return list
     */
    List<BannerVo> queryBannerList(BannerVo bannerVo);

    /**
     * 更新banner
     * @param build po
     */
    void updateBanner(BannerPo build);

    /**
     * 删除banner
     * @param build po
     */
    void deleteBanner(BannerPo build);

}
