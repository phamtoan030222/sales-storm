/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication8.service_impl;

import java.util.ArrayList;
import javaapplication8.dao.DSSanPham_dao;
import javaapplication8.model.Model_DSSanPham;
import javaapplication8.service.DSSanPham_Service;

/**
 *
 * @author nguyentuananh
 */
public class DSSanPham_Service_Impl implements DSSanPham_Service{
    private final DSSanPham_dao ds_spdao =  new  DSSanPham_dao();
    @Override
    public ArrayList<Model_DSSanPham> getAll() {
        ArrayList<Model_DSSanPham> getAll = ds_spdao.getAll();
        return getAll;
        
    }

    @Override
    public int suaSoLuong(Model_DSSanPham u) {
       int  suaSoLuong = ds_spdao.suaSoLuong(u);
        return suaSoLuong;
    }

    @Override
    public int suaSpSoLuong(int soluong, int id) {
        int suaSpSoLuong = ds_spdao.suaSpSoLuong(soluong, id);
        return suaSpSoLuong;
   }

    @Override
    public int suaSpSoLuong2(int soluong, int id) {
        int suaSpSoLuong2 = ds_spdao.suaSpSoLuong2(soluong, id);
        return suaSpSoLuong2;
   }
    
}
