/*
d * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import dao.SanPhamThuocTinhDAO;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import model.SanPham_ChiTiet;
import model.SanPham_ThuocTinh;
import service.SanPhamChiTietService;
import service.SanPhamThuocTinhService;
import service.impl.SanPhamChiTietServiceImpl;
import service.impl.SanPhamThuocTinhServiceImpl;
import util.STT;
import util.ValidationUtil;

/**
 *
 * @author phamd
 */
public class HomeView extends javax.swing.JFrame {

    /**
     * Creates new form HomeView
     */
    private JLabel selectedLabel = null; // Lưu label đang chọn
    private final Color defaultTextColor = new Color(248, 248, 248);  // Trắng nhạt
    private final Color hoverTextColor = new Color(255, 152, 0);  // Cam nổi bật khi hover
    private final Color selectedTextColor = new Color(255, 255, 255);  // Trắng sáng khi chọn
    private final Color defaultBackground = new Color(30, 30, 30); // Đen xám sang trọng
    private final Color hoverBackground = new Color(51, 51, 51); // Xám trung tính khi hover
    private final Color selectedBackground = new Color(255, 61, 0); // Đỏ cam rực rỡ khi chọn

    private final SanPhamThuocTinhService service_spthuoctinh = new SanPhamThuocTinhServiceImpl();
    private final SanPhamChiTietService service_spchitiet = new SanPhamChiTietServiceImpl();
    
    private HashMap<String, Integer> sanPhamMap = new HashMap<>();
    private HashMap<String, Integer> mauSacMap = new HashMap<>();
    private HashMap<String, Integer> kichThuocMap = new HashMap<>();
    private HashMap<String, Integer> chatLieuMap = new HashMap<>();
    
    public HomeView() {
        initComponents();
        this.setTitle("Hệ thống quản lý bán hàng");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        mainPanel.setLayout(new CardLayout());// Đặt CardLayout cho mainPanel

        // Thêm các JPanel vào CardLayout với ID duy nhất
        mainPanel.add(panelThongKe, "panelThongKe");
        mainPanel.add(panelNhanVien, "panelNhanVien");
        mainPanel.add(panelHoaDon, "panelHoaDon");
        mainPanel.add(panelKhachHang, "panelKhachHang");
        mainPanel.add(panelLichSu, "panelLichSu");
        mainPanel.add(panelKhuyenMai, "panelKhuyenMai");
        mainPanel.add(panelDoiMK, "panelDoiMK");
        mainPanel.add(panelDangXuat, "panelDangXuat");
        mainPanel.add(panelSanPham, "panelSanPham");
        
        addLabelClickListener(lbl_thongKe, "panelThongKe");
        addLabelClickListener(lbl_nhanVien, "panelNhanVien");
        addLabelClickListener(lbl_hoaDon, "panelHoaDon");
        addLabelClickListener(lbl_khachHang, "panelKhachHang");
        addLabelClickListener(lbl_lichSu, "panelLichSu");
        addLabelClickListener(lbl_khuyenMai, "panelKhuyenMai");
        addLabelClickListener(lbl_doiMK, "panelDoiMK");
        addLabelClickListener(lbl_dangXuat, "panelDangXuat");
        addLabelClickListener(lbl_sanPham, "panelSanPham");
        
        rdo_mausac.setSelected(true);
          lbl_dsthuoctinh.setText("DANH SÁCH THUỘC TÍNH MÀU SẮC");
        xuLyChonRadioButton();

        //fill cbobox 
        loadComboboxLoaiSanPham();
        loadComboboxLoaiMauSac();
        loadComboboxLoaiKichThuoc();
        loadComboboxLoaiChatlieu();
        
        fillTable_SPChiTiet(service_spchitiet.layDanhSachSanPhamChiTiet());
        
        btn_khoiphucthuoctinh.setVisible(false);
        btn_khoiphucspct.setVisible(false);
        btn_backthuoctinh.setVisible(false);
        btn_trovespct.setVisible(false);
        
        txt_timkiemsanpham.getDocument().addDocumentListener(new DocumentListener() {
            
            @Override
            public void insertUpdate(DocumentEvent e) {
                search();
            }
            
            @Override
            public void removeUpdate(DocumentEvent e) {
                search();
            }
            
            @Override
            public void changedUpdate(DocumentEvent e) {
                search();
            }
            
        });
    }
    
    private void capNhatBangThuocTinh(String tableName, String loaiTt) {
        List<SanPham_ThuocTinh> danhSach = service_spthuoctinh.layDanhSachThuocTinh(tableName);
        fillTable_SPThuocTinh(danhSach, loaiTt);
    }
    
    private void fillTable_SPThuocTinh(List<SanPham_ThuocTinh> danhSach, String loaiTt) {
        DefaultTableModel model = (DefaultTableModel) tbl_thuoctinh.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ

        for (SanPham_ThuocTinh sp : danhSach) {
            model.addTableModelListener(e -> STT.updateSTT(model));
            model.addRow(new Object[]{
                null,
                loaiTt,
                sp.getTen()});
        }
    }
    
    void xuLyChonRadioButton() {
        if (rdo_mausac.isSelected()) {
            capNhatBangThuocTinh("Mau_Sac", "Màu sắc");
        } else if (rdo_loaisp.isSelected()) {
            capNhatBangThuocTinh("San_Pham", "Sản phẩm");
        } else if (rdo_kichthuoc.isSelected()) {
            capNhatBangThuocTinh("Kich_Thuoc", "Kích thước");
        } else if (rdo_chatlieu.isSelected()) {
            capNhatBangThuocTinh("Chat_Lieu", "Chất liệu");
        }
    }
    
    private String getSelectedTableName() {
        if (rdo_mausac.isSelected()) {
            return "Mau_Sac";
        } else if (rdo_loaisp.isSelected()) {
            return "San_Pham";
        } else if (rdo_kichthuoc.isSelected()) {
            return "Kich_Thuoc";
        } else if (rdo_chatlieu.isSelected()) {
            return "Chat_Lieu";
        }
        return null;
    }
    
    private void xuLyChonRadioButton1() {
        if (rdo_mausac.isSelected()) {
            layDanhSachDaXoa("Mau_Sac", "Màu sắc");
        } else if (rdo_loaisp.isSelected()) {
            layDanhSachDaXoa("San_Pham", "Sản phẩm");
        } else if (rdo_kichthuoc.isSelected()) {
            layDanhSachDaXoa("Kich_Thuoc", "Kích thước");
        } else if (rdo_chatlieu.isSelected()) {
            layDanhSachDaXoa("Chat_Lieu", "Chất liệu");
        }
    }
    
    private void fillTable_SPChiTiet(List<SanPham_ChiTiet> ds) {
        DefaultTableModel model = (DefaultTableModel) tbl_spchitiet.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ

        for (SanPham_ChiTiet spct : ds) {
            model.addTableModelListener(e -> STT.updateSTT(model));
            model.addRow(new Object[]{
                null,
                spct.getMaSp(),
                spct.getTenSp(),
                spct.getLoaiSp(),
                spct.getKichThuoc(),
                spct.getMauSac(),
                spct.getChatLieu(),
                spct.getDonGia(),
                spct.getSoLuong()
            });
        }
    }
    
