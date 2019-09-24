package org.apdoer.manager.check.impl;

import org.apdoer.manager.check.BannerCheckService;
import org.apdoer.manager.model.dto.PageBean;
import org.apdoer.manager.model.vo.BannerAddVo;
import org.apdoer.manager.model.vo.BannerUpdateVo;
import org.apdoer.manager.model.vo.BannerVo;
import org.apdoer.manager.model.vo.ResultVo;
import org.apdoer.manager.utils.ResultVoBuildUtils;
import org.springframework.stereotype.Component;

/**
 * @author apdoer
 * @version 1.0
 * @date 2018/9/20 14:23
 */
@Component
public class BannerCheckServiceImpl implements BannerCheckService {
    @Override
    public ResultVo checkUpdateBannerParam(BannerUpdateVo bannerUpdateVo) {
        // todo
        return ResultVoBuildUtils.buildSuccessResultVo();
    }

    @Override
    public ResultVo checkQueryBannerParam(PageBean<BannerVo> pageBean, BannerVo bannerVo) {
        // todo
        return ResultVoBuildUtils.buildSuccessResultVo();
    }

    @Override
    public ResultVo checkAddBannerParam(BannerAddVo bannerAddVo) {
        // todo
        return ResultVoBuildUtils.buildSuccessResultVo();
    }
}
