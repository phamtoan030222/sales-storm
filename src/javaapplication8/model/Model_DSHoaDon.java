/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication8.model;

/**
 *
 * @author nguyentuananh
 */
public class Model_DSHoaDon {
    private String tennv, tenkh,sodt,mahd;
    private int id;
    private String ngaytao;
    private boolean trangthai;

    public Model_DSHoaDon(int id) {
        this.id = id;
    }
    

    public Model_DSHoaDon() {
    }

    public Model_DSHoaDon(String tennv, String tenkh, String mahd, String ngaytao, boolean trangthai) {
        this.tennv = tennv;
        this.tenkh = tenkh;
        this.mahd = mahd;
        this.ngaytao = ngaytao;
        this.trangthai = trangthai;
    }

   

   
    public Model_DSHoaDon(String tenkh, String sodt, String ngaytao) {
        this.tenkh = tenkh;
        this.sodt = sodt;
        this.ngaytao = ngaytao;
    }

    public Model_DSHoaDon(String tennv, String tenkh, String sodt, int id, String ngaytao, boolean trangthai) {
        this.tennv = tennv;
        this.tenkh = tenkh;
        this.sodt = sodt;
        this.id = id;
        this.ngaytao = ngaytao;
        this.trangthai = trangthai;
    }

   

 

    public String getSodt() {
        return sodt;
    }

    public void setSodt(String sodt) {
        this.sodt = sodt;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getTennv() {
        return tennv;
    }

    public void setTennv(String tennv) {
        this.tennv = tennv;
    }

    public String getTenkh() {
        return tenkh;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    public String getMahd() {
        return mahd;
    }

    public void setMahd(String mahd) {
        this.mahd = mahd;
    }

    public String  getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(String ngaytao) {
        this.ngaytao = ngaytao;
    }

    public boolean isTrangthai() {
        return trangthai;
    }

    public void setTrangthai(boolean trangthai) {
        this.trangthai = trangthai;
    }
     
}
