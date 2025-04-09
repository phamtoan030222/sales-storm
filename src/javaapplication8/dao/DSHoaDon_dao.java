/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication8.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javaapplication8.model.Model_DSHoaDon;
import javaapplication8.until.DBConnect;

/**
 *
 * @author nguyentuananh
 */
public class DSHoaDon_dao {
     private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    public DSHoaDon_dao() {
        con = DBConnect.getConnection();
    }

 

public ArrayList<Model_DSHoaDon> getAllHDChuaTT() {
    ArrayList<Model_DSHoaDon> list = new ArrayList<>();
    sql = """
          SELECT hd.MA_HD, nv.TEN_NV, hd.TEN_KHACH_HANG, hd.NGAY_DAT_HANG, hd.TRANG_THAI   
                    FROM Hoa_Don hd   
                    LEFT JOIN NhanVien nv ON hd.ID_NV = nv.ID 
                      Where hd.TRANG_THAI=0
          """;
    try {
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Định dạng ngày
        while (rs.next()) {
            String mahd = rs.getString("MA_HD");
            String tenNV = rs.getString("TEN_NV");
            String tenKH = rs.getString("TEN_KHACH_HANG");
            

            java.sql.Date sqlDate = rs.getDate("NGAY_DAT_HANG");
            String ngayTao = (sqlDate != null) ? sdf.format(sqlDate) : ""; // Chuyển thành String

            boolean trangThai = rs.getBoolean("TRANG_THAI");

            list.add(new Model_DSHoaDon(tenNV, tenKH, mahd, ngayTao, trangThai));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


public int themhoadon(Model_DSHoaDon u) {
    sql = "INSERT INTO hoa_don (TEN_KHACH_HANG, SDT_NHAN_HANG, NGAY_DAT_HANG, ID_NV, TRANG_THAI) VALUES (?, ?, ?, 1, 0)";
    try {
        ps = con.prepareStatement(sql);
        ps.setObject(1, u.getTenkh());
        ps.setObject(2, u.getSodt());
        ps.setObject(3, u.getNgaytao());

        return ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
        return 0;
    }
}


public int thanhtoanhoadon(Model_DSHoaDon u){
    sql = "update hoa_don \n" +
"          set TRANG_THAI =1\n" +
"          where id = ? ";       
    try {
        ps=con.prepareStatement(sql);
         ps.setInt(1, u.getId());
       
        return ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
        return 0;
    }
}
public ArrayList<Model_DSHoaDon> getAllHD() {
    ArrayList<Model_DSHoaDon> list = new ArrayList<>();
    sql = "SELECT hd.ID, nv.TEN_NV, hd.TEN_KHACH_HANG, hd.SDT_NHAN_HANG,hd.NGAY_DAT_HANG, hd.TRANG_THAI   \n" +
"          FROM Hoa_Don hd   \n" +
"          LEFT JOIN NhanVien nv ON hd.ID_NV = nv.ID" ;

    try {
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Định dạng ngày
        while (rs.next()) {
            int id = rs.getInt("ID");
            String tenNV = rs.getString("TEN_NV");
            String tenKH = rs.getString("TEN_KHACH_HANG");
            String sdt = rs.getString("SDT_NHAN_HANG");

            java.sql.Date sqlDate = rs.getDate("NGAY_DAT_HANG");
            String ngayTao = (sqlDate != null) ? sdf.format(sqlDate) : ""; // Chuyển thành String

            boolean trangThai = rs.getBoolean("TRANG_THAI");

            list.add(new Model_DSHoaDon(tenNV, tenKH, sdt, id, ngayTao, trangThai));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}
}
