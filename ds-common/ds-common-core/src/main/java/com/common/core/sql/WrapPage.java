package com.common.core.sql;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 代理 Page 对象，主要用于对接不同的前端分页字段
 *
 * @author guangmingdexin
 * @date 2022/4/16
 */
@Getter
@Setter
@ToString
public class WrapPage<T> implements IPage<T> {

    @JsonIgnore
    IPage<T> unwrap;

    private long pageNo;

    private long pageSize;

    private long totalCount;

    private long totalPage;

    private List<T> data;

    public WrapPage() {
        this.unwrap = new Page<>();
        this.data = new ArrayList<>();
    }

    public WrapPage(IPage<T> unwrap) {
        this.unwrap = unwrap;
        this.pageNo = unwrap.getCurrent();
        this.pageSize = unwrap.getSize();
        this.totalPage = unwrap.getPages();
        this.totalCount = unwrap.getTotal();
        this.data = unwrap.getRecords();
    }

    @Override
    public List<OrderItem> orders() {
        return unwrap.orders();
    }

    @JsonIgnore
    @Override
    public List<T> getRecords() {
        return unwrap.getRecords();
    }

    @JsonIgnore
    @Override
    public IPage<T> setRecords(List<T> records) {
        return unwrap.setRecords(records);
    }

    @JsonIgnore
    @Override
    public long getTotal() {
        return unwrap.getTotal();
    }

    @JsonIgnore
    @Override
    public IPage<T> setTotal(long total) {
        return unwrap.setTotal(total);
    }

    @JsonIgnore
    @Override
    public long getSize() {
        return unwrap.getSize();
    }

    @JsonIgnore
    @Override
    public IPage<T> setSize(long size) {
        return unwrap.setSize(size);
    }

    @JsonIgnore
    @Override
    public long getCurrent() {
        return unwrap.getCurrent();
    }

    @JsonIgnore
    @Override
    public IPage<T> setCurrent(long current) {
        return unwrap.setCurrent(current);
    }
}
