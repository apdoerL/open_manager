package org.apdoer.manager.model.vo;

import lombok.*;

/**
 * @author apdoer
 * @version 1.0
 * @date 2019/9/29 17:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RoleIdDescVo {

    private Integer roleId;

    private String name;
}
