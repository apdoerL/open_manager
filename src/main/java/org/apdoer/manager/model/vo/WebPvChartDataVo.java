package org.apdoer.manager.model.vo;

import lombok.*;

/**
 * @author apdoer
 * @version 1.0
 * @date 2019/9/24 15:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class WebPvChartDataVo {

    private String weekDays;

    private Long ipCounts;

    private Long pvCounts;
}
