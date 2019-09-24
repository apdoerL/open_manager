package org.apdoer.manager.model.vo;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author apdoer
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RolePermUpdateVo {
    @NotNull(message = "roleId不能为null")
    private Integer roleId;

    private List<Integer> permIds;
}