    private void search() {
        String keyword = txt_timkiemsanpham.getText().toLowerCase();
        DefaultTableModel model = (DefaultTableModel) tbl_spchitiet.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ
        List<SanPham_ChiTiet> list = service_spchitiet.loadTableTimKiemTuongDoi(keyword);
        for (SanPham_ChiTiet spct : list) {
            model.addTableModelListener(e -> STT.updateSTT(model));
            model.addRow(new Object[]{
                null,
                spct.getMaSp(),
                spct.getTenSp(),
                spct.getLoaiSp(),
                spct.getKichThuoc(),
                spct.getMauSac(),
                spct.getChatLieu(),
                spct.getDonGia(),
                spct.getSoLuong()
        });
        }
    }


/**
 * This method is called from within the constructor to initialize the form.
 * WARNING: Do NOT modify this code. The content of this method is always
 * regenerated by the Form Editor.
 */
@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbl_menu = new javax.swing.JPanel();
        lbl_thongKe = new javax.swing.JLabel();
        lbl_sanPham = new javax.swing.JLabel();
        lbl_nhanVien = new javax.swing.JLabel();
        lbl_hoaDon = new javax.swing.JLabel();
        lbl_khachHang = new javax.swing.JLabel();
        lbl_lichSu = new javax.swing.JLabel();
        lbl_khuyenMai = new javax.swing.JLabel();
        lbl_doiMK = new javax.swing.JLabel();
        lbl_dangXuat = new javax.swing.JLabel();
        mainPanel = new javax.swing.JPanel();
        panelThongKe = new javax.swing.JPanel();
        panelNhanVien = new javax.swing.JPanel();
        panelKhuyenMai = new javax.swing.JPanel();
        panelHoaDon = new javax.swing.JPanel();
        panelKhachHang = new javax.swing.JPanel();
        panelLichSu = new javax.swing.JPanel();
        panelDoiMK = new javax.swing.JPanel();
        panelDangXuat = new javax.swing.JPanel();
        panelSanPham = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txt_maspct = new javax.swing.JTextField();
        txt_tenspct = new javax.swing.JTextField();
        txt_dongiasp = new javax.swing.JTextField();
        txt_soluongsp = new javax.swing.JTextField();
        cbx_loaisanpham = new javax.swing.JComboBox<>();
        cbx_mausac = new javax.swing.JComboBox<>();
        cbx_kichthuoc = new javax.swing.JComboBox<>();
        cbx_chatlieu = new javax.swing.JComboBox<>();
        lbl_tbmasp = new javax.swing.JLabel();
        lbl_tbtensp = new javax.swing.JLabel();
        lbl_tbdongiasp = new javax.swing.JLabel();
        lbl_tbsoluong = new javax.swing.JLabel();
        btn_themspct = new javax.swing.JButton();
        btn_suaspct = new javax.swing.JButton();
        btn_xoaspct = new javax.swing.JButton();
        btn_resetthongtinchitietsp = new javax.swing.JButton();
        btn_themnhanhmausac = new javax.swing.JButton();
        btn_themnhanhchatlieu = new javax.swing.JButton();
        btn_themnhanhkichthuoc = new javax.swing.JButton();
        btn_themnhanhloaisp = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        lbl_ttspct = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txt_timkiemsanpham = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_spchitiet = new javax.swing.JTable();
        btn_spctdaxoa = new javax.swing.JButton();
        btn_khoiphucspct = new javax.swing.JButton();
        btn_trovespct = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txt_tenthuoctinh = new javax.swing.JTextField();
        rdo_kichthuoc = new javax.swing.JRadioButton();
        rdo_mausac = new javax.swing.JRadioButton();
        rdo_loaisp = new javax.swing.JRadioButton();
        rdo_chatlieu = new javax.swing.JRadioButton();
        btn_suathuoctinh = new javax.swing.JButton();
        btn_xoathuoctinh = new javax.swing.JButton();
        btn_themthuoctinh = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbl_thongbaothuoctinh = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_thuoctinh = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        lbl_dsthuoctinh = new javax.swing.JLabel();
        btn_dsdaxoa = new javax.swing.JButton();
        btn_khoiphucthuoctinh = new javax.swing.JButton();
        btn_backthuoctinh = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/img/rsz_logo-fotor-bg-remover-2025031441556.png"))); // NOI18N
        jLabel1.setText("jLabel1");

        lbl_menu.setPreferredSize(new java.awt.Dimension(75, 548));

