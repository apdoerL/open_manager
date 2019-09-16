package org.apdoer.manager.controller;

import lombok.extern.slf4j.Slf4j;
import org.apdoer.manager.annotations.SystemControllerLog;
import org.apdoer.manager.handler.QiNiuHandler;
import org.apdoer.manager.model.dto.QiniuQueryCriteria;
import org.apdoer.manager.model.pojo.QiniuCloudPo;
import org.apdoer.manager.model.pojo.QiniuFilePo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * 发送邮件
 * @author apdoer
 * @date 2018/09/28 6:55:53
 */
@Slf4j
@RestController
@RequestMapping("api")
public class QiniuController {

    @Autowired
    private QiNiuHandler qiNiuHandler;

    @GetMapping(value = "/qiNiuConfig")
    public ResponseEntity get(){
        return new ResponseEntity(qiNiuHandler.find(), HttpStatus.OK);
    }

    @SystemControllerLog("配置七牛云存储")
    @PutMapping(value = "/qiNiuConfig")
    public ResponseEntity emailConfig(@Validated @RequestBody QiniuCloudPo qiniuConfig){
        qiNiuHandler.update(qiniuConfig);
        return new ResponseEntity(HttpStatus.OK);
    }

    @SystemControllerLog("查询文件")
    @GetMapping(value = "/qiNiuContent")
    public ResponseEntity getRoles(QiniuQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(qiNiuHandler.queryAll(criteria,pageable),HttpStatus.OK);
    }

    /**
     * 上传文件到七牛云
     * @param file
     * @return
     */
    @SystemControllerLog("上传文件")
    @PostMapping(value = "/qiNiuContent")
    public ResponseEntity upload(@RequestParam MultipartFile file){
        QiniuFilePo qiniuContent = qiNiuHandler.upload(file,qiNiuHandler.find());
        Map map = new HashMap(3);
        map.put("id",qiniuContent.getId());
        map.put("errno",0);
        map.put("data",new String[]{qiniuContent.getUrl()});
        return new ResponseEntity(map,HttpStatus.OK);
    }

    /**
     * 同步七牛云数据到数据库
     * @return
     */
    @SystemControllerLog("同步七牛云数据")
    @PostMapping(value = "/qiNiuContent/synchronize")
    public ResponseEntity synchronize(){
        log.warn("REST request to synchronize qiNiu : {}");
        qiNiuHandler.synchronize(qiNiuHandler.find());
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 下载七牛云文件
     * @param id
     * @return
     * @throws Exception
     */
    @SystemControllerLog("下载文件")
    @GetMapping(value = "/qiNiuContent/download/{id}")
    public ResponseEntity download(@PathVariable Long id){
        Map map = new HashMap(1);
        map.put("url", qiNiuHandler.download(qiNiuHandler.findByContentId(id),qiNiuHandler.find()));
        return new ResponseEntity(map,HttpStatus.OK);
    }

    /**
     * 删除七牛云文件
     * @param id
     * @return
     * @throws Exception
     */
    @SystemControllerLog("删除文件")
    @DeleteMapping(value = "/qiNiuContent/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        qiNiuHandler.delete(qiNiuHandler.findByContentId(id),qiNiuHandler.find());
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 删除多张图片
     * @param ids
     * @return
     */
    @SystemControllerLog("删除图片")
    @DeleteMapping(value = "/qiNiuContent")
    public ResponseEntity deleteAll(@RequestBody Long[] ids) {
        qiNiuHandler.deleteAll(ids, qiNiuHandler.find());
        return new ResponseEntity(HttpStatus.OK);
    }
}
