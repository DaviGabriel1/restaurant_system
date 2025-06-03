package com.davi.restaurant_burguer.services;

import com.davi.restaurant_burguer.dtos.otp.RequestOtp;
import com.davi.restaurant_burguer.interfaces.ISendMessageServiceAdapter;
import com.davi.restaurant_burguer.models.OtpCode;
import com.davi.restaurant_burguer.repositories.OtpCodeRepository;
import com.davi.restaurant_burguer.utils.TimeUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class OtpService {
    private final Random random;
    private final OtpCodeRepository otpCodeRepository;
    private final ISendMessageServiceAdapter sendMessageService;
    private static final int CODE_LENGTH = 6;

    public OtpService(OtpCodeRepository otpCodeRepository, ISendMessageServiceAdapter sendMessageService) {
        this.otpCodeRepository = otpCodeRepository;
        this.sendMessageService = sendMessageService;
        this.random = new Random();
    }

    public boolean isValid(String phone, String code) {
        LocalDateTime now = TimeUtils.getZonedDateTime().toLocalDateTime();
        System.out.println(now);
        OtpCode otpCode = this.otpCodeRepository.findOneValidCode(phone, code, now);
        System.out.println(otpCode);
        if(otpCode != null){
            otpCode.setActive(false);
            this.otpCodeRepository.save(otpCode);
            return true;
        }
        return false;
    }

    public void generateCode(RequestOtp otp) {
        this.otpCodeRepository.setActiveCodeToFalse(otp.phone());
        String code = this.createRandomCode();
        this.saveCode(otp.phone(), code);
        this.sendMessageService.sendMessage("Olá "+otp.name()+"!, O seu código de verificação é: "+code,otp.phone());
    }

    public void saveCode(String phone, String code) {
        OtpCode otpCode = new OtpCode(phone, code);
        this.otpCodeRepository.save(otpCode);
    }

    private String createRandomCode() {
        StringBuilder code = new StringBuilder();
        for(int i = 0;i < CODE_LENGTH;i++) {
            String number = String.valueOf(this.random.nextInt(10));
            code.append(number);
        }
        return code.toString();
    }
}
