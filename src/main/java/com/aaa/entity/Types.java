package com.aaa.entity;

public class Types {
    private Integer t_id;
    private Integer t_state;
    private String t_name;
    protected  int startRow;
    protected int pageSize;

    public Integer getT_id() {
        return t_id;
    }

    public void setT_id(Integer t_id) {
        this.t_id = t_id;
    }

    public Integer getT_state() {
        return t_state;
    }

    public void setT_state(Integer t_state) {
        this.t_state = t_state;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

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
}

