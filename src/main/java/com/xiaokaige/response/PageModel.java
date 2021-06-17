package com.xiaokaige.response;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import com.xiaokaige.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页模型
 *
 * @author lgs
 */
@AllArgsConstructor
@ApiModel(description = "分页模型xxx")
@Data
public class PageModel<T> {

    @ApiModelProperty("数据")
    private List<T> data;

    @ApiModelProperty("数据总条数")
    private Long total;

    @ApiModelProperty("附加数据")
    private Object attach;

    @ApiModelProperty("分页下标")
    private Long index;

    @ApiModelProperty("页面大小")
    private Long size;

    public PageModel(List<T> data,
                     Object attach,
                     IPage<?> page) {
        this.data = data;
        this.total = page.getTotal();
        this.attach = attach;
        this.index = page.getCurrent();
        this.size = page.getSize();
    }

    public PageModel(List<T> data,
                     IPage<?> page) {
       this(data, null, page);
    }

    public PageModel(List<T> data, PageInfo<?> page) {
        this.data = data;
        this.total = page.getTotal();
        this.index =(long)page.getPageNum();
        this.size = (long)page.getPageSize();
    }

    public PageModel() {
        this.data = new ArrayList<>();
        this.total = 0L;
        this.attach = null;
        this.index = 0L;
        this.size = 0L;
    }

    public static <T> PageModel<T> of(PageDTO<T> pageDTO) {
        PageModel<T> pageModel = new PageModel<>();
        if (null == pageDTO) {
            return pageModel;
        }
        pageModel.setData(pageDTO.getData());
        pageModel.setTotal(pageDTO.getTotal());
        pageModel.setAttach(pageDTO.getAttach());
        pageModel.setIndex(pageDTO.getIndex());
        pageModel.setSize(pageDTO.getSize());
        return pageModel;
    }

    public static <T1, T2> PageModel<T1> of(PageDTO<T2> pageDTO, List<T1> dataList) {
        PageModel<T1> pageModel = new PageModel<>();
        if (null == pageDTO) {
            return pageModel;
        }
        pageModel.setData(dataList);
        pageModel.setTotal(pageDTO.getTotal());
        pageModel.setAttach(pageDTO.getAttach());
        pageModel.setIndex(pageDTO.getIndex());
        pageModel.setSize(pageDTO.getSize());
        return pageModel;
    }
}
