package com.aaa.entity;

import java.util.List;

public class OrderDetial {
    protected int startRow;

    protected int pageSize;
    private Integer l_id;
    private Integer l_count;
    private double l_sum;
    private String l_remarks;
    private Integer l_state;
    private Integer o_id;
    private Integer m_id;
   private List<Orders> orders;
    private List<Menu> menus;

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public Integer getL_id() {
        return l_id;
    }

    public void setL_id(Integer l_id) {
        this.l_id = l_id;
    }

    public Integer getL_count() {
        return l_count;
    }

    public void setL_count(Integer l_count) {
        this.l_count = l_count;
    }

    public double getL_sum() {
        return l_sum;
    }

    public void setL_sum(double l_sum) {
        this.l_sum = l_sum;
    }

    public String getL_remarks() {
        return l_remarks;
    }

    public void setL_remarks(String l_remarks) {
        this.l_remarks = l_remarks;
    }

    public Integer getL_state() {
        return l_state;
    }

    public void setL_state(Integer l_state) {
        this.l_state = l_state;
    }

    public Integer getO_id() {
        return o_id;
    }

    public void setO_id(Integer o_id) {
        this.o_id = o_id;
    }

    public Integer getM_id() {
        return m_id;
    }

    public void setM_id(Integer m_id) {
        this.m_id = m_id;
    }
}
