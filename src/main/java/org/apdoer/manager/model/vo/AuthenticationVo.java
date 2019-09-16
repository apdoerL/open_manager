package org.apdoer.manager.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * @author apdoer
 * @date 2018-11-23
 * 返回token
 */
@Getter
@AllArgsConstructor
public class AuthenticationVo {

    private final String token;

    private final JwtUser user;
}
