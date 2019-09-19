package org.apdoer.manager.model.vo;

import lombok.*;

import javax.validation.constraints.NotBlank;

/**
 * @author apdoer
 * @version 1.0
 * @date 2019/9/19 18:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserPwdUpdateVo {
    @NotBlank(message = "原密码不能为空")
    private String oldPass;
    @NotBlank(message = "新密码不能为空")
    private String newPass;
}
