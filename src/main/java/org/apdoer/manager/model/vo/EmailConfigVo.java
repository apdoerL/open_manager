package org.apdoer.manager.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author Li
 * @version 1.0
 * @date 2019/9/25 11:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailConfigVo {

    private String host;

    private String port;

    private String fromUser;

    private String pass;

    private String receiveUser;
}
