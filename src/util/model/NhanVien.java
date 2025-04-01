/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author phamd
 */
public class NhanVien {
    
    private int iD;
    
    private String ten;
    
    private String maNV;
    
    private String tenDangNhap;
    
    private String matKhau;
    
    private boolean trangThai;
    
    private boolean chucVu;
    
    private String ngaySinh;
    
    private boolean gioiTinh;
    
    private String sDT;
    
    private String diaChi;
    
    private String email;

    public NhanVien() {
    }

    public NhanVien(int iD, String ten, String maNV, String tenDangNhap, String matKhau, boolean trangThai, boolean chucVu, String ngaySinh, boolean gioiTinh, String sDT, String diaChi) {
        this.iD = iD;
        this.ten = ten;
        this.maNV = maNV;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.trangThai = trangThai;
        this.chucVu = chucVu;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.sDT = sDT;
        this.diaChi = diaChi;
    }

    public NhanVien(int iD, String tenDangNhap, String matKhau) {
        this.iD = iD;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
    }

    public NhanVien(int iD, String tenDangNhap, String matKhau, String email) {
        this.iD = iD;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.email = email;
    }

    

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public boolean isChucVu() {
        return chucVu;
    }

    public void setChucVu(boolean chucVu) {
        this.chucVu = chucVu;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getsDT() {
        return sDT;
    }

    public void setsDT(String sDT) {
        this.sDT = sDT;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
