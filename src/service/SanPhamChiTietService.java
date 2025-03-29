/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import dao.SanPhamChiTietDAO;
import java.util.List;
import model.SanPham_ChiTiet;
import model.SanPham_ThuocTinh;

/**
 *
 * @author dungc
 */
public interface SanPhamChiTietService {

    List<SanPham_ThuocTinh> getLoaiSanPham();

    List<SanPham_ThuocTinh> getLoaiMauSac();

    List<SanPham_ThuocTinh> getLoaiKichThuoc();

    List<SanPham_ThuocTinh> getLoaiChatLieu();

    List<SanPham_ChiTiet> layDanhSachSanPhamChiTiet();

    boolean themSanPhamChiTiet(String ma, String ten, int idsp, int idkt, int idms, int idcl, String donGia, int soLuong);

    boolean kiemTraMaSPCTDaTonTai(String ma);

    boolean updateSanPham(String ma, String ten, int idsp, int idkt, int idms, int idcl, String donGia, int soLuong);

    boolean xoaSPCT(String ma);

    List<SanPham_ChiTiet> layDanhSachSanPhamChiTietDaXoa();
    
    boolean khoiPhucDaXoaSPCT(String ma, int idsp, int idms, int idcl, int idkt);
    
    List<SanPham_ChiTiet> loadTableTimKiemTuongDoi(String keyword);
}
