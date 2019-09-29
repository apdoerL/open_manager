package org.apdoer.manager.model.vo;

import lombok.*;

import java.util.Date;

/**
 * @author apdoer
 * @version 1.0
 * @date 2019/9/19 19:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RoleVo {
    private Integer id;

    private String name;

    private String desc;

    private Date createTime;

    private Integer enabled;
}
