package com.aaa.entity;

public class Orders {
    protected int startRow;

    protected int pageSize;
    private int o_id;
    private double o_totalprice;
    private String o_time;
    private int u_id;
    private int o_deskNum;
    private Users users;

    public int getO_deskNum() {
        return o_deskNum;
    }

    public void setO_deskNum(int o_deskNum) {
        this.o_deskNum = o_deskNum;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
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

    public int getO_id() {
        return o_id;
    }

    public void setO_id(int o_id) {
        this.o_id = o_id;
    }

    public double getO_totalprice() {
        return o_totalprice;
    }

    public void setO_totalprice(double o_totalprice) {
        this.o_totalprice = o_totalprice;
    }

    public String getO_time() {
        return o_time;
    }

    public void setO_time(String o_time) {
        this.o_time = o_time;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }
}
