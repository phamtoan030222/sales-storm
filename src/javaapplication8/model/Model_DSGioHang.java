/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication8.model;

/**
 *
 * @author nguyentuananh
 */
public class Model_DSGioHang {

    private String tensp, mausac, mahd, maspct, magh;
    private int soluong;
    private double dongia, thanhtien, giamgia;
    private boolean trangthai;

    public Model_DSGioHang(String tensp, String mausac, String mahd, String maspct, String magh, int soluong, double dongia, double thanhtien, double giamgia, boolean trangthai) {
        this.tensp = tensp;
        this.mausac = mausac;
        this.mahd = mahd;
        this.maspct = maspct;
        this.magh = magh;
        this.soluong = soluong;
        this.dongia = dongia;
        this.thanhtien = thanhtien;
        this.giamgia = giamgia;
        this.trangthai = trangthai;
    }

    public Model_DSGioHang() {
    }

    public String getMahd() {
        return mahd;
    }

    public void setMahd(String mahd) {
        this.mahd = mahd;
    }

    public String getMaspct() {
        return maspct;
    }

    public void setMaspct(String maspct) {
        this.maspct = maspct;
    }

    public String getMagh() {
        return magh;
    }

    public void setMagh(String magh) {
        this.magh = magh;
    }

    public String getMausac() {
        return mausac;
    }

    public void setMausac(String mausac) {
        this.mausac = mausac;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public double getDongia() {
        return dongia;
    }

    public void setDongia(double dongia) {
        this.dongia = dongia;
    }

    public double getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(double thanhtien) {
        this.thanhtien = thanhtien;
    }

    public double getGiamgia() {
        return giamgia;
    }

    public void setGiamgia(double giamgia) {
        this.giamgia = giamgia;
    }

    public boolean isTrangthai() {
        return trangthai;
    }

    public void setTrangthai(boolean trangthai) {
        this.trangthai = trangthai;
    }

}
