/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication8.service;

import java.util.ArrayList;
import javaapplication8.model.Model_DSSanPham;

/**
 *
 * @author nguyentuananh
 */
public interface DSSanPham_Service {

    ArrayList<Model_DSSanPham> getAll();

     int suaSoLuong(Model_DSSanPham u);

    int suaSpSoLuong(int soluong, int id);

    int suaSpSoLuong2(int soluong, int id);

}
