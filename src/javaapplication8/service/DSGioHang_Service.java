/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication8.service;

import java.util.ArrayList;
import javaapplication8.model.Model_DSGioHang;

/**
 *
 * @author nguyentuananh
 */
public interface DSGioHang_Service {
    ArrayList<Model_DSGioHang> getAllGH(int idhoadon);
    int themGiohang(Model_DSGioHang u);
    int XoaSanPham(int id);
    int suaSptronggiohang(int soluong, int id);
    
}
