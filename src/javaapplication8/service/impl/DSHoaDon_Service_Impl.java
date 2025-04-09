/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication8.service_impl;

import java.util.ArrayList;
import java.util.List;
import javaapplication8.dao.DSHoaDon_dao;
import javaapplication8.model.Model_DSHoaDon;
import javaapplication8.service.DSHoaDon_Service;

/**
 *
 * @author nguyentuananh
 */
public class DSHoaDon_Service_Impl implements DSHoaDon_Service {
 private final DSHoaDon_dao ds_hdDao = new DSHoaDon_dao();
    @Override
    public ArrayList<Model_DSHoaDon> getAllHDChuaTT() {
        ArrayList<Model_DSHoaDon> getAllHDChuaTT = ds_hdDao.getAllHDChuaTT();
        return getAllHDChuaTT();
    }

    @Override
    public int themhoadon(Model_DSHoaDon u) {
        int themhoadon = ds_hdDao.themhoadon(u);
        return themhoadon;
   }

    @Override
    public int thanhtoanhoadon(Model_DSHoaDon u) {
        int thanhtoanhoadon = ds_hdDao.thanhtoanhoadon(u);
        return thanhtoanhoadon;
   }

    @Override
    public ArrayList<Model_DSHoaDon> getAllHD() {
        ArrayList<Model_DSHoaDon> getAllHD = ds_hdDao.getAllHD();
        return getAllHD();
    }
    
}
