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
public class GroupCreateVo {

    private String groupName;

    private String description;
}
