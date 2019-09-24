package org.apdoer.manager.controller;

import org.apdoer.manager.annotations.SystemControllerLog;
import org.apdoer.manager.handler.BannerHandler;
import org.apdoer.manager.model.dto.PageBean;
import org.apdoer.manager.model.vo.BannerAddVo;
import org.apdoer.manager.model.vo.BannerUpdateVo;
import org.apdoer.manager.model.vo.BannerVo;
import org.apdoer.manager.model.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author apdoer
 * @version 1.0
 * @date 2018/9/20 14:07
 */
@RestController
@RequestMapping("/banner")
public class BannerController {
    private BannerHandler bannerHandler;


    @Autowired
    public BannerController(BannerHandler bannerHandler) {
        this.bannerHandler = bannerHandler;
    }


    @PostMapping("/bannerList")
    @SystemControllerLog("添加banner")
    public ResultVo addBanner(@RequestBody @Validated BannerAddVo bannerAddVo){
        return bannerHandler.addBanner(bannerAddVo);
    }

    @GetMapping("/bannerList")
    @SystemControllerLog("查询banner列表")
    public ResultVo queryBannerList(PageBean<BannerVo> pageBean, BannerVo bannerVo){
        return bannerHandler.queryBannerList(pageBean,bannerVo);
    }

    @GetMapping("/banner/{bannerId}")
    @SystemControllerLog("查询具体的banner")
    public ResultVo queryBanner(@PathVariable Integer bannerId){
        return bannerHandler.queryBannerDetail(bannerId);
    }

    @PutMapping("/banner")
    @SystemControllerLog("更新banner信息")
    public ResultVo updateBanner(@RequestBody @Validated BannerUpdateVo bannerUpdateVo){
        return bannerHandler.updateBanner(bannerUpdateVo);
    }

    @DeleteMapping("/banner/{bannerId}")
    @SystemControllerLog("删除banner")
    public ResultVo deleteBanner(@PathVariable Integer bannerId){
        return bannerHandler.deleteBanner(bannerId);
    }

    @PutMapping("/banner/{bannerId}/{status}")
    public ResultVo updatebannerStatus(Integer bannerId,Integer status){
        return bannerHandler.updateBannerStatus(bannerId,status);
    }
}
