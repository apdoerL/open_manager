package org.apdoer.manager.handler;

import org.apdoer.manager.model.dto.PageBean;
import org.apdoer.manager.model.vo.BannerAddVo;
import org.apdoer.manager.model.vo.BannerUpdateVo;
import org.apdoer.manager.model.vo.BannerVo;
import org.apdoer.manager.model.vo.ResultVo;

/**
 * @author apdoer
 * @version 1.0
 * @date 2018/9/20 14:19
 */
public interface BannerHandler {
    /**
     * 新增banner
     * @param bannerAddVo
     * @return
     */
    ResultVo addBanner(BannerAddVo bannerAddVo);

    /**
     * 查询banner列表
     * @param pageBean
     * @param bannerVo
     * @return
     */
    ResultVo queryBannerList(PageBean<BannerVo> pageBean, BannerVo bannerVo);

    /**
     * 查询banner详情
     * @param bannerId
     * @return
     */
    ResultVo queryBannerDetail(Integer bannerId);

    /**
     * 更新banner信息
     * @param bannerUpdateVo
     * @return
     */
    ResultVo updateBanner(BannerUpdateVo bannerUpdateVo);

    /**
     * 删除banner
     * @param bannerId
     * @return
     */
    ResultVo deleteBanner(Integer bannerId);

    /**
     * 更新banner状态
     * @param bannerId
     * @param status
     * @return
     */
    ResultVo updateBannerStatus(Integer bannerId, Integer status);
}
