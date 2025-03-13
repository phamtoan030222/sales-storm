/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author phamd
 */
public interface OTPService {
    
    String generateOTP(String email);
    
    boolean verifyOTP(String email, String otp);
    
}
