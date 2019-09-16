package org.apdoer.manager.model.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author apdoer
 * @date 2018-12-03
 */
@Entity
@Data
@Table(name = "biz_permission")
public class PermissionPo implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	private String value;

	private Integer parentId;

	private Byte enabled;

	private Date createTime;
}
