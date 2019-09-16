package org.apdoer.manager.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
* @author apdoer
* @date 2019-6-4 14:49:34
*/
@Data
@NoArgsConstructor
public class JobQueryCriteria {

    private String name;

    private Boolean enabled;

    private Long deptId;

    private Set<Long> deptIds;
}