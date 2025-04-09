/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication8.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javaapplication8.model.SanPham_ThuocTinh;
import javaapplication8.until.DBConnect;

/**
 *
 * @author dungc
 */
public class SanPhamThuocTinhDao {
    private ArrayList<SanPham_ThuocTinh> list;
    private PreparedStatement ps = null;
    private Connection conn = null;
    private ResultSet rs = null;
    private String sql;

    public SanPhamThuocTinhDao() {
        conn = DBConnect.getConnection();

        list = new ArrayList<>();
    }

    public ArrayList<SanPham_ThuocTinh> getThuocTinh(String tableName) {
        ArrayList<SanPham_ThuocTinh> list = new ArrayList<>();
        sql = "SELECT id,ma, ten FROM " + tableName + " WHERE DA_XOA = 0";

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new SanPham_ThuocTinh(rs.getInt("id"),rs.getString("ma"), rs.getString("ten")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean addThuocTinh(String tableName, String ten) {
        sql = "insert into " + tableName + "(TEN) values (?)";
        try {
            ps = conn.prepareStatement(sql);

            ps.setString(1, ten);

            return ps.executeUpdate() > 0;//thêm/sửa/xoá:executeUpđate()
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateDaXoa(String tableName, String ten) {

        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;

        try {

            conn.setAutoCommit(false); // Tắt auto commit để kiểm soát transaction

            // Câu lệnh UPDATE đầu tiên: Cập nhật DA_XOA trong tableName
            String sql1 = "UPDATE " + tableName + " SET DA_XOA = 1 WHERE TEN = ?";
            ps1 = conn.prepareStatement(sql1);
            ps1.setString(1, ten);
            int rows1 = ps1.executeUpdate(); // Thực thi lệnh 1

            // Câu lệnh UPDATE thứ hai: Cập nhật DA_XOA trong bảng San_Pham_Chi_Tiet
            String sql2 = "UPDATE San_Pham_Chi_Tiet SET DA_XOA = 1 "
                    + "WHERE ID_" + tableName + " IN (SELECT ID FROM " + tableName + " WHERE DA_XOA = 1)";
            ps2 = conn.prepareStatement(sql2);
            int rows2 = ps2.executeUpdate(); // Thực thi lệnh 2

            conn.commit(); // Nếu cả hai UPDATE thành công, commit transaction
            return rows1 > 0 || rows2 > 0; // Trả về true nếu có ít nhất 1 dòng được cập nhật

        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Nếu có lỗi, rollback để không bị cập nhật lỗi một phần
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } 
        return false;
    }

    public boolean updateThuocTinh(String tableName, String tenCu, String tenMoi) {
        sql = "UPDATE " + tableName + " SET ten = ? WHERE ten = ?";

        try {
            ps = conn.prepareStatement(sql);

            ps.setNString(1, tenMoi);
            ps.setNString(2, tenCu);

            return ps.executeUpdate() > 0;//thêm/sửa/xoá:executeUpđate()

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean kiemTraTenThuocTinhDaTonTai(String tableName, String tenThuocTinh) {
        String sql = "SELECT COUNT(*) FROM " + tableName + " WHERE ten = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setNString(1, tenThuocTinh);
            rs = ps.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                return true; // Tên đã tồn tại
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Tên chưa tồn tại
    }

//    public ArrayList<SanPham_ThuocTinh> getThuocTinhDaXoa(String tableName) {
//        ArrayList<SanPham_ThuocTinh> list = new ArrayList<>();
//        sql = "SELECT ten FROM " + tableName + " WHERE DA_XOA = 1";
//
//        try {
//            ps = conn.prepareStatement(sql);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                list.add(new SanPham_ThuocTinh(rs.getString(1)));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
    
    public boolean khoiPhucDaXoa(String tableName, String ten) {

        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;

        try {

            conn.setAutoCommit(false); // Tắt auto commit để kiểm soát transaction

            // Câu lệnh UPDATE đầu tiên: Cập nhật DA_XOA trong tableName
            String sql1 = "UPDATE " + tableName + " SET DA_XOA = 0 WHERE TEN = ?";
            ps1 = conn.prepareStatement(sql1);
            ps1.setString(1, ten);
            int rows1 = ps1.executeUpdate(); // Thực thi lệnh 1

            // Câu lệnh UPDATE thứ hai: Cập nhật DA_XOA trong bảng San_Pham_Chi_Tiet
            String sql2 = "UPDATE San_Pham_Chi_Tiet SET DA_XOA = 0 "
                    + "WHERE ID_" + tableName + " IN (SELECT ID FROM " + tableName + " WHERE DA_XOA = 0)";
            ps2 = conn.prepareStatement(sql2);
            int rows2 = ps2.executeUpdate(); // Thực thi lệnh 2

            conn.commit(); // Nếu cả hai UPDATE thành công, commit transaction
            return rows1 > 0 || rows2 > 0; // Trả về true nếu có ít nhất 1 dòng được cập nhật

        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Nếu có lỗi, rollback để không bị cập nhật lỗi một phần
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } 
        return false;
    }

}
