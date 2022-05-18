package com.guang.persistence.wrap;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;

import java.util.List;

/**
 * @author guangmingdexin
 * @date 2022/4/16
 */
public class WrapPage<T> implements IPage<T> {

    IPage<T> unwrap;

    private long pageNo;

    private long pageSize;

    private long totalCount;

    private long totalPage;

    private List<T> data;

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

    @Override
    public List<T> getRecords() {
        return unwrap.getRecords();
    }

    @Override
    public IPage<T> setRecords(List<T> records) {
        return unwrap.setRecords(records);
    }

    @Override
    public long getTotal() {
        return unwrap.getTotal();
    }

    @Override
    public IPage<T> setTotal(long total) {
        return unwrap.setTotal(total);
    }

    @Override
    public long getSize() {
        return unwrap.getSize();
    }

    @Override
    public IPage<T> setSize(long size) {
        return unwrap.setSize(size);
    }

    @Override
    public long getCurrent() {
        return unwrap.getCurrent();
    }

    @Override
    public IPage<T> setCurrent(long current) {
        return unwrap.setCurrent(current);
    }
}
