/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import dao.SanPhamChiTietDAO;
import dao.SanPhamThuocTinhDAO;
import java.util.List;
import model.SanPham_ChiTiet;
import model.SanPham_ThuocTinh;
import service.SanPhamChiTietService;

/**
 *
 * @author dungc
 */
public class SanPhamChiTietServiceImpl implements SanPhamChiTietService {

    private final SanPhamChiTietDAO sanPhamChiTietDAO = new SanPhamChiTietDAO();
    private final SanPhamThuocTinhDAO sanPhamThuocTinhDAO = new SanPhamThuocTinhDAO();

    @Override
    public List<SanPham_ChiTiet> layDanhSachSanPhamChiTiet() {
        return sanPhamChiTietDAO.getSanPhamChiTiet();
    }

    @Override
    public List<SanPham_ThuocTinh> getLoaiSanPham() {
        return sanPhamThuocTinhDAO.getThuocTinh("San_Pham");
    }

    @Override
    public List<SanPham_ThuocTinh> getLoaiMauSac() {
        return sanPhamThuocTinhDAO.getThuocTinh("Mau_Sac");
    }

    @Override
    public List<SanPham_ThuocTinh> getLoaiKichThuoc() {
        return sanPhamThuocTinhDAO.getThuocTinh("Kich_Thuoc");
    }

    @Override
    public List<SanPham_ThuocTinh> getLoaiChatLieu() {
        return sanPhamThuocTinhDAO.getThuocTinh("Chat_Lieu");
    }

    @Override
    public boolean themSanPhamChiTiet(String ma, String ten, int idsp, int idkt, int idms, int idcl, String donGia, int soLuong) {
        return sanPhamChiTietDAO.addSanPhamChiTiet(ma, ten, idsp, idkt, idms, idcl, donGia, soLuong);
    }

    @Override
    public boolean kiemTraMaSPCTDaTonTai(String ma) {
        return sanPhamChiTietDAO.kiemTraMaSPCTDaTonTai(ma);
    }

    @Override
    public boolean updateSanPham(String ma, String ten, int idsp, int idkt, int idms, int idcl, String donGia, int soLuong) {
        return sanPhamChiTietDAO.updateSanPham(ma, ten, idsp, idkt, idms, idcl, donGia, soLuong);
    }

    @Override
    public boolean xoaSPCT(String ma) {
        return sanPhamChiTietDAO.xoaSPCT(ma);
    }

    @Override
    public List<SanPham_ChiTiet> layDanhSachSanPhamChiTietDaXoa() {
        return sanPhamChiTietDAO.getSanPhamChiTietDaXoa();
    }

    @Override
    public boolean khoiPhucDaXoaSPCT(String ma, int idsp, int idms, int idcl, int idkt) {
        return sanPhamChiTietDAO.khoiPhucDaXoaSPCT(ma, idsp, idms, idcl, idkt);
    }

    @Override
    public List<SanPham_ChiTiet> loadTableTimKiemTuongDoi(String keyword) {
        return sanPhamChiTietDAO.loadTableTimKiemTuongDoi(keyword);
    }

}
