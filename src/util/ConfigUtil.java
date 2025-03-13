/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 *
 * @author phamd
 */
public class ConfigUtil {
    
    private static final String FILE_NAME = "config.properties";
    
    //Đọc dữ liệu từ file config
    public static Properties loadConfig() {
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream(FILE_NAME)) {
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
    
    //Ghi dữ liệu
    public static void saveConfig(String tenDangNhap, String matKhau, boolean nhoMatKhau) {
        Properties prop = new Properties();
        prop.setProperty("tenDangNhap", tenDangNhap);
        prop.setProperty("matKhau", nhoMatKhau ? matKhau : "");
        prop.setProperty("nhoMatKhau", String.valueOf(nhoMatKhau));
        
        try (OutputStream output = new FileOutputStream(FILE_NAME)) {
            prop.store(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
