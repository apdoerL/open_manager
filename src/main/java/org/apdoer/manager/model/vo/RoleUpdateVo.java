package org.apdoer.manager.model.vo;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author apdoer
 * @version 1.0
 * @date 2019/9/19 19:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class RoleUpdateVo {

    @NotNull(message = "roleId不能为空")
    private Integer roleId;

    @NotBlank(message = "roleName不能为空")
    @Size(max = 25)
    private String name;

    @NotBlank(message = "roleDesc不能为空")
    @Size(max = 25)
    private String roleDesc;
}
