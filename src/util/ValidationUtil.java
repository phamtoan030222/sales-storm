/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author phamd
 */
public class ValidationUtil {
    
    public static boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
    
    public static boolean isNumberic(String str){
        try {
            Integer.parseInt(str);
            return true;
            
        } catch (Exception e) {
            return false;
        }
    }
    
    public static boolean kiemTraMa(String ma){
        return ma.matches("^[A-Z]{2}\\d{3}$");
    }
    
}
