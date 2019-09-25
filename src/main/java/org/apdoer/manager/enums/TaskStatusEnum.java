package org.apdoer.manager.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author apdoer
 * @version 1.0
 * @date 2019/9/25 15:23
 */
@AllArgsConstructor
@Getter
public enum TaskStatusEnum {
    /**
     * 启用
     */
    ENABLED(1,"启用"),

    /**
     * 暂停
     */
    PAUSE(0,"暂停")


    ;
    private int status;

    private String desc;
}
