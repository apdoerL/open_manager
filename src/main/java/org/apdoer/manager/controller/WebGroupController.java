package org.apdoer.manager.controller;

import org.apdoer.manager.annotations.SystemControllerLog;
import org.apdoer.manager.handler.WebGroupHandler;
import org.apdoer.manager.model.dto.PageBean;
import org.apdoer.manager.model.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author apdoer
 * @version 1.0
 * @date 2019/9/20 18:03
 */
@RestController
@RequestMapping("/group")
public class WebGroupController {
    @Autowired
    public void setWebGroupHandler(WebGroupHandler webGroupHandler) {
        this.webGroupHandler = webGroupHandler;
    }

    private WebGroupHandler webGroupHandler;

    @GetMapping("/groupList")
    @SystemControllerLog("查询组列表")
    public ResultVo queryGroupList(PageBean<WebGroupVo>pageBean, WebGroupVo webGroupVo){
        return webGroupHandler.queryGroupList(pageBean,webGroupVo);
    }


    @PostMapping("/group")
    @SystemControllerLog("创建新组")
    public ResultVo addGroup(@RequestBody @Validated GroupCreateVo groupCreateVo){
        return webGroupHandler.createGroup(groupCreateVo);
    }

    @DeleteMapping("/group/{id}")
    @SystemControllerLog("删除组")
    public ResultVo deleteGroup(@PathVariable("id") Integer groupId){
        return webGroupHandler.deletGroup(groupId);
    }

    @PutMapping("/group")
    @SystemControllerLog("更新组信息")
    public ResultVo updateGroup(@RequestBody @Validated GroupUpdateVo groupUpdateVo){
        return webGroupHandler.updateGroup(groupUpdateVo);
    }

    @PostMapping("/groupMember")
    @SystemControllerLog("添加组员")
    public ResultVo addGroupMember(@RequestBody @Validated GroupMemberVo groupMemberVo){
        return webGroupHandler.addGroupMember(groupMemberVo);
    }

    @PutMapping("/group/{id}/{status}")
    @SystemControllerLog("更新组状态")
    public ResultVo updateGroupStatus(@PathVariable("id") Integer groupId,@PathVariable("status") Integer status){
        return webGroupHandler.updateGroupStatus(groupId,status);
    }
}
