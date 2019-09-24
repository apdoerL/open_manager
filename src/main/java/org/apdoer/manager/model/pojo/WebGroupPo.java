package org.apdoer.manager.model.pojo;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author apdoer
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Data
@Table(name = "web_group")
public class WebGroupPo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer groupId;

    private String groupName;

    private String description;

    private Integer enabled;

    private Date createTime;
}
