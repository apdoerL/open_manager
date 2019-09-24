package org.apdoer.manager.check.impl;

import lombok.extern.slf4j.Slf4j;
import org.apdoer.manager.check.WebGroupCheckService;
import org.apdoer.manager.model.dto.PageBean;
import org.apdoer.manager.model.vo.*;
import org.apdoer.manager.utils.ResultVoBuildUtils;
import org.springframework.stereotype.Service;

/**
 * @author apdoer
 */
@Service
@Slf4j
public class WebGroupCheckServiceImpl implements WebGroupCheckService {

    @Override
    public ResultVo checkQueryGroupParam(PageBean<WebGroupVo> pageBean, WebGroupVo webGroupVo) {
        // todo
        return ResultVoBuildUtils.buildSuccessResultVo();
    }

    @Override
    public ResultVo checkCreateGroupParam(GroupCreateVo groupCreateVo) {
        // todo
        return ResultVoBuildUtils.buildSuccessResultVo();
    }

    @Override
    public ResultVo checkUpdateGroupParam(GroupUpdateVo groupUpdateVo) {
        // todo
        return ResultVoBuildUtils.buildSuccessResultVo();
    }

    @Override
    public ResultVo checkAddGroupMemberParam(GroupMemberVo groupMemberVo) {
        // todo
        return ResultVoBuildUtils.buildSuccessResultVo();
    }
}
