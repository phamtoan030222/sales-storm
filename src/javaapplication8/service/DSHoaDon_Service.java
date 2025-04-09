/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication8.service;

import java.util.ArrayList;
import javaapplication8.model.Model_DSHoaDon;

      

/**
 *
 * @author nguyentuananh
 */
public interface DSHoaDon_Service {
    ArrayList<Model_DSHoaDon>getAllHDChuaTT();
    int themhoadon(Model_DSHoaDon u );
    int thanhtoanhoadon(Model_DSHoaDon u );
    ArrayList<Model_DSHoaDon> getAllHD();
    
    
    
}
