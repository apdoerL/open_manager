package org.apdoer.manager.handler;

import org.apdoer.manager.model.vo.AlipayConfigVo;
import org.apdoer.manager.model.vo.ResultVo;
import org.apdoer.manager.model.vo.TradeVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author apdoer
 * @version 1.0
 * @date 2019/9/26 10:47
 */
public interface AlipayHandler {
    /**
     * 获取alipay 配置
     * @return
     */
    ResultVo queryConfig();

    /**
     * 配置alipay
     * @param alipayConfig
     * @return
     */
    ResultVo update(AlipayConfigVo alipayConfig);

    /**
     * 网页支付
     * @param tradeVo
     * @return
     */
    ResultVo payByPc(TradeVo tradeVo);

    /**
     * 手机网页支付
     * @param tradeVo
     * @return
     */
    ResultVo payByWeb(TradeVo tradeVo);

    /**
     * 支付回调
     * @param request
     * @param response
     * @return
     */
    ResultVo fallback(HttpServletRequest request, HttpServletResponse response);

    /**
     * 支付异步通知
     * @param request
     * @return
     */
    ResultVo notifyPay(HttpServletRequest request);
}
