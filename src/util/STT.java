/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author dungc
 */
import javax.swing.table.DefaultTableModel;
import javax.swing.event.TableModelListener;

public class STT {
    public static void updateSTT(DefaultTableModel model) {
        // Lưu danh sách listener hiện có
        TableModelListener[] listeners = model.getTableModelListeners();
        
        // Xóa tất cả listener để tránh vòng lặp vô hạn
        for (TableModelListener listener : listeners) {
            model.removeTableModelListener(listener);
        }

        // Cập nhật STT
        for (int i = 0; i < model.getRowCount(); i++) {
            model.setValueAt(i + 1, i, 0);
        }

        // Khôi phục listener sau khi cập nhật xong
        for (TableModelListener listener : listeners) {
            model.addTableModelListener(listener);
        }
    }
}
