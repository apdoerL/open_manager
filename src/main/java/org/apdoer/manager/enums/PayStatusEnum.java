package org.apdoer.manager.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum PayStatusEnum {

    FINISHED("TRADE_FINISHED", "交易完成"),

    SUCCESS("TRADE_SUCCESS", "已支付"),

    CREATED("WAIT_BUYER_PAY", "创建"),

    CLOSED("TRADE_CLOSED", "交易关闭"),


    ;
    private String status;
    private String desc;

}
