package org.apdoer.manager.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apdoer.manager.enums.ExceptionCodeEnum;
import org.apdoer.manager.exception.UpdateMysqlException;
import org.apdoer.manager.mapper.BannerMapper;
import org.apdoer.manager.model.pojo.BannerPo;
import org.apdoer.manager.model.vo.BannerVo;
import org.apdoer.manager.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author apdoer
 * @version 1.0
 * @date 2018/9/20 14:22
 */
@Service
@Slf4j
public class BannerServiceImpl implements BannerService {
    private BannerMapper bannerMapper;


    @Autowired
    @SuppressWarnings("all")
    public void setBannerMapper(BannerMapper bannerMapper) {
        this.bannerMapper = bannerMapper;
    }

    @Override
    public void createBanner(BannerPo build) {
        try {
            bannerMapper.insertSelective(build);
        }catch (Exception e){
            log.error("create banner error banner:{},reason:{}",build,e);
            throw new UpdateMysqlException(ExceptionCodeEnum.UPDATE_MYSQL_ERROR);
        }
    }

    @Override
    public List<BannerVo> queryBannerList(BannerVo bannerVo) {
        return null;
    }

    @Override
    public void updateBanner(BannerPo build) {
        try {
            bannerMapper.updateByPrimaryKeySelective(build);
        }catch (Exception e){
            log.error("update banner error banner:{},reason:{}",build,e);
            throw new UpdateMysqlException(ExceptionCodeEnum.UPDATE_MYSQL_ERROR);
        }
    }

    @Override
    public void deleteBanner(BannerPo build) {
        try {
            bannerMapper.updateByPrimaryKeySelective(build);
        }catch (Exception e){
            log.error("delete banner error banner:{},reason:{}",build,e);
            throw new UpdateMysqlException(ExceptionCodeEnum.UPDATE_MYSQL_ERROR);
        }
    }

}
