package org.apdoer.manager.model.vo;

import lombok.*;
import org.apdoer.manager.utils.RegexUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author apdoer
 * @version 1.0
 * @date 2018/9/19 17:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserCreateVo {

    private Integer id;

    @NotBlank(message = "用户名不能为空")
    @Size(max = 16,message = "用户名不能超过16位")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Size(max = 16,message = "密码只能在6-16位之间",min = 6)
    @Pattern(regexp = "^[a-zA-Z0-9]{6,16}$",message = "密码必须是数字,英文字符")
    private String password;

    private Integer roleId;

    @NotBlank(message = "谷歌验证码不能为空")
    private String googleCode;

    private String phone;

    @NotBlank(message = "邮箱必填")
    @Pattern(regexp = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$",message = "邮箱格式不对")
    private String email;

    private String avatar;
}
