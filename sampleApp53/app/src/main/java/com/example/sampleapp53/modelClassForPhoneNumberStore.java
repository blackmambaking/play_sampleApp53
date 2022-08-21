package com.example.sampleapp53;

public class modelClassForPhoneNumberStore {
    String Name;
    long Mobile;

    public modelClassForPhoneNumberStore(String name, int mobile) {
        Name = name;
        Mobile = mobile;
    }

    public modelClassForPhoneNumberStore() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public long getMobile() {
        return Mobile;
    }

    public void setMobile(int mobile) {
        Mobile = mobile;
    }
}
