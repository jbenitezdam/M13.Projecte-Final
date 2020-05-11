package com.example.myownbusiness;

public class Usuarios_BBDD {

    private String userCode;
    private String accName;
    private String accPassword;
    private String emailAddress;
    private String birthDate;

    public Usuarios_BBDD(String userCode, String accName, String accPassword, String emailAddress, String birthDate) {
        this.userCode = userCode;
        this.accName = accName;
        this.accPassword = accPassword;
        this.emailAddress = emailAddress;
        this.birthDate = birthDate;
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

    //SETTER -------------------------------------------------------------------------------------->
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
}
