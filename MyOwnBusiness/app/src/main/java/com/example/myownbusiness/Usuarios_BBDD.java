package com.example.myownbusiness;

import java.io.Serializable;

public class Usuarios_BBDD implements Serializable {

    private String userCode;
    private String accName;
    private String accPassword;
    private String emailAddress;
    private String birthDate;
    private String visualName;
    private String direction;
    private String phoneNumber;

    //Contructor.
    public Usuarios_BBDD(String userCode, String accName,
                         String accPassword, String emailAddress, String birthDate, String visualName, String direction, String phoneNumber) {
        this.userCode = userCode;
        this.accName = accName;
        this.accPassword = accPassword;
        this.emailAddress = emailAddress;
        this.birthDate = birthDate;
        this.visualName = visualName;
        this.direction = direction;
        this.phoneNumber = phoneNumber;
    }

    //Void contructor.
    public Usuarios_BBDD() {
    }

    //GETTER -------------------------------------------------------------------------------------->
    public String getUserCode() {
        return userCode;
    }

    public String getAccName() {
        return accName;
    }

    public String getAccPassword() {
        return accPassword;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getVisualName() {
        return visualName;
    }

    public String getDirection() {
        return direction;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    //SETTER -------------------------------------------------------------------------------------->1

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public void setAccPassword(String accPassword) {
        this.accPassword = accPassword;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setVisualName(String visualName) {
        this.visualName = visualName;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
