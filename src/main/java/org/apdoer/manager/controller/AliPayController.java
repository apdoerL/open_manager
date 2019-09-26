package org.apdoer.manager.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apdoer.manager.annotations.SystemControllerLog;
import org.apdoer.manager.handler.AlipayHandler;
import org.apdoer.manager.model.vo.AlipayConfigVo;
import org.apdoer.manager.model.vo.ResultVo;
import org.apdoer.manager.model.vo.TradeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 支付宝 相关接口, todo 未完成
 * @author apdoer
 * @date 2018-12-31
 */
@Slf4j
@RestController
@RequestMapping("/pay")
public class AliPayController {
    private AlipayHandler alipayHandler;



    @Autowired
    public void setAlipayHandler(AlipayHandler alipayHandler) {
        this.alipayHandler = alipayHandler;
    }




    @GetMapping("/config")
    @SystemControllerLog("获取Alipay配置")
    public ResultVo queryPayConfig(){
        return alipayHandler.queryConfig();
    }


    @PutMapping("/config")
    @SystemControllerLog("配置alipay")
    public ResultVo deployPayConfig(@Validated @RequestBody AlipayConfigVo alipayConfig){
        return alipayHandler.update(alipayConfig);
    }


    @SystemControllerLog("支付宝PC网页支付")
    @ApiOperation(value = "PC网页支付")
    @PostMapping(value = "/aliPay/toPayAsPC")
    public ResultVo payByPc(@Validated @RequestBody TradeVo tradeVo){
        return alipayHandler.payByPc(tradeVo);
    }

    @SystemControllerLog("支付宝手机网页支付")
    @ApiOperation(value = "手机网页支付")
    @PostMapping(value = "/aliPay/toPayAsWeb")
    public ResultVo payByWeb(@Validated @RequestBody TradeVo tradeVo) {
        return alipayHandler.payByWeb(tradeVo);
    }

    @ApiIgnore
    @GetMapping("/fallback")
    @ApiOperation(value = "支付之后跳转的链接")
    @SystemControllerLog("回调页面")
    public ResultVo returnPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return alipayHandler.fallback(request,response);
    }

    @ApiIgnore
    @RequestMapping("/notify")
    @ApiOperation(value = "支付异步通知(要公网访问)，接收异步通知，检查通知内容app_id、out_trade_no、total_amount是否与请求中的一致，根据trade_status进行后续业务处理")
    @SystemControllerLog("支付异步通知")
    public ResultVo notify(HttpServletRequest request){
        return alipayHandler.notifyPay(request);
    }
}
