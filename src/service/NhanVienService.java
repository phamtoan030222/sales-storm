/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.NhanVienDAO;
import model.NhanVien;


/**
 *
 * @author phamd
 */
public interface NhanVienService {
    
    boolean dangNhap(String tenDangNhap, String matKhau);
    
    boolean timNhanVienTheoEmail(String email);
    
    boolean resetPassword(String email, String newPassword);
    
}


