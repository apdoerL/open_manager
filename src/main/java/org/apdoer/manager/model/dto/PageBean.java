package org.apdoer.manager.model.dto;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.Page;
import lombok.Data;

/**
 * @author apdoer
 */
@Data
public class PageBean<T> implements Serializable {
    private static final long serialVersionUID = 8656597559014685635L;
    //总记录数
    private long total;
    //结果集
    private List<T> list;
    // 第几页
    private int pageNum = 1;
    // 每页记录数
    private int pageSize;
    // 总页数
    private int pages;
    // 当前页的数量 <= pageSize，该属性来自ArrayList的size属性
    private int size = 10;
    // 排序属性
    private String orderBy;


    //用于再次配置pageBean信息
    public void initPageBean(List<T> list) {
        if (list instanceof Page) {
            Page<T> page = (Page<T>) list;
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.total = page.getTotal();
            this.pages = page.getPages();
            this.size = page.size();
        }
    }



}
