/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import dao.OTPDAO;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import service.OTPService;
import util.EmailUtil;

/**
 *
 * @author phamd
 */
public class OTPServiceImpl implements OTPService {

    private final Map<String, String> otpStorage = new HashMap<>();

    @Override
    public String generateOTP(String email) {
        String otp = EmailUtil.sendOTP(email);

        if (otp != null) {
            otpStorage.put(email, otp);
            return otp;
        }

        return null;
    }

    @Override
    public boolean verifyOTP(String email, String inputOtp) {
        String storedOtp = otpStorage.get(email);

        if (storedOtp != null && storedOtp.equals(inputOtp)) {
            otpStorage.remove(email); // Xóa OTP sau khi xác thực thành công
            return true;
        }
        return false;
    }

}
