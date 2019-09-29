package org.apdoer.manager.model.vo;

import lombok.*;

import java.util.List;

/**
 * @author apdoer
 * @version 1.0
 * @date 2019/9/29 18:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PermissionVo {

    private List<PermissionVo> child;

    private Long				permId;

    private String				name;

    private String				value;

    private Long				parentId;
}
