package org.apdoer.manager.model.pojo;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author apdoer
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name = "web_user")
public class WebUserPo implements Serializable {
    private static final long serialVersionUID = 8761789725081606023L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    private String phone;

    private String email;

    private String googleCode;

    private Integer enabled;

    private Date createTime;
}
