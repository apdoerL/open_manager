package org.apdoer.manager.model.vo;

import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * @author apdoer
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GroupMemberVo {

    @NotNull(message = "组id不能为空")
    private Integer groupId;

    @NotNull(message = "userId不能为空")
    private Integer userId;
}
