package org.apdoer.manager.model.vo;

import lombok.*;

/**
 * @author apdoer
 * @version 1.0
 * @date 2018/9/19 17:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserUpdateVo {
    private Integer userId;

    private String username;

    private String phone;
}
