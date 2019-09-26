package org.apdoer.manager.handler.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import lombok.extern.slf4j.Slf4j;
import org.apdoer.manager.enums.ExceptionCodeEnum;
import org.apdoer.manager.enums.PayStatusEnum;
import org.apdoer.manager.exception.BadRequestException;
import org.apdoer.manager.handler.AlipayHandler;
import org.apdoer.manager.model.pojo.AlipayPo;
import org.apdoer.manager.model.vo.AlipayConfigVo;
import org.apdoer.manager.model.vo.ResultVo;
import org.apdoer.manager.model.vo.TradeVo;
import org.apdoer.manager.service.AlipayService;
import org.apdoer.manager.utils.AlipayUtils;
import org.apdoer.manager.utils.BeanUtils;
import org.apdoer.manager.utils.ResultVoBuildUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author apdoer
 * @version 1.0
 * @date 2019/9/26 10:47
 */
@Service
@Slf4j
public class AlipayHandlerImpl implements AlipayHandler {
    private AlipayService alipayService;
    private AlipayUtils alipayUtils;



    @Autowired
    public void setAlipayService(AlipayService alipayService) {
        this.alipayService = alipayService;
    }
    @Autowired
    public void setAlipayUtils(AlipayUtils alipayUtils) {
        this.alipayUtils = alipayUtils;
    }


    @Override
    public ResultVo queryConfig() {
        return ResultVoBuildUtils.buildSuccessResultVo(alipayService.queryConfig());
    }

    @Override
    public ResultVo update(AlipayConfigVo alipayConfig) {
        AlipayPo alipayPo = BeanUtils.convert(alipayConfig, AlipayPo.class);
        alipayPo.setId(1L);
        alipayService.updateConfig(alipayPo);
        return ResultVoBuildUtils.buildSuccessResultVo();
    }

    @Override
    public ResultVo payByPc(TradeVo tradeVo) {
        AlipayPo alipayPo = alipayService.queryConfig();
        tradeVo.setOutTradeNo(alipayUtils.getOrderCode());
        if (alipayPo.getId() == null){
            return ResultVoBuildUtils.buildResultVo(ExceptionCodeEnum.REQUEST_PARAM_INVALID.getCode(),ExceptionCodeEnum.REQUEST_PARAM_INVALID.getValue());
        }
        AlipayClient alipayClient = new DefaultAlipayClient(alipayPo.getGatewayUrl(), alipayPo.getAppId(), alipayPo.getPrivateKey(), alipayPo.getFormat(), alipayPo.getCharset(), alipayPo.getPublicKey(), alipayPo.getSignType());

        // 创建API对应的request(电脑网页版)
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();

        request.setReturnUrl(alipayPo.getReturnUrl());
        request.setNotifyUrl(alipayPo.getNotifyUrl());
        //填充业务参数
        request.setBizContent(buildBizContent(alipayPo,tradeVo));
        try {
            return ResultVoBuildUtils.buildSuccessResultVo(alipayClient.pageExecute(request, "GET").getBody());
        } catch (AlipayApiException e) {
            log.error("");
            return ResultVoBuildUtils.buildFaildResultVo("");
        }
    }

    private String buildBizContent(AlipayPo alipayPo, TradeVo tradeVo) {
        return "{" +
                "    \"out_trade_no\":\""+tradeVo.getOutTradeNo()+"\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":"+tradeVo.getTotalAmount()+"," +
                "    \"subject\":\""+tradeVo.getSubject()+"\"," +
                "    \"body\":\""+tradeVo.getBody()+"\"," +
                "    \"extend_params\":{" +
                "    \"sys_service_provider_id\":\""+alipayPo.getSysServiceProviderId()+"\"" +
                "    }"+
                "  }";
    }

    @Override
    public ResultVo payByWeb(TradeVo tradeVo) {
        AlipayPo alipayPo = alipayService.queryConfig();
        tradeVo.setOutTradeNo(alipayUtils.getOrderCode());
        if(alipayPo.getId() == null){
            throw new BadRequestException("请先添加相应配置，再操作");
        }
        AlipayClient alipayClient = new DefaultAlipayClient(alipayPo.getGatewayUrl(), alipayPo.getAppId(), alipayPo.getPrivateKey(), alipayPo.getFormat(), alipayPo.getCharset(), alipayPo.getPublicKey(), alipayPo.getSignType());

        double money = Double.parseDouble(tradeVo.getTotalAmount());
        if(money <= 0 || money >= 5000){
            throw new BadRequestException("测试金额过大");
        }

        AlipayTradeWapPayRequest request = buildWapRequest(alipayPo,tradeVo);

        try {
            return ResultVoBuildUtils.buildSuccessResultVo(alipayClient.pageExecute(request, "GET").getBody());
        } catch (AlipayApiException e) {
            log.error("");
            return ResultVoBuildUtils.buildFaildResultVo("");
        }
    }

    private AlipayTradeWapPayRequest buildWapRequest(AlipayPo alipayPo, TradeVo tradeVo) {
        AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
        request.setReturnUrl(alipayPo.getReturnUrl());
        request.setNotifyUrl(alipayPo.getNotifyUrl());
        request.setBizContent(buildBizContent(alipayPo,tradeVo));
        return request;
    }

    @Override
    public ResultVo fallback(HttpServletRequest request, HttpServletResponse response) {
        AlipayPo alipayPo = alipayService.queryConfig();
        response.setContentType("text/html;charset=" + alipayPo.getCharset());
        //内容验签，防止黑客篡改参数
        if(alipayUtils.rsaCheck(request,alipayPo)){
            //商户订单号
            //支付宝交易号
            String tradeNo = null,outTradeNo = null;

            outTradeNo = new String(request.getParameter("out_trade_no").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            tradeNo = new String(request.getParameter("trade_no").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            log.info("商户订单号"+outTradeNo+"  "+"第三方交易号"+tradeNo);

            return ResultVoBuildUtils.buildSuccessResultVo("payment successful");
        }else{
            throw new BadRequestException(ExceptionCodeEnum.REQUEST_PARAM_INVALID);
        }
    }

    @Override
    public ResultVo notifyPay(HttpServletRequest request) {
        AlipayPo alipayPo = alipayService.queryConfig();
        Map<String, String[]> parameterMap = request.getParameterMap();
        StringBuilder notifyBuild = new StringBuilder("/****************************** pay notify ******************************/\n");
        parameterMap.forEach((key, value) -> notifyBuild.append(key + "=" + value[0] + "\n") );
        //内容验签，防止黑客篡改参数
        if (alipayUtils.rsaCheck(request,alipayPo)) {
            //交易状态
            String tradeStatus = new String(request.getParameter("trade_status").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            // 商户订单号
            String outTradeNo = new String(request.getParameter("out_trade_no").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            //支付宝交易号
            String tradeNo = new String(request.getParameter("trade_no").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            //付款金额
            String totalAmount = new String(request.getParameter("total_amount").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            //验证
            if(tradeStatus.equals(PayStatusEnum.SUCCESS.getStatus())||tradeStatus.equals(PayStatusEnum.FINISHED.getStatus())){

                 // 验证通过后应该根据业务需要处理订单
            }
            return ResultVoBuildUtils.buildSuccessResultVo();
        }
        throw new BadRequestException(ExceptionCodeEnum.REQUEST_PARAM_INVALID);
    }
}
