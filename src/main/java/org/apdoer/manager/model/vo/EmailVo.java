package org.apdoer.manager.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 发送邮件时，接收参数的类
 * @author apdoer
 * @date 2018/09/28 12:02:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailVo {

    /**
     * 收件人，支持多个收件人，用逗号分隔
     */
    @NotEmpty
    private List<String> receivers;

    @NotBlank
    private String title;

    @NotBlank
    private String content;
}
