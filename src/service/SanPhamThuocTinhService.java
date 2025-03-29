/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import dao.SanPhamThuocTinhDAO;
import java.util.List;
import model.SanPham_ThuocTinh;

/**
 *
 * @author dungc
 */
public interface SanPhamThuocTinhService {

    List<SanPham_ThuocTinh> layDanhSachThuocTinh(String tableName);

    boolean addThuocTinh(String tableName, String ten);

    boolean updateDaXoa(String tableName, String ten);

    boolean updateThuocTinh(String tableName, String tenCu, String tenMoi);

    boolean kiemTraTenThuocTinhDaTonTai(String tableName, String tenThuocTinh);
    
    List<SanPham_ThuocTinh> getThuocTinhDaXoa(String tableName);
    
    boolean khoiPhucDaXoa(String tableName, String ten);
}
