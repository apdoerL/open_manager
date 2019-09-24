package org.apdoer.manager.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * pv 与 ip 统计
 *
 * @author apdoer
 * @date 2018-12-13
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "web_pv_count")
public class WebPvCountPo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String date;

    private Long pvCounts;

    private Long ipCounts;

    private Timestamp createTime;

    private String weekDay;
}
