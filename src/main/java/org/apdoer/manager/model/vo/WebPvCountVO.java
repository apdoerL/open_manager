package org.apdoer.manager.model.vo;

import lombok.*;

/**
 * @author apdoer
 * @version 1.0
 * @date 2019/9/24 14:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class WebPvCountVO {

    private Long dailyPv;

    private Long dailyIp;

    private Long weeklyIp;

    private Long weeklyPv;
}
