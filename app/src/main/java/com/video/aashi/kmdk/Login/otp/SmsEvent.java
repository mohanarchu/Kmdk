package com.video.aashi.kmdk.Login.otp;

public class SmsEvent {
    private String otp;

    public SmsEvent(String otp) {
        this.otp = otp;
    }

    public String getOtp() {
        return otp;
    }
}
