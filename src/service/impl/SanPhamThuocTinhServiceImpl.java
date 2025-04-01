/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import dao.SanPhamThuocTinhDAO;
import java.util.List;
import model.SanPham_ThuocTinh;
import service.SanPhamThuocTinhService;

/**
 *
 * @author dungc
 */
public class SanPhamThuocTinhServiceImpl implements SanPhamThuocTinhService {

    private final SanPhamThuocTinhDAO dao = new SanPhamThuocTinhDAO();

    @Override
    public List<SanPham_ThuocTinh> layDanhSachThuocTinh(String tableName) {
        return dao.getThuocTinh(tableName);
    }

    @Override
    public boolean addThuocTinh(String tableName, String ten) {
        return dao.addThuocTinh(tableName, ten);
    }

    @Override
    public boolean updateDaXoa(String tableName, String ten) {
        return dao.updateDaXoa(tableName, ten);
    }

    @Override
    public boolean updateThuocTinh(String tableName, String tenCu, String tenMoi) {
        return dao.updateThuocTinh(tableName, tenCu, tenMoi);
    }

    @Override
    public boolean kiemTraTenThuocTinhDaTonTai(String tableName, String tenThuocTinh) {
        return dao.kiemTraTenThuocTinhDaTonTai(tableName, tenThuocTinh);
    }

    @Override
    public List<SanPham_ThuocTinh> getThuocTinhDaXoa(String tableName) {
    return dao.getThuocTinhDaXoa(tableName);
    }

    @Override
    public boolean khoiPhucDaXoa(String tableName, String ten) {
    return dao.khoiPhucDaXoa(tableName, ten);
    }
}
