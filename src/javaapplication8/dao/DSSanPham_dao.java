/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication8.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javaapplication8.model.Model_DSSanPham;
import javaapplication8.until.DBConnect;

/**
 *
 * @author nguyentuananh
 */
public class DSSanPham_dao {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    public DSSanPham_dao() {
        con = DBConnect.getConnection();
    }

    public ArrayList<Model_DSSanPham> getAll() {
        ArrayList<Model_DSSanPham> list = new ArrayList<>();
        sql = "SELECT spct.id,\n"
                + "    sp.TEN AS Ten_San_Pham,\n"
                + "    cl.TEN AS Chat_Lieu,\n"
                + "    kt.TEN AS Kich_Thuoc,\n"
                + "    ms.TEN AS Mau_Sac,\n"
                + "    spct.SO_LUONG,\n"
                + "    spct.DON_GIA\n"
                + "FROM SAN_PHAM_CHI_TIET spct\n"
                + "JOIN SAN_PHAM sp ON spct.ID_SP = sp.ID\n"
                + "JOIN NHA_CUNG_CAP ncc ON spct.ID_NHA_CUNG_CAP = ncc.ID\n"
                + "JOIN CHAT_LIEU cl ON spct.ID_CHAT_LIEU = cl.ID\n"
                + "JOIN KICH_THUOC kt ON spct.ID_KICH_THUOC = kt.ID\n"
                + "JOIN MAU_SAC ms ON spct.ID_MAU_SAC = ms.ID;";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String tensp, loai, chatlieu, kichthuoc, mausac;
                int soluong, id;
                double dongia;
                id = rs.getInt(1);
                tensp = rs.getString(2);
                chatlieu = rs.getString(3);
                kichthuoc = rs.getString(4);
                mausac = rs.getString(5);
                soluong = rs.getInt(6);
                dongia = rs.getDouble(7);

                list.add(new Model_DSSanPham(tensp, chatlieu, kichthuoc, mausac, soluong, id, dongia));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int suaSoLuong(Model_DSSanPham u) {
        String sql = "UPDATE San_Pham_Chi_Tiet SET SO_LUONG = ? WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, u.getSoluong()); // Đặt giá trị mới cho số lượng
            ps.setInt(2, u.getId()); // Đặt giá trị ID để xác định sản phẩm cần cập nhật
            ps.executeUpdate(); // Thực thi UPDATE
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    public int suaSpSoLuong(int soluong, int id) {
        String sql = "UPDATE spct\n"
                + "SET spct.SO_LUONG = spct.SO_LUONG+?\n"
                + "FROM San_Pham_Chi_Tiet spct\n"
                + "JOIN Hoa_Don_Chi_Tiet hdct ON spct.ID = hdct.ID_SPCT\n"
                + "WHERE hdct.ID = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, soluong); // Số lượng mới
            ps.setInt(2, id); // ID của hóa đơn chi tiết
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Cập nhật số lượng thành công!");
            } else {
                System.out.println("Không tìm thấy sản phẩm để cập nhật.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    public int suaSpSoLuong2(int soluong, int id) {
        String sql = "UPDATE spct\n"
                + "SET spct.SO_LUONG = spct.SO_LUONG-?\n"
                + "FROM San_Pham_Chi_Tiet spct\n"
                + "JOIN Hoa_Don_Chi_Tiet hdct ON spct.ID = hdct.ID_SPCT\n"
                + "WHERE hdct.ID = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, soluong); // Số lượng mới
            ps.setInt(2, id); // ID của hóa đơn chi tiết
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Cập nhật số lượng thành công!");
            } else {
                System.out.println("Không tìm thấy sản phẩm để cập nhật.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
}
