package org.apdoer.manager.model.pojo;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long				id;

	private String				account;

	private String				url;

	private String				ip;

	private String				location;

	private String				operType;

	private String				recordType;

	private Date				createTime;

    public RecordPo(String user, String logUrl, String logIp, String logAddress, String operType, String recordType, Date date) {
    	this.account = user;
    	this.url = logUrl;
    	this.ip = logIp;
    	this.location = logAddress;
    	this.operType = operType;
    	this.recordType = recordType;
    	this.createTime = date;
    }
}
