package org.apdoer.manager.model.pojo;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 后台管理用户
 * @author apdoer
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="biz_user")
public class BizUserPo implements Serializable {
    private static final long serialVersionUID = -8933797925302809526L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String avatar;

    private String email;

    private String phone;

    private Boolean enabled;

    private String password;

    private Timestamp createTime;

    private Date lastPasswordResetTime;

}