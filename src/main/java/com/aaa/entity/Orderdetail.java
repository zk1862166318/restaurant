package com.aaa.entity;

public class Orderdetail {
    public Integer l_id;
    public Integer l_count;
    public String l_sum;
    public String l_remarks;
    public String l_state;
    public String m_name;
    public String m_img;
    public String o_time;
    public String Obegin;
    public String Oend;
    public String demoReload;

    public String getObegin() {
        return Obegin;
    }

    public void setObegin(String obegin) {
        Obegin = obegin;
    }

    public String getOend() {
        return Oend;
    }

    public void setOend(String oend) {
        Oend = oend;
    }

    public String getDemoReload() {
        return demoReload;
    }

    public void setDemoReload(String demoReload) {
        this.demoReload = demoReload;
    }

    public String getO_time() {
        return o_time;
    }

    public void setO_time(String o_time) {
        this.o_time = o_time;
    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public String getM_img() {
        return m_img;
    }

    public void setM_img(String m_img) {
        this.m_img = m_img;
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

    public String getL_sum() {
        return l_sum;
    }

    public void setL_sum(String l_sum) {
        this.l_sum = l_sum;
    }

    public String getL_remarks() {
        return l_remarks;
    }

    public void setL_remarks(String l_remarks) {
        this.l_remarks = l_remarks;
    }

    public String getL_state() {
        return l_state;
    }

    public void setL_state(String l_state) {
        this.l_state = l_state;
    }
}
