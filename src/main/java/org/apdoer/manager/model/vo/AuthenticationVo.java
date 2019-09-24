package org.apdoer.manager.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author apdoer
 * @date 2018-11-23
 * 返回给前端的 token 和uservo
 */
@Data
@AllArgsConstructor
public class AuthenticationVo {

    private final String token;

    private final JwtUser user;
}
