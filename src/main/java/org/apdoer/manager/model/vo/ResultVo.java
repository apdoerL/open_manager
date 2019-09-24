package org.apdoer.manager.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultVo {

    private Integer code;

    private String msg;

    private Object data;

    public ResultVo(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }
}
