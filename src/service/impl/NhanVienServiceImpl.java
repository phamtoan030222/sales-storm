/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import dao.NhanVienDAO;
import model.NhanVien;
import service.NhanVienService;

/**
 *
 * @author phamd
 */
public class NhanVienServiceImpl implements NhanVienService{
    
    private final NhanVienDAO nhanVienDAO = new NhanVienDAO();

    @Override
    public boolean dangNhap(String tenDangNhap, String matKhau) {
        NhanVien nv = nhanVienDAO.dangNhap(tenDangNhap, matKhau);
        if(nv != null){
            return true;
        }
        return false;      
    }
    
    public boolean timNhanVienTheoEmail(String email) {
        NhanVien nv = nhanVienDAO.timNhanVienTheoEmail(email);
        if(nv != null){
            return true;
        }
        return false;
    }

    @Override
    public boolean resetPassword(String email, String newPassword) {
        return nhanVienDAO.capNhatMatKhau(email, newPassword);
    }
}
