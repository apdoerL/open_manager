package org.apdoer.manager.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Date;

/**
 * @author apdoer
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class WebGroupVo {

    private Integer groupId;

    private String groupName;

    private String description;

    private Integer enabled;

    private Date createTime;

    @JsonIgnore
    private String queryContent;
}
