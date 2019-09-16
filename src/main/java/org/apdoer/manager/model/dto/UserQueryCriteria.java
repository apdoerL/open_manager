package org.apdoer.manager.model.dto;

import lombok.Data;

import java.util.Set;

/**
 * @author apdoer
 * @date 2018-11-23
 */
@Data
public class UserQueryCriteria {

    private Long id;

    private Set<Long> deptIds;

    private String username;

    private String email;

    private Boolean enabled;

    private Long deptId;
}
