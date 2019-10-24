package com.aaa.entity;


public class Menus {
    private Integer m_id;
    private String m_name;
    private String m_img;
    private Double m_price;
    private Integer m_state;
    private Integer t_id;

    public Integer getM_id() {
        return m_id;
    }

    public void setM_id(Integer m_id) {
        this.m_id = m_id;
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

    public Double getM_price() {
        return m_price;
    }

    public void setM_price(Double m_price) {
        this.m_price = m_price;
    }

    public Integer getM_state() {
        return m_state;
    }

    public void setM_state(Integer m_state) {
        this.m_state = m_state;
    }

    public Integer getT_id() {
        return t_id;
    }

    public void setT_id(Integer t_id) {
        this.t_id = t_id;
    }

    public Menus() {
    }

    public Menus(Integer m_id, String m_name, String m_img, Double m_price, Integer m_state, Integer t_id) {
        this.m_id = m_id;
        this.m_name = m_name;
        this.m_img = m_img;
        this.m_price = m_price;
        this.m_state = m_state;
        this.t_id = t_id;
    }
}
