/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package javaapplication8.service;

import java.util.List;
import javaapplication8.model.SanPham_ThuocTinh;

/**
 *
 * @author dungc
 */
public interface SanPhamThuocTinhService {
    List<SanPham_ThuocTinh> layDanhSachThuocTinh(String tableName);
}
