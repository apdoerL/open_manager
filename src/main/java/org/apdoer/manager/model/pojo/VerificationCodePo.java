package org.apdoer.manager.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author apdoer
 * @date 2018-12-26
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "verification_code")
public class VerificationCodePo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    /**
     * 使用场景，自己定义
     */
    private String scenes;

    /**
     * true 为有效，false 为无效，验证时状态+时间+具体的邮箱或者手机号
     */
    private Boolean status = true;


    /**
     * 类型 ：phone 和 email
     */
    private String type;

    /**
     * 具体的phone与email
     */
    private String value;

    /**
     * 创建日期
     */
    private Timestamp createTime;

}
