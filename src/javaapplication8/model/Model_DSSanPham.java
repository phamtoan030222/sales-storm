/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication8.model;

/**
 *
 * @author nguyentuananh
 */
public class Model_DSSanPham {
     private String tensp,chatlieu,kichthuoc,mausac,masp; 
    private int soluong,id;
    private double dongia;

    public Model_DSSanPham() {
    }

    public Model_DSSanPham(String tensp, String chatlieu, String kichthuoc, String mausac, int soluong, int id, double dongia) {
        this.tensp = tensp;
        this.chatlieu = chatlieu;
        this.kichthuoc = kichthuoc;
        this.mausac = mausac;
        this.soluong = soluong;
        this.id = id;
        this.dongia = dongia;
    }

    public Model_DSSanPham(String tensp, String chatlieu, String kichthuoc, String mausac, int soluong, double dongia) {
        this.tensp = tensp;
      
        this.chatlieu = chatlieu;
        this.kichthuoc = kichthuoc;
        this.mausac = mausac;
        this.soluong = soluong;
        this.dongia = dongia;
    }

    public Model_DSSanPham(int soluong, int id) {
        this.soluong = soluong;
        this.id = id;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

  

    public String getChatlieu() {
        return chatlieu;
    }

    public void setChatlieu(String chatlieu) {
        this.chatlieu = chatlieu;
    }

    public String getKichthuoc() {
        return kichthuoc;
    }

    public void setKichthuoc(String kichthuoc) {
        this.kichthuoc = kichthuoc;
    }

    public String getMausac() {
        return mausac;
    }

    public void setMausac(String mausac) {
        this.mausac = mausac;
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

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }
     
}
