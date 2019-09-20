package org.apdoer.manager.handler.impl;

import org.apdoer.manager.check.BannerCheckService;
import org.apdoer.manager.constants.ManagerConstant;
import org.apdoer.manager.enums.ExceptionCodeEnum;
import org.apdoer.manager.handler.BannerHandler;
import org.apdoer.manager.model.dto.PageBean;
import org.apdoer.manager.model.vo.BannerAddVo;
import org.apdoer.manager.model.vo.BannerUpdateVo;
import org.apdoer.manager.model.vo.BannerVo;
import org.apdoer.manager.model.vo.ResultVo;
import org.apdoer.manager.service.BannerService;
import org.apdoer.manager.utils.ResultVoBuildUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author apdoer
 * @version 1.0
 * @date 2018/9/20 14:20
 */
@Component
public class BannerHandlerImpl implements BannerHandler {
    private BannerService bannerService;
    private BannerCheckService bannerCheckService;


    @Autowired
    public void setBannerService(BannerService bannerService) {
        this.bannerService = bannerService;
    }
    @Autowired
    public void setBannerCheckService(BannerCheckService bannerCheckService) {
        this.bannerCheckService = bannerCheckService;
    }

    @Override
    public ResultVo addBanner(BannerAddVo bannerAddVo) {
        ResultVo resultVo = bannerCheckService.checkAddBannerParam(bannerAddVo);
        if (resultVo == null || resultVo.getCode()!= ExceptionCodeEnum.SUCCESS.getCode()){
            return resultVo;
        }
        // todo
        return null;
    }

    @Override
    public ResultVo queryBannerList(PageBean<BannerVo> pageBean, BannerVo bannerVo) {
        ResultVo resultVo = bannerCheckService.checkQueryBannerParam(pageBean,bannerVo);
        if (resultVo == null || resultVo.getCode()!= ExceptionCodeEnum.SUCCESS.getCode()){
            return resultVo;
        }
        // todo
        return null;
    }

    @Override
    public ResultVo queryBannerDetail(Integer bannerId) {
        // todo
        return null;
    }

    @Override
    public ResultVo updateBanner(BannerUpdateVo bannerUpdateVo) {
        ResultVo resultVo = bannerCheckService.checkUpdateBannerParam(bannerUpdateVo);
        if (resultVo == null || resultVo.getCode()!= ExceptionCodeEnum.SUCCESS.getCode()){
            return resultVo;
        }
        return null;
    }

    @Override
    public ResultVo deleteBanner(Integer bannerId) {
        //todo
        return null;
    }

    @Override
    public ResultVo updateBannerStatus(Integer bannerId, Integer status) {
        if (status == null || status < ManagerConstant.STATUS_DISABLED || status > ManagerConstant.STATUS_DELETE){
            return ResultVoBuildUtils.buildResultVo(ExceptionCodeEnum.REQUEST_PARAM_INVALID.getCode(),ExceptionCodeEnum.REQUEST_PARAM_INVALID.getValue());
        }
        // todo
        return null;
    }
}
