package com.aaa.entity;

public class MenusDB {
    private int m_id;
    private String m_name;
    private String m_img;
    private double m_price;
    private int m_state;
    private String t_name;

    public MenusDB(int m_id, String m_name, String m_img, double m_price, int m_state, String t_name) {
        this.m_id = m_id;
        this.m_name = m_name;
        this.m_img = m_img;
        this.m_price = m_price;
        this.m_state = m_state;
        this.t_name = t_name;
    }

    @Override
    public String toString() {
        return "MenusDB{" +
                "m_id=" + m_id +
                ", m_name='" + m_name + '\'' +
                ", m_img='" + m_img + '\'' +
                ", m_price=" + m_price +
                ", m_state=" + m_state +
                ", t_name='" + t_name + '\'' +
                '}';
    }

    public MenusDB() {
    }

    public int getM_id() {
        return m_id;
    }

    public void setM_id(int m_id) {
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

    public double getM_price() {
        return m_price;
    }

    public void setM_price(double m_price) {
        this.m_price = m_price;
    }

    public int getM_state() {
        return m_state;
    }

    public void setM_state(int m_state) {
        this.m_state = m_state;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }
}
