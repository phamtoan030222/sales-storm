/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author dungc
 */
public class SanPham_ThuocTinh {

    private int id;
    private String ten;
    private boolean trangThai;

    public SanPham_ThuocTinh() {
    }

    public SanPham_ThuocTinh(int id, String ten) {
        this.id = id;
        this.ten = ten;
    }
   

    public SanPham_ThuocTinh(String ten, boolean trangThai) {
        this.ten = ten;
        this.trangThai = trangThai;
    }

    public SanPham_ThuocTinh(String ten) {
        this.ten = ten;
    }

  

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    @Override
    public String toString() { 
        return this.ten;  // Hiển thị tên trong ComboBox
    }
    
}
