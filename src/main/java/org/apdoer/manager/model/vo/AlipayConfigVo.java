package org.apdoer.manager.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author apdoer
 * @version 1.0
 * @date 2019/9/25 19:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlipayConfigVo {

    @NotBlank
    private String appID;

    @NotBlank
    private String privateKey;

    @NotBlank
    private String publicKey;

    private String signType="RSA2";

    private String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    private String charset= "utf-8";

    @NotBlank
    private String notifyUrl;

    @NotBlank
    private String returnUrl;

    private String format="JSON";

    @NotBlank
    private String sysServiceProviderId;
}
