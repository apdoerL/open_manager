package org.apdoer.manager.model.pojo;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 角色
 * @author apdoer
 * @date 2018-11-22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "biz_role")
public class RolePo implements Serializable {
    private static final long serialVersionUID = -3032798796195632340L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;

    private String name;

    private String roleDesc;

    private Byte enabled;

    private Date createTime;
}
