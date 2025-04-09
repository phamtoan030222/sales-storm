/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication8.service.serviceimpl;

import java.util.List;
import javaapplication8.dao.SanPhamThuocTinhDao;
import javaapplication8.model.SanPham_ThuocTinh;
import javaapplication8.service.SanPhamThuocTinhService;

/**
 *
 * @author dungc
 */
public class SanPhamThuocTinhServiceImpl implements SanPhamThuocTinhService {

    private final SanPhamThuocTinhDao dao = new SanPhamThuocTinhDao();

    @Override
    public List<SanPham_ThuocTinh> layDanhSachThuocTinh(String tableName) {
        return dao.getThuocTinh(tableName);
    }

}
