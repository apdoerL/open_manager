package org.apdoer.manager.model.vo;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author apdoer
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GroupUpdateVo {

    @NotNull(message = "组id不能为空")
    private Integer groupId;

    @NotBlank(message = "组名不能为空")
    @Size(max = 25)
    private String groupName;

    @NotBlank(message = "组名不能为空")
    @Size(max = 1000)
    private String description;
}
