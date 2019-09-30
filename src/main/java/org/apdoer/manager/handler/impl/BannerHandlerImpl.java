package org.apdoer.manager.handler.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.catalina.security.SecurityUtil;
import org.apdoer.manager.check.BannerCheckService;
import org.apdoer.manager.constants.ManagerConstant;
import org.apdoer.manager.enums.CommonStatusEnum;
import org.apdoer.manager.enums.ExceptionCodeEnum;
import org.apdoer.manager.handler.BannerHandler;
import org.apdoer.manager.model.dto.PageBean;
import org.apdoer.manager.model.pojo.BannerPo;
import org.apdoer.manager.model.vo.BannerAddVo;
import org.apdoer.manager.model.vo.BannerUpdateVo;
import org.apdoer.manager.model.vo.BannerVo;
import org.apdoer.manager.model.vo.ResultVo;
import org.apdoer.manager.service.BannerService;
import org.apdoer.manager.spring.SecurityUtils;
import org.apdoer.manager.utils.ResultVoBuildUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

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
    @Transactional(rollbackFor = Exception.class)
    public ResultVo addBanner(BannerAddVo bannerAddVo) {
        ResultVo resultVo = bannerCheckService.checkAddBannerParam(bannerAddVo);
        if (resultVo == null || resultVo.getCode()!= ExceptionCodeEnum.SUCCESS.getCode()){
            return resultVo;
        }
        //获取当前用户
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        bannerService.createBanner(BannerPo.builder()
                .title(bannerAddVo.getTitle())
                .imgUrl(bannerAddVo.getImgUrl())
                .jumpUrl(bannerAddVo.getJumpUrl())
                .position(bannerAddVo.getPosition())
                .creator(username)
                .enabled(CommonStatusEnum.ENABLED.getCode())
                .type(bannerAddVo.getType())
                .language(bannerAddVo.getLanguage())
                .thumbnail(bannerAddVo.getThumbnail())
                .createTime(new Date()).build());
        // todo 清除缓存
        return ResultVoBuildUtils.buildSuccessResultVo();
    }

    @Override
    public ResultVo queryBannerList(PageBean<BannerVo> pageBean, BannerVo bannerVo) {
        ResultVo resultVo = bannerCheckService.checkQueryBannerParam(pageBean,bannerVo);
        if (resultVo == null || resultVo.getCode()!= ExceptionCodeEnum.SUCCESS.getCode()){
            return resultVo;
        }
        PageHelper.startPage(pageBean.getPageNum(),pageBean.getPageSize());
        List<BannerVo> bannerVos = bannerService.queryBannerList(bannerVo);
        PageInfo<BannerVo> pageInfo = new PageInfo<>(bannerVos);
        return ResultVoBuildUtils.buildSuccessResultVo(pageInfo);
    }

    @Override
    public ResultVo queryBannerDetail(Integer bannerId) {
        // todo
        return ResultVoBuildUtils.buildSuccessResultVo();
    }

    @Override
    public ResultVo updateBanner(BannerUpdateVo bannerUpdateVo) {
        ResultVo resultVo = bannerCheckService.checkUpdateBannerParam(bannerUpdateVo);
        if (resultVo == null || resultVo.getCode()!= ExceptionCodeEnum.SUCCESS.getCode()){
            return resultVo;
        }
        bannerService.updateBanner(BannerPo.builder()
                .id(bannerUpdateVo.getId())
                .build());
        // todo clean cache
        return ResultVoBuildUtils.buildSuccessResultVo();
    }

    @Override
    public ResultVo deleteBanner(Integer bannerId) {
        bannerService.deleteBanner(BannerPo.builder().id(bannerId).build());
        //todo 清缓存
        return ResultVoBuildUtils.buildSuccessResultVo();
    }

    @Override
    public ResultVo updateBannerStatus(Integer bannerId, Integer status) {
        if (status == null || status < ManagerConstant.STATUS_DISABLED || status > ManagerConstant.STATUS_DELETE){
            return ResultVoBuildUtils.buildResultVo(ExceptionCodeEnum.REQUEST_PARAM_INVALID.getCode(),ExceptionCodeEnum.REQUEST_PARAM_INVALID.getValue());
        }
        bannerService.updateBanner(BannerPo.builder().id(bannerId).enabled(status).build());
        // todo
        return ResultVoBuildUtils.buildSuccessResultVo();
    }
}
