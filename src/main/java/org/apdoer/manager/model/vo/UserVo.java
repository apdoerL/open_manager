package org.apdoer.manager.model.vo;

import lombok.*;

/**
 * @author Li
 * @version 1.0
 * @date 2019/9/19 17:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserVo {

    private Long userId;

    private String username;
}
