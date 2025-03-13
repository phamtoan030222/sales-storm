/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.NhanVien;
import resources.config.DBConnect;

/**
 *
 * @author phamd
 */
public class NhanVienDAO {
    
    private ArrayList<NhanVien> list;
    private Connection conn = null;
    private ResultSet rs = null;
    private String sql;

    public NhanVienDAO() {
        conn = DBConnect.getConnection();
        
        list = new ArrayList<>();
    }
    
    public NhanVien dangNhap(String tenDN, String matKhau){
        sql = """
              SELECT ID, TenDangNhap, MatKhau FROM NhanVien
              WHERE TenDangNhap = ? AND MatKhau = ?;
              """;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tenDN);
            ps.setString(2, matKhau);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new NhanVien(rs.getInt("ID"), rs.getString("TenDangNhap"),
                                    rs.getString("MatKhau"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public NhanVien timNhanVienTheoEmail(String email) {
    String sql = "SELECT ID, TenDangNhap, MatKhau, Email FROM NhanVien WHERE Email = ?";
    
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            return new NhanVien(
                rs.getInt("ID"),
                rs.getString("TenDangNhap"),
                rs.getString("MatKhau"),
                rs.getString("Email")
            );
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
    
    public boolean capNhatMatKhau(String email, String newPassword) {
        sql = "UPDATE NhanVien SET MatKhau = ? WHERE Email = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newPassword);
            ps.setString(2, email);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    
}
