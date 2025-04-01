/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author dungc
 */
public class SanPham_ChiTiet {

    private int id;
    private String maSp;
    private String tenSp;
    private String loaiSp;
    private String kichThuoc;
    private String mauSac;
    private String chatLieu;
    private String donGia;
    private int soLuong;
    private boolean daXoa;
    
    

    

    public SanPham_ChiTiet() {
    }

    public SanPham_ChiTiet(String maSp, String tenSp, String loaiSp, String kichThuoc, String mauSac, String chatLieu, String donGia, int soLuong) {
        this.maSp = maSp;
        this.tenSp = tenSp;
        this.loaiSp = loaiSp;
        this.kichThuoc = kichThuoc;
        this.mauSac = mauSac;
        this.chatLieu = chatLieu;
        this.donGia = donGia;
        this.soLuong = soLuong;
    }

    public String getMaSp() {
        return maSp;
    }

    public void setMaSp(String maSp) {
        this.maSp = maSp;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public String getLoaiSp() {
        return loaiSp;
    }

    public void setLoaiSp(String loaiSp) {
        this.loaiSp = loaiSp;
    }

    public String getKichThuoc() {
        return kichThuoc;
    }

    public void setKichThuoc(String kichThuoc) {
        this.kichThuoc = kichThuoc;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public String getChatLieu() {
        return chatLieu;
    }

    public void setChatLieu(String chatLieu) {
        this.chatLieu = chatLieu;
    }

    public String getDonGia() {
        return donGia;
    }

    public void setDonGia(String donGia) {
        this.donGia = donGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    
    public boolean isDaXoa() {
        return daXoa;
    }

    public void setDaXoa(boolean daXoa) {
        this.daXoa = daXoa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

}
