package org.apdoer.manager.model.pojo;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Table;


/**
 * @author apdoer
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "biz_record")
public class RecordPo implements Serializable {
	private static final long	serialVersionUID	= -3568018161450543446L;

	private Long				id;

	private String				account;

	private String				url;

	private String				ip;

	private String				location;

	private String				operType;

	private String				recordType;

	private Date				createTime;

}
