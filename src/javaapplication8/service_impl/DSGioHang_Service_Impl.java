/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication8.service_impl;

import java.util.ArrayList;
import javaapplication8.dao.DSGioHang_dao;
import javaapplication8.model.Model_DSGioHang;
import javaapplication8.service.DSGioHang_Service;

/**
 *
 * @author nguyentuananh
 */
public class DSGioHang_Service_Impl implements DSGioHang_Service {
    private final DSGioHang_dao ds_ghdao = new DSGioHang_dao();

    @Override
    public ArrayList<Model_DSGioHang> getAllGH(int idhoadon) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int themGiohang(Model_DSGioHang u) {
        int themGiohang = ds_ghdao.themGiohang(u);
        return themGiohang;
    }

    @Override
    public int XoaSanPham(int id) {
        int XoaSanPham = ds_ghdao.XoaSanPham(id);
        return XoaSanPham;
    }

    @Override
    public int suaSptronggiohang(int soluong, int id) {
    int suaSotronggiohang = ds_ghdao.suaSptronggiohang(soluong, id);
    return suaSotronggiohang;
    }
    
    
}
