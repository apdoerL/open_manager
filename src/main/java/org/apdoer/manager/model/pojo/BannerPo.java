package org.apdoer.manager.model.pojo;

import lombok.*;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author apdoer
 * @version 1.0
 * @date 2018/9/20 14:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "biz_banner")
public class BannerPo implements Serializable {

    private static final long serialVersionUID = 4287810495345489291L;

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
}