        lbl_thongKe.setFont(new java.awt.Font("Roboto Slab SemiBold", 1, 14)); // NOI18N
        lbl_thongKe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_thongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon/thongke.png"))); // NOI18N
        lbl_thongKe.setText("THỐNG KÊ");
        lbl_thongKe.setAlignmentX(0.5F);
        lbl_thongKe.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        lbl_thongKe.setDisplayedMnemonicIndex(0);
        lbl_thongKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_thongKeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_thongKeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_thongKeMouseExited(evt);
            }
        });

        lbl_sanPham.setFont(new java.awt.Font("Roboto Slab SemiBold", 1, 14)); // NOI18N
        lbl_sanPham.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_sanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon/thongke.png"))); // NOI18N
        lbl_sanPham.setText("SẢN PHẨM");
        lbl_sanPham.setAlignmentX(0.5F);
        lbl_sanPham.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        lbl_sanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_sanPhamMouseClicked(evt);
            }
        });

        lbl_nhanVien.setFont(new java.awt.Font("Roboto Slab SemiBold", 1, 14)); // NOI18N
        lbl_nhanVien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_nhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon/thongke.png"))); // NOI18N
        lbl_nhanVien.setText("NHÂN VIÊN");
        lbl_nhanVien.setAlignmentX(0.5F);
        lbl_nhanVien.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        lbl_nhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_nhanVienMouseClicked(evt);
            }
        });

        lbl_hoaDon.setFont(new java.awt.Font("Roboto Slab SemiBold", 1, 14)); // NOI18N
        lbl_hoaDon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_hoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon/thongke.png"))); // NOI18N
        lbl_hoaDon.setText("HÓA ĐƠN");
        lbl_hoaDon.setAlignmentX(0.5F);
        lbl_hoaDon.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        lbl_hoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_hoaDonMouseClicked(evt);
            }
        });

        lbl_khachHang.setFont(new java.awt.Font("Roboto Slab SemiBold", 1, 14)); // NOI18N
        lbl_khachHang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_khachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon/thongke.png"))); // NOI18N
        lbl_khachHang.setText(" KHÁCH HÀNG");
        lbl_khachHang.setAlignmentX(0.5F);
        lbl_khachHang.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        lbl_khachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_khachHangMouseClicked(evt);
            }
        });

        lbl_lichSu.setFont(new java.awt.Font("Roboto Slab SemiBold", 1, 14)); // NOI18N
        lbl_lichSu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_lichSu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon/thongke.png"))); // NOI18N
        lbl_lichSu.setText("LỊCH SỬ");
        lbl_lichSu.setAlignmentX(0.5F);
        lbl_lichSu.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        lbl_lichSu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_lichSuMouseClicked(evt);
            }
        });

        lbl_khuyenMai.setFont(new java.awt.Font("Roboto Slab SemiBold", 1, 14)); // NOI18N
        lbl_khuyenMai.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_khuyenMai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon/thongke.png"))); // NOI18N
        lbl_khuyenMai.setText("KHUYẾN MẠI");
        lbl_khuyenMai.setAlignmentX(0.5F);
        lbl_khuyenMai.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        lbl_khuyenMai.setName(""); // NOI18N
        lbl_khuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_khuyenMaiMouseClicked(evt);
            }
        });

        lbl_doiMK.setFont(new java.awt.Font("Roboto Slab SemiBold", 1, 14)); // NOI18N
        lbl_doiMK.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_doiMK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon/thongke.png"))); // NOI18N
        lbl_doiMK.setText("ĐỔI MẬT KHẨU");
        lbl_doiMK.setAlignmentX(0.5F);
        lbl_doiMK.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        lbl_doiMK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_doiMKMouseClicked(evt);
            }
        });

        lbl_dangXuat.setFont(new java.awt.Font("Roboto Slab SemiBold", 1, 14)); // NOI18N
        lbl_dangXuat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_dangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon/thongke.png"))); // NOI18N
        lbl_dangXuat.setText("ĐĂNG XUẤT");
        lbl_dangXuat.setAlignmentX(0.5F);
        lbl_dangXuat.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        lbl_dangXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_dangXuatMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout lbl_menuLayout = new javax.swing.GroupLayout(lbl_menu);
        lbl_menu.setLayout(lbl_menuLayout);
        lbl_menuLayout.setHorizontalGroup(
            lbl_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_thongKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbl_sanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbl_nhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbl_hoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbl_khachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbl_lichSu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbl_khuyenMai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbl_doiMK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbl_dangXuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        lbl_menuLayout.setVerticalGroup(
            lbl_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lbl_menuLayout.createSequentialGroup()
                .addComponent(lbl_thongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_sanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_nhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_hoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_khachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_lichSu, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_khuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_doiMK, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_dangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 104, Short.MAX_VALUE))
        );

        mainPanel.setBackground(new java.awt.Color(153, 153, 255));
        mainPanel.setName("mainPanel"); // NOI18N
        mainPanel.setLayout(new java.awt.CardLayout());

        panelThongKe.setBackground(new java.awt.Color(255, 153, 255));
        panelThongKe.setName("panelThongKe"); // NOI18N
        panelThongKe.setPreferredSize(new java.awt.Dimension(1048, 548));
        panelThongKe.setLayout(null);
        mainPanel.add(panelThongKe, "panelThongKe");

        panelNhanVien.setBackground(new java.awt.Color(153, 153, 255));
        panelNhanVien.setName("panelNhanVien"); // NOI18N
        panelNhanVien.setLayout(null);
        mainPanel.add(panelNhanVien, "panelNhanVien");

        panelKhuyenMai.setName("panelKhuyenMai"); // NOI18N
        panelKhuyenMai.setLayout(null);
        mainPanel.add(panelKhuyenMai, "panelKhuyenMai");

        panelHoaDon.setName("panelHoaDon"); // NOI18N
        panelHoaDon.setLayout(null);
        mainPanel.add(panelHoaDon, "panelHoaDon");

        panelKhachHang.setBackground(new java.awt.Color(204, 255, 204));
        panelKhachHang.setName("panelKhachHang"); // NOI18N
        panelKhachHang.setLayout(null);
        mainPanel.add(panelKhachHang, "panelKhachHang");

        panelLichSu.setBackground(new java.awt.Color(0, 153, 153));
        panelLichSu.setName("panelLichSu"); // NOI18N
        panelLichSu.setLayout(null);
        mainPanel.add(panelLichSu, "panelLichSu");

        panelDoiMK.setBackground(new java.awt.Color(153, 0, 153));
        panelDoiMK.setName("panelDoiMK"); // NOI18N
        panelDoiMK.setLayout(null);
        mainPanel.add(panelDoiMK, "panelDoiMK");

        panelDangXuat.setBackground(new java.awt.Color(255, 153, 102));
        panelDangXuat.setName("panelDangXuat"); // NOI18N
        panelDangXuat.setLayout(null);
        mainPanel.add(panelDangXuat, "panelDangXuat");

        panelSanPham.setBackground(new java.awt.Color(204, 204, 204));
        panelSanPham.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panelSanPham.setName("panelSanPham"); // NOI18N
        panelSanPham.setLayout(null);

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Quản lý sản phẩm");

        jLabel6.setText("Mã sản phẩm");

        jLabel7.setText("Tên sản phẩm");

        jLabel8.setText("Loại sản phẩm");

        jLabel9.setText("Đơn giá");

        jLabel10.setText("Số lượng");

        jLabel11.setText("Màu sắc");

        jLabel12.setText("Kích thước");

        jLabel13.setText("Chất liệu");

        txt_maspct.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txt_maspctCaretUpdate(evt);
            }
        });

        txt_tenspct.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txt_tenspctCaretUpdate(evt);
            }
        });

        txt_dongiasp.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txt_dongiaspCaretUpdate(evt);
            }
        });

        txt_soluongsp.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txt_soluongspCaretUpdate(evt);
            }
        });

        cbx_loaisanpham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbx_loaisanpham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_loaisanphamActionPerformed(evt);
            }
        });

        cbx_mausac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbx_kichthuoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbx_chatlieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbl_tbmasp.setForeground(new java.awt.Color(255, 0, 51));

        lbl_tbtensp.setForeground(new java.awt.Color(255, 0, 0));

        lbl_tbdongiasp.setForeground(new java.awt.Color(255, 0, 0));

        lbl_tbsoluong.setForeground(new java.awt.Color(255, 51, 51));

        btn_themspct.setText("Thêm");
        btn_themspct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themspctActionPerformed(evt);
            }
        });

        btn_suaspct.setText("Sửa");
        btn_suaspct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaspctActionPerformed(evt);
            }
        });

        btn_xoaspct.setText("Xóa");
        btn_xoaspct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaspctActionPerformed(evt);
            }
        });

        btn_resetthongtinchitietsp.setText("Làm mới");
        btn_resetthongtinchitietsp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetthongtinchitietspActionPerformed(evt);
            }
        });

        btn_themnhanhmausac.setIcon(new javax.swing.ImageIcon("C:\\Users\\dungc\\Desktop\\da1\\+_icon.png")); // NOI18N
        btn_themnhanhmausac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themnhanhmausacActionPerformed(evt);
            }
        });

        btn_themnhanhchatlieu.setIcon(new javax.swing.ImageIcon("C:\\Users\\dungc\\Desktop\\da1\\+_icon.png")); // NOI18N
        btn_themnhanhchatlieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themnhanhchatlieuActionPerformed(evt);
            }
        });

        btn_themnhanhkichthuoc.setIcon(new javax.swing.ImageIcon("C:\\Users\\dungc\\Desktop\\da1\\+_icon.png")); // NOI18N
        btn_themnhanhkichthuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themnhanhkichthuocActionPerformed(evt);
            }
        });

        btn_themnhanhloaisp.setIcon(new javax.swing.ImageIcon("C:\\Users\\dungc\\Desktop\\da1\\+_icon.png")); // NOI18N
        btn_themnhanhloaisp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themnhanhloaispActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel7))
                                .addGap(36, 36, 36)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(lbl_tbdongiasp, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lbl_tbtensp, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txt_dongiasp)
                                                .addComponent(cbx_loaisanpham, 0, 227, Short.MAX_VALUE)
                                                .addComponent(txt_tenspct)))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(45, 45, 45)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_maspct, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                                    .addComponent(lbl_tbmasp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(btn_themnhanhloaisp, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(102, 102, 102)
                                .addComponent(lbl_tbsoluong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel10))
                                .addGap(44, 44, 44)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cbx_chatlieu, javax.swing.GroupLayout.Alignment.LEADING, 0, 247, Short.MAX_VALUE)
                                    .addComponent(cbx_kichthuoc, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbx_mausac, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_soluongsp))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_themnhanhmausac, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_themnhanhchatlieu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_themnhanhkichthuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(26, 26, 26)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_themspct)
                    .addComponent(btn_suaspct)
                    .addComponent(btn_xoaspct)
                    .addComponent(btn_resetthongtinchitietsp))
                .addGap(33, 33, 33))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txt_maspct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(cbx_mausac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_themspct)
                        .addComponent(btn_themnhanhmausac)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(cbx_kichthuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_themnhanhkichthuoc))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbx_chatlieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(btn_themnhanhchatlieu)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_tbmasp, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_tenspct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(btn_suaspct))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_tbtensp, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(btn_xoaspct))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_soluongsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_tbsoluong, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_resetthongtinchitietsp)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_themnhanhloaisp)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbx_loaisanpham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_dongiasp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_tbdongiasp, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbl_ttspct.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_ttspct.setText("THÔNG TIN SẢN PHẨM ĐANG BÁN");

        jLabel15.setText("Tìm kiếm sản phẩm theo tên");

        txt_timkiemsanpham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_timkiemsanphamActionPerformed(evt);
            }
        });

        tbl_spchitiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã sản phẩm", "Tên sản phẩm", "Loại sản phẩm", "Kích thước", "Màu sắc", "Chất liệu", "Đơn giá", "Số lượng"
            }
        ));
        tbl_spchitiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_spchitietMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_spchitiet);
        if (tbl_spchitiet.getColumnModel().getColumnCount() > 0) {
            tbl_spchitiet.getColumnModel().getColumn(0).setMinWidth(40);
            tbl_spchitiet.getColumnModel().getColumn(0).setPreferredWidth(40);
            tbl_spchitiet.getColumnModel().getColumn(0).setMaxWidth(40);
            tbl_spchitiet.getColumnModel().getColumn(1).setMinWidth(80);
            tbl_spchitiet.getColumnModel().getColumn(1).setPreferredWidth(80);
            tbl_spchitiet.getColumnModel().getColumn(1).setMaxWidth(80);
            tbl_spchitiet.getColumnModel().getColumn(4).setMinWidth(70);
            tbl_spchitiet.getColumnModel().getColumn(4).setPreferredWidth(70);
            tbl_spchitiet.getColumnModel().getColumn(4).setMaxWidth(70);
            tbl_spchitiet.getColumnModel().getColumn(8).setMinWidth(60);
            tbl_spchitiet.getColumnModel().getColumn(8).setPreferredWidth(60);
            tbl_spchitiet.getColumnModel().getColumn(8).setMaxWidth(60);
        }

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(lbl_ttspct)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_timkiemsanpham, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 969, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(15, Short.MAX_VALUE))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_ttspct)
                    .addComponent(jLabel15)
                    .addComponent(txt_timkiemsanpham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_spctdaxoa.setText("Hiển thị danh sách đã xóa");
        btn_spctdaxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_spctdaxoaActionPerformed(evt);
            }
        });

        btn_khoiphucspct.setText("Khôi phục");
        btn_khoiphucspct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_khoiphucspctActionPerformed(evt);
            }
        });

        btn_trovespct.setText("Trở về");
        btn_trovespct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_trovespctActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btn_trovespct)
                        .addGap(18, 18, 18)
                        .addComponent(btn_khoiphucspct)
                        .addGap(27, 27, 27)
                        .addComponent(btn_spctdaxoa))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_spctdaxoa)
                    .addComponent(btn_khoiphucspct)
                    .addComponent(btn_trovespct))
                .addGap(22, 22, 22))
        );

        jTabbedPane1.addTab("Thông Tin Chi Tiết", jPanel3);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setToolTipText("");

        jLabel3.setText("Giá trị");

        txt_tenthuoctinh.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txt_tenthuoctinhCaretUpdate(evt);
            }
        });

        buttonGroup1.add(rdo_kichthuoc);
        rdo_kichthuoc.setText("Kích thước");
        rdo_kichthuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_kichthuocActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdo_mausac);
        rdo_mausac.setSelected(true);
        rdo_mausac.setText("Màu sắc");
        rdo_mausac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_mausacActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdo_loaisp);
        rdo_loaisp.setText("Loại sản phẩm");
        rdo_loaisp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_loaispActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdo_chatlieu);
        rdo_chatlieu.setText("Chất liệu");
        rdo_chatlieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_chatlieuActionPerformed(evt);
            }
        });

        btn_suathuoctinh.setText("Sửa");
        btn_suathuoctinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suathuoctinhActionPerformed(evt);
            }
        });

        btn_xoathuoctinh.setText("Xóa");
        btn_xoathuoctinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoathuoctinhActionPerformed(evt);
            }
        });

        btn_themthuoctinh.setText("Thêm");
        btn_themthuoctinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themthuoctinhActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Thuộc tính chi tiết");

        lbl_thongbaothuoctinh.setForeground(new java.awt.Color(255, 0, 51));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_thongbaothuoctinh, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_tenthuoctinh)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(btn_themthuoctinh)
                                .addGap(27, 27, 27)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(btn_xoathuoctinh)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_suathuoctinh))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(rdo_mausac, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdo_kichthuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdo_chatlieu, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdo_loaisp)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdo_mausac)
                    .addComponent(rdo_kichthuoc)
                    .addComponent(rdo_chatlieu)
                    .addComponent(rdo_loaisp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_tenthuoctinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_thongbaothuoctinh, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_xoathuoctinh)
                        .addComponent(btn_themthuoctinh)
                        .addComponent(btn_suathuoctinh)))
                .addGap(321, 321, 321))
        );

        tbl_thuoctinh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Loại thuộc tính", "Giá trị"
            }
        ));
        tbl_thuoctinh.setPreferredSize(new java.awt.Dimension(300, 487));
        tbl_thuoctinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_thuoctinhMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_thuoctinh);
        if (tbl_thuoctinh.getColumnModel().getColumnCount() > 0) {
            tbl_thuoctinh.getColumnModel().getColumn(0).setMinWidth(40);
            tbl_thuoctinh.getColumnModel().getColumn(0).setPreferredWidth(40);
            tbl_thuoctinh.getColumnModel().getColumn(0).setMaxWidth(40);
        }

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1036, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 67, Short.MAX_VALUE)
        );

        lbl_dsthuoctinh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_dsthuoctinh.setText("DANH SÁCH THUỘC TÍNH");

        btn_dsdaxoa.setText("Hiển thị danh sách đã xóa");
        btn_dsdaxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dsdaxoaActionPerformed(evt);
            }
        });

        btn_khoiphucthuoctinh.setText("Khôi phục");
        btn_khoiphucthuoctinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_khoiphucthuoctinhActionPerformed(evt);
            }
        });

        btn_backthuoctinh.setText("Trở về");
        btn_backthuoctinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backthuoctinhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(431, 431, 431)
                        .addComponent(lbl_dsthuoctinh))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btn_dsdaxoa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_khoiphucthuoctinh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_backthuoctinh)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lbl_dsthuoctinh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_dsdaxoa)
                    .addComponent(btn_khoiphucthuoctinh)
                    .addComponent(btn_backthuoctinh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Thuộc Tính Sản Phẩm", jPanel4);
        jTabbedPane1.addTab("Tìm Kiếm Thông Tin", jTabbedPane2);

        panelSanPham.add(jTabbedPane1);
        jTabbedPane1.setBounds(10, 10, 1040, 540);

        mainPanel.add(panelSanPham, "panelSanPham");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_menu, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbl_menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_thongKeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_thongKeMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl_thongKeMouseEntered

    private void lbl_thongKeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_thongKeMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl_thongKeMouseExited

    private void lbl_thongKeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_thongKeMouseClicked
        // TODO add your handling code here:   
        CardLayout card = (CardLayout) mainPanel.getLayout();
        card.show(mainPanel, "panelThongKe");
    }//GEN-LAST:event_lbl_thongKeMouseClicked

    private void lbl_sanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_sanPhamMouseClicked
        // TODO add your handling code here:
        CardLayout card = (CardLayout) mainPanel.getLayout();
        card.show(mainPanel, "panelSanPham");
    }//GEN-LAST:event_lbl_sanPhamMouseClicked

    private void lbl_nhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_nhanVienMouseClicked
        // TODO add your handling code here:
        CardLayout card = (CardLayout) mainPanel.getLayout();
        card.show(mainPanel, "panelNhanVien");
    }//GEN-LAST:event_lbl_nhanVienMouseClicked

    private void lbl_hoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_hoaDonMouseClicked
        // TODO add your handling code here:
        CardLayout card = (CardLayout) mainPanel.getLayout();
        card.show(mainPanel, "panelHoaDon");
    }//GEN-LAST:event_lbl_hoaDonMouseClicked

    private void lbl_khachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_khachHangMouseClicked
        // TODO add your handling code here:
        CardLayout card = (CardLayout) mainPanel.getLayout();
        card.show(mainPanel, "panelKhachHang");
    }//GEN-LAST:event_lbl_khachHangMouseClicked

    private void lbl_lichSuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_lichSuMouseClicked
        // TODO add your handling code here:
        CardLayout card = (CardLayout) mainPanel.getLayout();
        card.show(mainPanel, "panelLichSu");
    }//GEN-LAST:event_lbl_lichSuMouseClicked

    private void lbl_khuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_khuyenMaiMouseClicked
        // TODO add your handling code here:
        CardLayout card = (CardLayout) mainPanel.getLayout();
        card.show(mainPanel, "panelKhuyenMai");
    }//GEN-LAST:event_lbl_khuyenMaiMouseClicked

    private void lbl_doiMKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_doiMKMouseClicked
        // TODO add your handling code here:
        CardLayout card = (CardLayout) mainPanel.getLayout();
        card.show(mainPanel, "panelDoiMK");
    }//GEN-LAST:event_lbl_doiMKMouseClicked

    private void lbl_dangXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_dangXuatMouseClicked
        // TODO add your handling code here:
        CardLayout card = (CardLayout) mainPanel.getLayout();
        card.show(mainPanel, "panelDangXuat");
    }//GEN-LAST:event_lbl_dangXuatMouseClicked

    private void rdo_mausacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_mausacActionPerformed
        xuLyChonRadioButton();
        lbl_dsthuoctinh.setText("DANH SÁCH THUỘC TÍNH MÀU SẮC");
    }//GEN-LAST:event_rdo_mausacActionPerformed

    private void rdo_kichthuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_kichthuocActionPerformed
        xuLyChonRadioButton();
                lbl_dsthuoctinh.setText("DANH SÁCH THUỘC TÍNH KÍCH THƯỚC");
    }//GEN-LAST:event_rdo_kichthuocActionPerformed

    private void rdo_loaispActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_loaispActionPerformed
        xuLyChonRadioButton();
                lbl_dsthuoctinh.setText("DANH SÁCH THUỘC TÍNH LOẠI SẢN PHẨM");
    }//GEN-LAST:event_rdo_loaispActionPerformed

    private void rdo_chatlieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_chatlieuActionPerformed
        xuLyChonRadioButton();
                lbl_dsthuoctinh.setText("DANH SÁCH THUỘC TÍNH CHẤT LIỆU");
    }//GEN-LAST:event_rdo_chatlieuActionPerformed

    private void btn_themthuoctinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themthuoctinhActionPerformed
        String ten = txt_tenthuoctinh.getText().trim();
        String tableName = getSelectedTableName();

        // Kiểm tra tên không được để trống
        if (ValidationUtil.isEmpty(ten)) {
            lbl_thongbaothuoctinh.setText("Tên thuộc tính không được để trống");
            return;
        }

        // Kiểm tra tên thuộc tính đã tồn tại chưa
        if (service_spthuoctinh.kiemTraTenThuocTinhDaTonTai(tableName, ten)) {
            JOptionPane.showMessageDialog(this, "Tên thuộc tính đã tồn tại!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Xác nhận thêm mới
        int luachon = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm thuộc tính không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (luachon == JOptionPane.YES_OPTION) {
            boolean them = service_spthuoctinh.addThuocTinh(tableName, ten);
            if (them) {
                loadComboboxLoaiSanPham();
                loadComboboxLoaiMauSac();
                loadComboboxLoaiKichThuoc();
                loadComboboxLoaiChatlieu();
                JOptionPane.showMessageDialog(this, "Thêm thuộc tính sản phẩm thành công.");
                xuLyChonRadioButton(); // Cập nhật lại bảng sau khi thêm
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại.");
            }
        }
    }//GEN-LAST:event_btn_themthuoctinhActionPerformed

    private void btn_xoathuoctinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoathuoctinhActionPerformed
        int chon = tbl_thuoctinh.getSelectedRow();

        if (chon == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một thuộc tính để xóa!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Lấy tên thuộc tính từ cột thứ 2 (index = 2)
        String tenThuocTinh = tbl_thuoctinh.getValueAt(chon, 2).toString();

        // Lấy tên bảng từ radio đã chọn
        String tableName = getSelectedTableName();

        if (tableName == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn loại thuộc tính cần xóa!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Hỏi xác nhận trước khi xóa
        int luachon = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa '" + tenThuocTinh + "'?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (luachon == JOptionPane.YES_OPTION) {
            boolean xoaThanhCong = service_spthuoctinh.updateDaXoa(tableName, tenThuocTinh);

            if (xoaThanhCong) {
                JOptionPane.showMessageDialog(this, "Xóa thuộc tính thành công!");
                xuLyChonRadioButton();  // Cập nhật lại bảng sau khi xóa
                loadComboboxLoaiSanPham();
                loadComboboxLoaiMauSac();
                loadComboboxLoaiKichThuoc();
                loadComboboxLoaiChatlieu();
                fillTable_SPChiTiet(service_spchitiet.layDanhSachSanPhamChiTiet());

            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại. Vui lòng thử lại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_xoathuoctinhActionPerformed

    private void tbl_thuoctinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_thuoctinhMouseClicked
        int selectedRow = tbl_thuoctinh.getSelectedRow(); // Lấy chỉ số hàng được chọn
        if (selectedRow != -1) { // Kiểm tra xem có hàng nào được chọn không
            String tenThuocTinh = tbl_thuoctinh.getValueAt(selectedRow, 2).toString(); // Lấy dữ liệu từ cột 2
            txt_tenthuoctinh.setText(tenThuocTinh); // Hiển thị vào ô nhập liệu
        }
    }//GEN-LAST:event_tbl_thuoctinhMouseClicked

    private void btn_suathuoctinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suathuoctinhActionPerformed
        int chon = tbl_thuoctinh.getSelectedRow();

        if (chon == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một thuộc tính để cập nhật!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String tenCu = tbl_thuoctinh.getValueAt(chon, 2).toString(); // Lấy tên cũ từ bảng
        String tenMoi = txt_tenthuoctinh.getText().trim().toString(); // Lấy tên mới từ text field
        String tableName = getSelectedTableName(); // Lấy bảng hiện tại

        if (ValidationUtil.isEmpty(tenMoi)) {
            JOptionPane.showMessageDialog(this, "Tên thuộc tính không được để trống!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Kiểm tra xem có thay đổi tên không
        if (tenCu.equalsIgnoreCase(tenMoi)) {
            JOptionPane.showMessageDialog(this, "Tên mới không có sự thay đổi!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Kiểm tra xem tên mới đã tồn tại chưa
        if (service_spthuoctinh.kiemTraTenThuocTinhDaTonTai(tableName, tenMoi)) {
            JOptionPane.showMessageDialog(this, "Tên thuộc tính đã tồn tại! Vui lòng nhập tên khác.", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Xác nhận cập nhật
        int xacNhan = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn cập nhật tên thuộc tính?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (xacNhan == JOptionPane.YES_OPTION) {
            boolean capNhatThanhCong = service_spthuoctinh.updateThuocTinh(tableName, tenCu, tenMoi);

            if (capNhatThanhCong) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
                xuLyChonRadioButton(); // Load lại bảng sau khi cập nhật
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại. Vui lòng thử lại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_btn_suathuoctinhActionPerformed

    private void btn_dsdaxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dsdaxoaActionPerformed
        xuLyChonRadioButton1();
        btn_khoiphucthuoctinh.setVisible(true);
        btn_backthuoctinh.setVisible(true);
        lbl_dsthuoctinh.setText("DANH SÁCH THUỘC TÍNH ĐÃ XÓA");
    }//GEN-LAST:event_btn_dsdaxoaActionPerformed

    private void tbl_spchitietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_spchitietMouseClicked
        int selectedRow = tbl_spchitiet.getSelectedRow(); // Lấy chỉ số hàng được chọn
        if (selectedRow != -1) { // Kiểm tra xem có hàng nào được chọn không

            txt_maspct.setText(tbl_spchitiet.getValueAt(selectedRow, 1).toString());// Lấy dữ liệu từ cột 1
            txt_tenspct.setText(tbl_spchitiet.getValueAt(selectedRow, 2).toString());
            String sanPham = tbl_spchitiet.getValueAt(selectedRow, 3).toString();
            cbx_loaisanpham.setSelectedItem(sanPham);

            String kichThuoc = tbl_spchitiet.getValueAt(selectedRow, 4).toString();
            cbx_kichthuoc.setSelectedItem(kichThuoc);
            String mauSac = tbl_spchitiet.getValueAt(selectedRow, 5).toString();
            cbx_mausac.setSelectedItem(mauSac);
            String chatLieu = tbl_spchitiet.getValueAt(selectedRow, 6).toString();
            cbx_chatlieu.setSelectedItem(chatLieu);

            String donGia = (tbl_spchitiet.getValueAt(selectedRow, 7).toString());
            donGia = donGia.replaceAll("[^0-9]", "");
            txt_dongiasp.setText(donGia);
            txt_soluongsp.setText(tbl_spchitiet.getValueAt(selectedRow, 8).toString());

            txt_maspct.setEditable(false);
        }
    }//GEN-LAST:event_tbl_spchitietMouseClicked

    private void btn_khoiphucthuoctinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_khoiphucthuoctinhActionPerformed
        int chon = tbl_thuoctinh.getSelectedRow();

        if (chon == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một thuộc tính để khôi phục!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Lấy tên thuộc tính từ cột thứ 2 (index = 2)
        String tenThuocTinh = tbl_thuoctinh.getValueAt(chon, 2).toString();

        // Lấy tên bảng từ radio đã chọn
        String tableName = getSelectedTableName();

        if (tableName == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn loại thuộc tính cần khôi phục!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Hỏi xác nhận trước khi xóa
        int luachon = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn khôi phục '" + tenThuocTinh + "'?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (luachon == JOptionPane.YES_OPTION) {
            boolean khoiPhucThanhCong = service_spthuoctinh.khoiPhucDaXoa(tableName, tenThuocTinh);

            if (khoiPhucThanhCong) {
                JOptionPane.showMessageDialog(this, "Khôi phục thuộc tính thành công!");
                loadComboboxLoaiChatlieu();
                loadComboboxLoaiKichThuoc();
                loadComboboxLoaiMauSac();
                loadComboboxLoaiSanPham();

                xuLyChonRadioButton1();
            } else {
                JOptionPane.showMessageDialog(this, "Khôi phục thất bại. Vui lòng thử lại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }

        }
    }//GEN-LAST:event_btn_khoiphucthuoctinhActionPerformed

    private void txt_tenthuoctinhCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txt_tenthuoctinhCaretUpdate
        lbl_thongbaothuoctinh.setText("");
    }//GEN-LAST:event_txt_tenthuoctinhCaretUpdate

    private void btn_spctdaxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_spctdaxoaActionPerformed
        btn_khoiphucspct.setVisible(true);
        fillTable_SPChiTiet(service_spchitiet.layDanhSachSanPhamChiTietDaXoa());
        lbl_ttspct.setText("THÔNG TIN SẢN PHẨM ĐÃ XÓA");
        btn_trovespct.setVisible(true);
    }//GEN-LAST:event_btn_spctdaxoaActionPerformed

    private void btn_khoiphucspctActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_khoiphucspctActionPerformed
        int chon = tbl_spchitiet.getSelectedRow();

        if (chon == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm để khôi phục!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String ma = txt_maspct.getText().trim();

        // Lấy giá trị từ JComboBox
        String tenLoaiSP = cbx_loaisanpham.getSelectedItem().toString();
        String tenKichThuoc = cbx_kichthuoc.getSelectedItem().toString();
        String tenMauSac = cbx_mausac.getSelectedItem().toString();
        String tenChatLieu = cbx_chatlieu.getSelectedItem().toString();

        // Lấy ID từ HashMap (cần tạo HashMap ở bước trước)
        int idLoaiSP = sanPhamMap.get(tenLoaiSP);
        int idKichThuoc = kichThuocMap.get(tenKichThuoc);
        int idMauSac = mauSacMap.get(tenMauSac);
        int idChatLieu = chatLieuMap.get(tenChatLieu);

        // Hỏi xác nhận trước khi xóa
        int luachon = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn khôi phục ?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (luachon == JOptionPane.YES_OPTION) {
            boolean khoiPhucThanhCong = service_spchitiet.khoiPhucDaXoaSPCT(ma, idLoaiSP, idMauSac, idChatLieu, idKichThuoc);

            if (khoiPhucThanhCong) {
                JOptionPane.showMessageDialog(this, "Khôi phục sản phẩm thành công!");

                fillTable_SPChiTiet(service_spchitiet.layDanhSachSanPhamChiTietDaXoa());

            } else {
                JOptionPane.showMessageDialog(this, "Khôi phục thất bại. Vui lòng thử lại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_khoiphucspctActionPerformed

    private void btn_backthuoctinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backthuoctinhActionPerformed
        btn_khoiphucthuoctinh.setVisible(false);
        btn_backthuoctinh.setVisible(false);
        xuLyChonRadioButton();  // Cập nhật lại bảng sau khi xóa
        fillTable_SPChiTiet(service_spchitiet.layDanhSachSanPhamChiTiet());
        lbl_dsthuoctinh.setText("DANH SÁCH THUỘC TÍNH");
    }//GEN-LAST:event_btn_backthuoctinhActionPerformed

    private void btn_trovespctActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_trovespctActionPerformed
        lbl_ttspct.setText("THÔNG TIN SẢN PHẨM ĐANG BÁN");
         fillTable_SPChiTiet(service_spchitiet.layDanhSachSanPhamChiTiet());
         btn_trovespct.setVisible(false);
         btn_khoiphucspct.setVisible(false);
    }//GEN-LAST:event_btn_trovespctActionPerformed

    private void txt_timkiemsanphamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_timkiemsanphamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_timkiemsanphamActionPerformed

    private void btn_resetthongtinchitietspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetthongtinchitietspActionPerformed
        txt_maspct.setText("");
        txt_tenspct.setText("");
        txt_dongiasp.setText("");
        txt_soluongsp.setText("");
        lbl_tbmasp.setText("");
        lbl_tbtensp.setText("");
        lbl_tbdongiasp.setText("");
        lbl_tbsoluong.setText("");
        cbx_chatlieu.setSelectedIndex(0);
        cbx_kichthuoc.setSelectedIndex(0);
        cbx_loaisanpham.setSelectedIndex(0);
        cbx_mausac.setSelectedIndex(0);
        txt_maspct.setEditable(true);
    }//GEN-LAST:event_btn_resetthongtinchitietspActionPerformed

    private void btn_xoaspctActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaspctActionPerformed
        int selectedRow = tbl_spchitiet.getSelectedRow();
        String ma = txt_maspct.getText().trim();

        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần xóa!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int luachon = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa sản phẩm không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (luachon == JOptionPane.YES_OPTION) {
            // Gửi ID vào service thay vì tên
            boolean xoa = service_spchitiet.xoaSPCT(ma);
            if (xoa) {
                JOptionPane.showMessageDialog(this, "Xóa sản phẩm thành công.");
                // Cập nhật lại bảng sau khi thêm
                fillTable_SPChiTiet(service_spchitiet.layDanhSachSanPhamChiTiet());
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại.");
            }
        }
    }//GEN-LAST:event_btn_xoaspctActionPerformed

    private void btn_suaspctActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaspctActionPerformed
        int selectedRow = tbl_spchitiet.getSelectedRow();

        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm muốn sửa!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String ma = txt_maspct.getText().trim();
        String ten = txt_tenspct.getText().trim();

        // Lấy giá trị từ JComboBox
        String tenLoaiSP = cbx_loaisanpham.getSelectedItem().toString();
        String tenKichThuoc = cbx_kichthuoc.getSelectedItem().toString();
        String tenMauSac = cbx_mausac.getSelectedItem().toString();
        String tenChatLieu = cbx_chatlieu.getSelectedItem().toString();

        // Lấy ID từ HashMap (cần tạo HashMap ở bước trước)
        int idLoaiSP = sanPhamMap.get(tenLoaiSP);
        int idKichThuoc = kichThuocMap.get(tenKichThuoc);
        int idMauSac = mauSacMap.get(tenMauSac);
        int idChatLieu = chatLieuMap.get(tenChatLieu);

        String donGia = txt_dongiasp.getText().trim();

        if (ValidationUtil.isEmpty(ten)) {
            lbl_tbtensp.setText("Tên sản phẩm không được để trống");
            return;
        }

        if (ValidationUtil.isEmpty(txt_dongiasp.getText().trim())) {
            lbl_tbdongiasp.setText("Đơn giá sản phẩm không được để trống");
            return;
        } else if (!ValidationUtil.isNumberic(txt_dongiasp.getText().trim())) {
            lbl_tbdongiasp.setText("Đơn giá sản phẩm phải là số ");
            return;
        } else if (Integer.parseInt(txt_dongiasp.getText().trim()) <= 0) {
            lbl_tbdongiasp.setText("Đơn giá sản phẩm phải lớn hơn 0");
            return;
        }

        int soLuong;
        if (ValidationUtil.isEmpty(txt_soluongsp.getText().trim())) {
            lbl_tbsoluong.setText("Số lượng sản phẩm không được để trống");
            return;
        } else if (!ValidationUtil.isNumberic(txt_soluongsp.getText().trim())) {
            lbl_tbsoluong.setText("Số lượng sản phẩm phải là số Nguyên");
            return;
        } else if (Integer.parseInt(txt_soluongsp.getText().trim()) <= 0) {
            lbl_tbsoluong.setText("Số lượng sản phẩm phải lớn hơn 0");
            return;
        } else {
            soLuong = Integer.parseInt(txt_soluongsp.getText().trim());
        }

        // Xác nhận sửa
        int luachon = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa sản phẩm không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (luachon == JOptionPane.YES_OPTION) {
            // Gửi ID vào service thay vì tên
            boolean sua = service_spchitiet.updateSanPham(ma, ten, idLoaiSP, idKichThuoc, idMauSac, idChatLieu, donGia, soLuong);
            if (sua) {
                JOptionPane.showMessageDialog(this, "Sửa sản phẩm thành công.");
                // Cập nhật lại bảng sau khi thêm
                fillTable_SPChiTiet(service_spchitiet.layDanhSachSanPhamChiTiet());
            } else {
                JOptionPane.showMessageDialog(this, "Sửa thất bại.");
            }
        }
    }//GEN-LAST:event_btn_suaspctActionPerformed

    private void btn_themspctActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themspctActionPerformed
         txt_maspct.setEditable(true);
        String ma = txt_maspct.getText().trim();
        String ten = txt_tenspct.getText().trim();

        // Lấy giá trị từ JComboBox
        String tenLoaiSP = cbx_loaisanpham.getSelectedItem().toString();
        String tenKichThuoc = cbx_kichthuoc.getSelectedItem().toString();
        String tenMauSac = cbx_mausac.getSelectedItem().toString();
        String tenChatLieu = cbx_chatlieu.getSelectedItem().toString();

        // Lấy ID từ HashMap (cần tạo HashMap ở bước trước)
        int idLoaiSP = sanPhamMap.get(tenLoaiSP);
        int idKichThuoc = kichThuocMap.get(tenKichThuoc);
        int idMauSac = mauSacMap.get(tenMauSac);
        int idChatLieu = chatLieuMap.get(tenChatLieu);

        String donGia = txt_dongiasp.getText().trim();

        if (ValidationUtil.isEmpty(ma)) {
            lbl_tbmasp.setText("Mã sản phẩm không được để trống");
            return;
        } else if (!ValidationUtil.kiemTraMa(ma)) {
            lbl_tbmasp.setText("Mã sản phẩm gồm 5 kí tự, 2 chữ in Hoa và 3 số");
            return;
        } else if (service_spchitiet.kiemTraMaSPCTDaTonTai(ma)) {
            lbl_tbmasp.setText("Mã sản phẩm đã tồn tại");
            return;
        }

        if (ValidationUtil.isEmpty(ten)) {
            lbl_tbtensp.setText("Tên sản phẩm không được để trống");
            return;
        }

        if (ValidationUtil.isEmpty(txt_dongiasp.getText().trim())) {
            lbl_tbdongiasp.setText("Đơn giá sản phẩm không được để trống");
            return;
        } else if (!ValidationUtil.isNumberic(txt_dongiasp.getText().trim())) {
            lbl_tbdongiasp.setText("Đơn giá sản phẩm phải là số ");
            return;
        } else if (Integer.parseInt(txt_dongiasp.getText().trim()) <= 0) {
            lbl_tbdongiasp.setText("Đơn giá sản phẩm phải lớn hơn 0");
            return;
        }

        int soLuong;
        if (ValidationUtil.isEmpty(txt_soluongsp.getText().trim())) {
            lbl_tbsoluong.setText("Số lượng sản phẩm không được để trống");
            return;
        } else if (!ValidationUtil.isNumberic(txt_soluongsp.getText().trim())) {
            lbl_tbsoluong.setText("Số lượng sản phẩm phải là số Nguyên");
            return;
        } else if (Integer.parseInt(txt_soluongsp.getText().trim()) <= 0) {
            lbl_tbsoluong.setText("Số lượng sản phẩm phải lớn hơn 0");
            return;
        } else {
            soLuong = Integer.parseInt(txt_soluongsp.getText().trim());
        }

        // Xác nhận thêm mới
        int luachon = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm sản phẩm không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (luachon == JOptionPane.YES_OPTION) {
            // Gửi ID vào service thay vì tên
            boolean them = service_spchitiet.themSanPhamChiTiet(ma, ten, idLoaiSP, idKichThuoc, idMauSac, idChatLieu, donGia, soLuong);
            if (them) {
                JOptionPane.showMessageDialog(this, "Thêm sản phẩm chi tiết thành công.");
                // Cập nhật lại bảng sau khi thêm
                fillTable_SPChiTiet(service_spchitiet.layDanhSachSanPhamChiTiet());
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại.");
            }
        }
    }//GEN-LAST:event_btn_themspctActionPerformed

    private void cbx_loaisanphamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_loaisanphamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbx_loaisanphamActionPerformed

    private void txt_soluongspCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txt_soluongspCaretUpdate
        lbl_tbsoluong.setText("");
    }//GEN-LAST:event_txt_soluongspCaretUpdate

    private void txt_dongiaspCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txt_dongiaspCaretUpdate
        lbl_tbdongiasp.setText("");
    }//GEN-LAST:event_txt_dongiaspCaretUpdate

    private void txt_tenspctCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txt_tenspctCaretUpdate
        lbl_tbtensp.setText("");
    }//GEN-LAST:event_txt_tenspctCaretUpdate

    private void txt_maspctCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txt_maspctCaretUpdate
        lbl_tbmasp.setText("");
    }//GEN-LAST:event_txt_maspctCaretUpdate

    private void btn_themnhanhloaispActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themnhanhloaispActionPerformed
       // Mở cửa sổ ThemNhanhTT và truyền đối tượng HomeView cùng callback vào
    ThemNhanhTT themnhanh = new ThemNhanhTT(this, new Runnable() {
        @Override
        public void run() {
            // Sau khi cửa sổ ThemNhanhTT đóng và thao tác thêm thuộc tính thành công
            loadComboboxLoaiSanPham();
            loadComboboxLoaiMauSac();
            loadComboboxLoaiKichThuoc();
            loadComboboxLoaiChatlieu();
            xuLyChonRadioButton();  // Cập nhật lại bảng sau khi thêm
        }
    });

    // Set lựa chọn radio button để chọn bảng đúng
    rdo_loaisp.setSelected(true); // Chọn Loại sản phẩm

    // Lấy tên bảng sau khi đã chọn radio button
    String tableName = getSelectedTableName();  // Lấy tên bảng từ radio button được chọn
    
    // Truyền tên bảng vào ThemNhanhTT
    themnhanh.setSelectedTableName(tableName);
    themnhanh.setVisible(true);
    }//GEN-LAST:event_btn_themnhanhloaispActionPerformed

    private void btn_themnhanhmausacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themnhanhmausacActionPerformed
         // Mở cửa sổ ThemNhanhTT và truyền đối tượng HomeView cùng callback vào
    ThemNhanhTT themnhanh = new ThemNhanhTT(this, new Runnable() {
        @Override
        public void run() {
            // Sau khi cửa sổ ThemNhanhTT đóng và thao tác thêm thuộc tính thành công
            loadComboboxLoaiSanPham();
            loadComboboxLoaiMauSac();
            loadComboboxLoaiKichThuoc();
            loadComboboxLoaiChatlieu();
            xuLyChonRadioButton();  // Cập nhật lại bảng sau khi thêm
        }
    });

    // Set lựa chọn radio button để chọn bảng đúng
    rdo_mausac.setSelected(true); // Chọn Loại sản phẩm

    // Lấy tên bảng sau khi đã chọn radio button
    String tableName = getSelectedTableName();  // Lấy tên bảng từ radio button được chọn
    
    // Truyền tên bảng vào ThemNhanhTT
    themnhanh.setSelectedTableName(tableName);
    themnhanh.setVisible(true);
    }//GEN-LAST:event_btn_themnhanhmausacActionPerformed

    private void btn_themnhanhkichthuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themnhanhkichthuocActionPerformed
        // Mở cửa sổ ThemNhanhTT và truyền đối tượng HomeView cùng callback vào
    ThemNhanhTT themnhanh = new ThemNhanhTT(this, new Runnable() {
        @Override
        public void run() {
            // Sau khi cửa sổ ThemNhanhTT đóng và thao tác thêm thuộc tính thành công
            loadComboboxLoaiSanPham();
            loadComboboxLoaiMauSac();
            loadComboboxLoaiKichThuoc();
            loadComboboxLoaiChatlieu();
            xuLyChonRadioButton();  // Cập nhật lại bảng sau khi thêm
        }
    });

    // Set lựa chọn radio button để chọn bảng đúng
    rdo_kichthuoc.setSelected(true); // Chọn Loại sản phẩm

    // Lấy tên bảng sau khi đã chọn radio button
    String tableName = getSelectedTableName();  // Lấy tên bảng từ radio button được chọn
    
    // Truyền tên bảng vào ThemNhanhTT
    themnhanh.setSelectedTableName(tableName);
    themnhanh.setVisible(true);
    }//GEN-LAST:event_btn_themnhanhkichthuocActionPerformed

    private void btn_themnhanhchatlieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themnhanhchatlieuActionPerformed
        // Mở cửa sổ ThemNhanhTT và truyền đối tượng HomeView cùng callback vào
    ThemNhanhTT themnhanh = new ThemNhanhTT(this, new Runnable() {
        @Override
        public void run() {
            // Sau khi cửa sổ ThemNhanhTT đóng và thao tác thêm thuộc tính thành công
            loadComboboxLoaiSanPham();
            loadComboboxLoaiMauSac();
            loadComboboxLoaiKichThuoc();
            loadComboboxLoaiChatlieu();
            xuLyChonRadioButton();  // Cập nhật lại bảng sau khi thêm
        }
    });

    // Set lựa chọn radio button để chọn bảng đúng
    rdo_chatlieu.setSelected(true); // Chọn Loại sản phẩm

    // Lấy tên bảng sau khi đã chọn radio button
    String tableName = getSelectedTableName();  // Lấy tên bảng từ radio button được chọn
    
    // Truyền tên bảng vào ThemNhanhTT
    themnhanh.setSelectedTableName(tableName);
    themnhanh.setVisible(true);
    }//GEN-LAST:event_btn_themnhanhchatlieuActionPerformed

    private void layDanhSachDaXoa(String tableName, String loaiTt) {
        List<SanPham_ThuocTinh> danhSach = service_spthuoctinh.getThuocTinhDaXoa(tableName);
        fillTable_SPThuocTinh(danhSach, loaiTt);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

}
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HomeView.class  

.getName()).log(java.util.logging.Level.SEVERE, null, ex);

} catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeView.class  

