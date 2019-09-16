package org.apdoer.manager.handler;

import org.apdoer.manager.model.dto.QiniuQueryCriteria;
import org.apdoer.manager.model.pojo.QiniuCloudPo;
import org.apdoer.manager.model.pojo.QiniuFilePo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author apdoer
 * @date 2018-12-31
 */
@CacheConfig(cacheNames = "qiNiu")
public interface QiNiuHandler {

    /**
     * 查询文件
     * @param criteria
     * @param pageable
     * @return
     */
    @Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(QiniuQueryCriteria criteria, Pageable pageable);

    /**
     * 查配置
     * @return
     */
    @Cacheable(cacheNames = "qiNiuConfig", key = "'1'")
    QiniuCloudPo find();

    /**
     * 修改配置
     * @param qiniuConfig
     * @return
     */
    @CachePut(cacheNames = "qiNiuConfig", key = "'1'")
    QiniuCloudPo update(QiniuCloudPo qiniuConfig);

    /**
     * 上传文件
     * @param file
     * @param qiniuConfig
     * @return
     */
    @CacheEvict(allEntries = true)
    QiniuFilePo upload(MultipartFile file, QiniuCloudPo qiniuConfig);

    /**
     * 查询文件
     * @param id
     * @return
     */
    @Cacheable(key = "'content:'+#p0")
    QiniuFilePo findByContentId(Long id);

    /**
     * 下载文件
     * @param content
     * @param config
     * @return
     */
    String download(QiniuFilePo content, QiniuCloudPo config);

    /**
     * 删除文件
     * @param content
     * @param config
     * @return
     */
    @CacheEvict(allEntries = true)
    void delete(QiniuFilePo content, QiniuCloudPo config);

    /**
     * 同步数据
     * @param config
     */
    @CacheEvict(allEntries = true)
    void synchronize(QiniuCloudPo config);

    /**
     * 删除文件
     * @param ids
     * @param config
     */
    @CacheEvict(allEntries = true)
    void deleteAll(Long[] ids, QiniuCloudPo config);
}
