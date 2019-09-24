package org.apdoer.manager.model.pojo;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 邮件配置
 * @author apdoer
 * @date 2018-12-26
 */
@Data
@Table(name = "biz_email_config")
public class EmailPo implements Serializable {

    private static final long serialVersionUID = -2325573564598568255L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String host;

    private String port;

    private String fromUser;

    private String pass;

    private String receiveUser;
}
