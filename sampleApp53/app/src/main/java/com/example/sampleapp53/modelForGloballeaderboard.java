package com.example.sampleapp53;

public class modelForGloballeaderboard {
    String Email;
    Long XP;
    public modelForGloballeaderboard() {
    }

    public modelForGloballeaderboard(String email, Long XP) {
        this.Email = email;
        this.XP = XP;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Long getXP() {
        return XP;
    }

    public void setXP(Long XP) {
        this.XP = XP;
    }
}
