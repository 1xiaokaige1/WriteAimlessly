package com.xiaokaige.dto;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: qinrongjun
 * @date: 2021/1/18 5:13 下午
 */
@Data
public class PageDTO<T> {
    @ApiModelProperty("数据")
    private List<T> data;

    @ApiModelProperty("数据总条数")
    private Long total;

    @ApiModelProperty("页数")
    private Long pages;

    @ApiModelProperty("附加数据")
    private Object attach;

    @ApiModelProperty("分页下标")
    private Long index;

    @ApiModelProperty("页面大小")
    private Long size;

    public PageDTO(List<T> data,
                   Object attach,
                   IPage<?> page) {
        this.data = data;
        this.total = page.getTotal();
        this.pages = page.getPages();
        this.attach = attach;
        this.index = page.getCurrent();
        this.size = page.getSize();
    }

    public PageDTO(List<T> data,
                   IPage<?> page) {
        this(data, null, page);
    }

    public PageDTO(List<T> data, PageInfo<?> page) {
        this.data = data;
        this.total = page.getTotal();
        this.pages = (long) page.getPages();
        this.index = (long) page.getPageNum();
        this.size = (long) page.getPageSize();
    }


    public static <T> PageDTO<T> of(List<T> data,
                              long pageIndex,
                              long pageSize,
                              long total,
                              Object attach) {
        if (0 == pageSize) {
            return new PageDTO<>();
        }
        long pages = total % pageSize > 0 ? total / pageSize + 1 : total / pageSize;
        PageDTO<T> pageDTO = new PageDTO<>();
        pageDTO.setData(data);
        pageDTO.setIndex(pageIndex);
        pageDTO.setSize(pageSize);
        pageDTO.setTotal(total);
        pageDTO.setPages(pages);
        pageDTO.setAttach(attach);
        return pageDTO;
    }

    public static <T> PageDTO<T> of(List<T> data,
                                    long pageIndex,
                                    long pageSize,
                                    long total) {
        if (0 == pageSize) {
            return new PageDTO<>();
        }
        long pages = total % pageSize > 0 ? total / pageSize + 1 : total / pageSize;
        PageDTO<T> pageDTO = new PageDTO<>();
        pageDTO.setData(data);
        pageDTO.setIndex(pageIndex);
        pageDTO.setSize(pageSize);
        pageDTO.setTotal(total);
        pageDTO.setPages(pages);
        pageDTO.setAttach(null);
        return pageDTO;
    }

    public PageDTO() {
        this.data = new ArrayList<>();
        this.total = 0L;
        this.pages = 0L;
        this.attach = null;
        this.index = 0L;
        this.size = 0L;

    }
}
