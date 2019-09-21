package org.apdoer.manager.model.vo;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author apdoer
 * @version 1.0
 * @date 2018/9/20 18:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OperationRecordVo {

    private Integer id;

    private String account;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date operTime;

    private String ipAddress;

    private String location;

    private String operUrl;

    private String operType;

}
