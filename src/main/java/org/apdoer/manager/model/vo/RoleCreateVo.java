package org.apdoer.manager.model.vo;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author apdoer
 * @version 1.0
 * @date 2018/9/19 19:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RoleCreateVo {

    @NotBlank
    @Size(max = 12)
    private String name;

    @NotBlank
    @Size(max = 25)
    private String roleDesc;
}
