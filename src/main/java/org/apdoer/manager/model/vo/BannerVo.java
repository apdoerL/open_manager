package org.apdoer.manager.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Date;

/**
 * @author apdoer
 * @version 1.0
 * @date 2018/9/20 14:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BannerVo {
    private Integer id;

    private String title;

    private String imgUrl;

    private String jumpUrl;

    private Integer position;

    private String creator;

    private Integer enabled;

    private Date createTime;

    private Integer type;

    private String language;

    private String thumbnail;

    @JsonIgnore
    private String queryContent;
}
