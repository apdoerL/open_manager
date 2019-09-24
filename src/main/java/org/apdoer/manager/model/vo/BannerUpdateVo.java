package org.apdoer.manager.model.vo;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author apdoer
 * @version 1.0
 * @date 2018/9/20 14:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BannerUpdateVo {

    @NotNull(message = "bannerid不能为空")
    private Integer id;

    @NotBlank(message = "title不能为空")
    @Size(max = 25)
    private String title;

    @NotBlank(message = "图片地址不能为空")
    @Size(max = 1000)
    private String imgUrl;

    @NotBlank(message = "跳转路径不能为空")
    @Size(max = 1000)
    private String jumpUrl;

    @NotNull(message = "位置不能为空")
    @Size(max = 6)
    private Integer position;

    @NotNull(message = "类型不能为空")
    @Size(max = 2)
    private Integer type;

    @NotBlank(message = "缩略图不能为空")
    @Size(max = 5)
    private String language;

    @NotBlank(message = "缩略图不能为空")
    @Size(max = 1000)
    private String thumbnail;
}
