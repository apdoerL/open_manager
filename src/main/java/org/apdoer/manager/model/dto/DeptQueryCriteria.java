package org.apdoer.manager.model.dto;

import lombok.Data;

import java.util.Set;

/**
* @author apdoer
* @date 2019-03-25
*/
@Data
public class DeptQueryCriteria{

    private Set<Long> ids;

    private String name;

    private Boolean enabled;

    private Long pid;
}