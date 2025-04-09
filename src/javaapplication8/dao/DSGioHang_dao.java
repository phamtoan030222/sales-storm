/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication8.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javaapplication8.model.Model_DSGioHang;
import javaapplication8.until.DBConnect;

/**
 *
 * @author nguyentuananh
 */
public class DSGioHang_dao {
    
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    public DSGioHang_dao() {
        con = DBConnect.getConnection();
    }

//    public ArrayList<Model_DSGioHang> getAllGH(int idhoadon) {
//        ArrayList<Model_DSGioHang> list = new ArrayList<>();
//        String sql = "SELECT \n"
//                + "    hdct.id, sp.ten, hdct.SO_LUONG, ms.TEN, hdct.DON_GIA,\n"
//                + "    hdct.SO_LUONG * hdct.DON_GIA AS thanhtien, \n"
//                + "    hd.TRANG_THAI \n"
//                + "FROM Hoa_Don_Chi_Tiet hdct \n"
//                + "    JOIN San_Pham_Chi_Tiet spct ON hdct.ID_SPCT = spct.ID \n"
//                + "    JOIN San_Pham sp ON spct.ID_SP = sp.ID \n"
//                + "    JOIN Hoa_Don hd on hd.ID = hdct.ID_HD \n"
//                + "    JOIN Mau_Sac ms on ms.ID = spct.ID_MAU_SAC\n"
//                + "WHERE hdct.ID_HD = ?";
//
//        try (PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setInt(1, idhoadon);
//            try (ResultSet rs = ps.executeQuery()) {
//                while (rs.next()) {
//
//                    int id = rs.getInt(1);
//                    String tensp = rs.getString(2);
//                    int soluong = rs.getInt(3);
//                    String mausac = rs.getString(4);
//                    double dongia = rs.getDouble(5);
//                    double thanhtien = rs.getDouble(6);
//                    boolean trangthai = rs.getBoolean(7);
//
//                    list.add(new Model_DSGioHang(tensp, mausac, soluong, id, dongia, thanhtien, trangthai));
//
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }

//    public int themGiohang(Model_DSGioHang u) {
//        String sql = "INSERT INTO Hoa_Don_Chi_Tiet (ID_HD, ID_SPCT, SO_LUONG, DON_GIA) VALUES (?, ?, ?, ?)";
//        try {
//            ps = con.prepareStatement(sql);
//            ps.setInt(1, u.getIdhd());       // ID hóa đơn
//            ps.setInt(2, u.getIdspct());     // ID sản phẩm chi tiết
//            ps.setInt(3, u.getSoluong());    // Số lượng
//            ps.setDouble(4, u.getDongia());  // Đơn giá
//            ps.execute();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return 0;
//        }
//         return 1;
//    }

    public int XoaSanPham(int id) {
        String sql = "DELETE FROM Hoa_Don_Chi_Tiet WHERE ID = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, id);
            return ps.executeUpdate(); // Trả về số dòng bị xóa (thường là 1 nếu xóa thành công)
        } catch (Exception e) {
            e.printStackTrace(); // In lỗi ra console để debug
            return 0; // Trả về 0 nếu có lỗi
        }
    }

    public int suaSptronggiohang(int soluong, int id) {
        String sql = "UPDATE Hoa_Don_Chi_Tiet\n"
                + "SET SO_LUONG = ?\n"
                + "WHERE ID = ?;";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, soluong); // Số lượng mới
            ps.setInt(2, id); // ID của hóa đơn chi tiết
            return ps.executeUpdate(); // Trả về số dòng bị xóa (thường là 1 nếu xóa thành công)
        } catch (Exception e) {
            e.printStackTrace(); // In lỗi ra console để debug
            return 0; // Trả về 0 nếu có lỗi
        }

    }
}
