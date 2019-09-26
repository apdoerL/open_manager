package org.apdoer.manager.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Li
 * @version 1.0
 * @date 2019/9/26 18:56
 */
@AllArgsConstructor
@Getter
public enum CommonStatusEnum {


    /*启用*/
    ENABLED(1,"启用"),
    /*不启用*/
    DISABLED(0,"不启用"),


    ;
    private int code;

    private String value;
}
