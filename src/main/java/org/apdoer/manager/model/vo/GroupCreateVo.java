package org.apdoer.manager.model.vo;

import lombok.*;


/**
 * @author apdoer
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GroupCreateVo {

    private String groupName;

    private String description;
}
