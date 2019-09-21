package org.apdoer.manager.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Date;

/**
 * @author apdoer
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class WebUserVo {
    private Integer id;

    private String username;

    private String password;

    private String phone;

    private String email;

    private String googleCode;

    private Integer enabled;

    private Date createTime;

    @JsonIgnore
    private String queryContent;
}
