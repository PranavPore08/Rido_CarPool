package com.example.privatetransportservice.Domain;

public class User {
   String mobile;
   String name;
   String email;
   String gender;

    public User(String mobile, String name, String email, String gender) {
        this.mobile = mobile;
        this.name = name;
        this.email = email;
        this.gender = gender;
    }

    public User(String userMobileNumber, String name, String mobEmail) {
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