.getName()).log(java.util.logging.Level.SEVERE, null, ex);

} catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeView.class  

.getName()).log(java.util.logging.Level.SEVERE, null, ex);

} catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeView.class  

.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeView().setVisible(true);
            }
        });
    }

    private void addLabelClickListener(JLabel label, String panelName) {
        label.setOpaque(true); // Đảm bảo JLabel hiển thị màu nền

        label.addMouseListener(new MouseAdapter() {

            @Override
public void mouseEntered(MouseEvent e) {
                if (label != selectedLabel) {
                    label.setForeground(hoverTextColor);
                    label.setBackground(hoverBackground);
                }
            }

            @Override
public void mouseExited(MouseEvent e) {
                if (label != selectedLabel) {
                    label.setForeground(Color.BLACK);
                    label.setBackground(new Color(242, 242, 242));
                }
            }

            @Override
public void mouseClicked(MouseEvent e) {
                if (selectedLabel != null) {
                    // Reset label trước đó về màu mặc định
                    selectedLabel.setForeground(Color.BLACK);
                    selectedLabel.setBackground(new Color(242, 242, 242));
                }

                // Cập nhật label được chọn
                selectedLabel = label;
                label.setForeground(selectedTextColor);
                label.setBackground(selectedBackground);

                // Hiển thị panel tương ứng
                CardLayout card = (CardLayout) mainPanel.getLayout();
                card.show(mainPanel, panelName);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_backthuoctinh;
    private javax.swing.JButton btn_dsdaxoa;
    private javax.swing.JButton btn_khoiphucspct;
    private javax.swing.JButton btn_khoiphucthuoctinh;
    private javax.swing.JButton btn_resetthongtinchitietsp;
    private javax.swing.JButton btn_spctdaxoa;
    private javax.swing.JButton btn_suaspct;
    private javax.swing.JButton btn_suathuoctinh;
    private javax.swing.JButton btn_themnhanhchatlieu;
    private javax.swing.JButton btn_themnhanhkichthuoc;
    private javax.swing.JButton btn_themnhanhloaisp;
    private javax.swing.JButton btn_themnhanhmausac;
    private javax.swing.JButton btn_themspct;
    private javax.swing.JButton btn_themthuoctinh;
    private javax.swing.JButton btn_trovespct;
    private javax.swing.JButton btn_xoaspct;
    private javax.swing.JButton btn_xoathuoctinh;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbx_chatlieu;
    private javax.swing.JComboBox<String> cbx_kichthuoc;
    private javax.swing.JComboBox<String> cbx_loaisanpham;
    private javax.swing.JComboBox<String> cbx_mausac;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel lbl_dangXuat;
    private javax.swing.JLabel lbl_doiMK;
    private javax.swing.JLabel lbl_dsthuoctinh;
    private javax.swing.JLabel lbl_hoaDon;
    private javax.swing.JLabel lbl_khachHang;
    private javax.swing.JLabel lbl_khuyenMai;
    private javax.swing.JLabel lbl_lichSu;
    private javax.swing.JPanel lbl_menu;
    private javax.swing.JLabel lbl_nhanVien;
    private javax.swing.JLabel lbl_sanPham;
    private javax.swing.JLabel lbl_tbdongiasp;
    private javax.swing.JLabel lbl_tbmasp;
    private javax.swing.JLabel lbl_tbsoluong;
    private javax.swing.JLabel lbl_tbtensp;
    private javax.swing.JLabel lbl_thongKe;
    private javax.swing.JLabel lbl_thongbaothuoctinh;
    private javax.swing.JLabel lbl_ttspct;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel panelDangXuat;
    private javax.swing.JPanel panelDoiMK;
    private javax.swing.JPanel panelHoaDon;
    private javax.swing.JPanel panelKhachHang;
    private javax.swing.JPanel panelKhuyenMai;
    private javax.swing.JPanel panelLichSu;
    private javax.swing.JPanel panelNhanVien;
    private javax.swing.JPanel panelSanPham;
    private javax.swing.JPanel panelThongKe;
    private javax.swing.JRadioButton rdo_chatlieu;
    private javax.swing.JRadioButton rdo_kichthuoc;
    private javax.swing.JRadioButton rdo_loaisp;
    private javax.swing.JRadioButton rdo_mausac;
    private javax.swing.JTable tbl_spchitiet;
    private javax.swing.JTable tbl_thuoctinh;
    private javax.swing.JTextField txt_dongiasp;
    private javax.swing.JTextField txt_maspct;
    private javax.swing.JTextField txt_soluongsp;
    private javax.swing.JTextField txt_tenspct;
    private javax.swing.JTextField txt_tenthuoctinh;
    private javax.swing.JTextField txt_timkiemsanpham;
    // End of variables declaration//GEN-END:variables

    void loadComboboxLoaiSanPham() {
        cbx_loaisanpham.removeAllItems();
        sanPhamMap.clear(); // Xóa dữ liệu cũ

        List<SanPham_ThuocTinh> dsSanPham = service_spchitiet.getLoaiSanPham(); // Lấy danh sách ID & Tên

        for (SanPham_ThuocTinh ms : dsSanPham) {
            cbx_loaisanpham.addItem(ms.getTen()); // Chỉ thêm TÊN vào JComboBox
            sanPhamMap.put(ms.getTen(), ms.getId()); // Lưu ID tương ứng với Tên
        }
    }

    void loadComboboxLoaiMauSac() {
        cbx_mausac.removeAllItems();
        mauSacMap.clear(); // Xóa dữ liệu cũ

        List<SanPham_ThuocTinh> dsMauSac = service_spchitiet.getLoaiMauSac(); // Lấy danh sách ID & Tên

        for (SanPham_ThuocTinh ms : dsMauSac) {
            cbx_mausac.addItem(ms.getTen()); // Chỉ thêm TÊN vào JComboBox
            mauSacMap.put(ms.getTen(), ms.getId()); // Lưu ID tương ứng với Tên
        }
    }

    void loadComboboxLoaiKichThuoc() {
        cbx_kichthuoc.removeAllItems();
        kichThuocMap.clear(); // Xóa dữ liệu cũ

        List<SanPham_ThuocTinh> dsKichThuoc = service_spchitiet.getLoaiKichThuoc(); // Lấy danh sách ID & Tên

        for (SanPham_ThuocTinh ms : dsKichThuoc) {
            cbx_kichthuoc.addItem(ms.getTen()); // Chỉ thêm TÊN vào JComboBox
            kichThuocMap.put(ms.getTen(), ms.getId()); // Lưu ID tương ứng với Tên
        }
    }

    void loadComboboxLoaiChatlieu() {
        cbx_chatlieu.removeAllItems();
        chatLieuMap.clear(); // Xóa dữ liệu cũ

        List<SanPham_ThuocTinh> dsChatLieu = service_spchitiet.getLoaiChatLieu(); // Lấy danh sách ID & Tên

        for (SanPham_ThuocTinh ms : dsChatLieu) {
            cbx_chatlieu.addItem(ms.getTen()); // Chỉ thêm TÊN vào JComboBox
            chatLieuMap.put(ms.getTen(), ms.getId()); // Lưu ID tương ứng với Tên
        }
    }
}

   
