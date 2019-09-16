package org.apdoer.manager.model.pojo;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 支付宝配置类
 * @author apdoer
 * @date 2018-12-31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name = "core_param_alipay")
public class AlipayPo implements Serializable {
    private static final long serialVersionUID = -7692913268429666029L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 应用ID,APPID，收款账号既是APPID对应支付宝账号
     */
    private String appId;

    /**
     * 商户私钥，您的PKCS8格式RSA2私钥
     */
    private String privateKey;

    /**
     * 支付宝公钥
     */
    private String publicKey;

    /**
     * 签名方式，固定格式
     */
    private String signType="RSA2";

    /**
     * 支付宝开放安全地址，一般不会变
     */
    private String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    /**
     * 编码，固定格式
     */
    private String charset= "utf-8";

    /**
     * 异步通知地址
     */
    private String notifyUrl;

    /**
     * 订单完成后返回的页面
     */
    private String returnUrl;

    /**
     * 类型，固定格式
     */
    private String format="JSON";

    /**
     * 商户号
     */
    private String sysServiceProviderId;

}
