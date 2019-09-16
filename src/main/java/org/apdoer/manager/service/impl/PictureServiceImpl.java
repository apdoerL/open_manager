//package org.apdoer.manager.service.impl;
//
//import cn.hutool.http.HttpUtil;
//import cn.hutool.json.JSONObject;
//import cn.hutool.json.JSONUtil;
//import com.alibaba.fastjson.JSON;
//import lombok.extern.slf4j.Slf4j;
//import org.apdoer.manager.constants.ManagerConstant;
//import org.apdoer.manager.exception.BadRequestException;
//import org.apdoer.manager.model.pojo.PicturePo;
//import org.apdoer.manager.repository.PictureRepository;
//import org.apdoer.manager.service.PictureService;
//import org.apdoer.manager.utils.FileUtil;
//import org.apdoer.manager.utils.PageUtil;
//import org.apdoer.manager.utils.QueryHelper;
//import org.apdoer.manager.utils.ValidationUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.util.HashMap;
//import java.util.Optional;
//
///**
// * @author apdoer
// * @date 2018-12-27
// */
//@Slf4j
//@Service(value = "pictureService")
//@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
//public class PictureServiceImpl implements PictureService {
//
//    @Autowired
//    private PictureRepository pictureRepository;
//
//    public static final String SUCCESS = "success";
//
//    public static final String CODE = "code";
//
//    public static final String MSG = "msg";
//
//    @Override
//    public Object queryAll(PictureQueryCriteria criteria, Pageable pageable){
//        return PageUtil.toPage(pictureRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelper.getPredicate(root,criteria,criteriaBuilder),pageable));
//    }
//
//    @Override
//    @Transactional(rollbackFor = Throwable.class)
//    public PicturePo upload(MultipartFile multipartFile, String username) {
//        File file = FileUtil.toFile(multipartFile);
//
//        HashMap<String, Object> paramMap = new HashMap<>(1);
//
//        paramMap.put("smfile", file);
//        String result= HttpUtil.post(ManagerConstant.Url.SM_MS_URL, paramMap);
//
//        JSONObject jsonObject = JSONUtil.parseObj(result);
//        PicturePo picture = null;
//        if(!jsonObject.get(CODE).toString().equals(SUCCESS)){
//            throw new BadRequestException(jsonObject.get(MSG).toString());
//        }
//        //转成实体类
//        picture = JSON.parseObject(jsonObject.get("data").toString(), PicturePo.class);
//        picture.setSize(FileUtil.getSize(Integer.valueOf(picture.getSize())));
//        picture.setUsername(username);
//        picture.setFilename(FileUtil.getFileNameNoEx(multipartFile.getOriginalFilename())+"."+FileUtil.getExtensionName(multipartFile.getOriginalFilename()));
//        pictureRepository.save(picture);
//        //删除临时文件
//        FileUtil.deleteFile(file);
//        return picture;
//
//    }
//
//    @Override
//    public PicturePo findById(Long id) {
//        Optional<PicturePo> picture = pictureRepository.findById(id);
//        ValidationUtil.isNull(picture,"Picture","id",id);
//        return picture.get();
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void delete(PicturePo picture) {
//        try {
//            String result= HttpUtil.get(picture.getDelete());
//            pictureRepository.delete(picture);
//        } catch(Exception e){
//            pictureRepository.delete(picture);
//        }
//
//    }
//
//    @Override
//    public void deleteAll(Long[] ids) {
//        for (Long id : ids) {
//            delete(findById(id));
//        }
//    }
//}
