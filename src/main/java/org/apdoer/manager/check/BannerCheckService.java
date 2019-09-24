package org.apdoer.manager.check;

import org.apdoer.manager.model.dto.PageBean;
import org.apdoer.manager.model.vo.BannerAddVo;
import org.apdoer.manager.model.vo.BannerUpdateVo;
import org.apdoer.manager.model.vo.BannerVo;
import org.apdoer.manager.model.vo.ResultVo;

/**
 * @author apdoer
 * @version 1.0
 * @date 2018/9/20 14:23
 */
public interface BannerCheckService {
    /**
     * 校验更新banner参数
     * @param bannerUpdateVo
     * @return
     */
    ResultVo checkUpdateBannerParam(BannerUpdateVo bannerUpdateVo);

    /**
     * 校验查询banner列表参数
     * @param pageBean
     * @param bannerVo
     * @return
     */
    ResultVo checkQueryBannerParam(PageBean<BannerVo> pageBean, BannerVo bannerVo);

    /**
     * 校验添加banner参数
     * @param bannerAddVo
     * @return
     */
    ResultVo checkAddBannerParam(BannerAddVo bannerAddVo);
}
