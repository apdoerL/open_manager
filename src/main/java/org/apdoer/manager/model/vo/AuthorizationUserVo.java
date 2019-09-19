package org.apdoer.manager.model.vo;

import lombok.*;

import javax.validation.constraints.NotBlank;

/**
 * @author apdoer
 * @date 2018-11-30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizationUserVo {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String googleCode;


    @Override
    public String toString() {
        return "{username=" + username  + ", password= ******}";
    }
}
