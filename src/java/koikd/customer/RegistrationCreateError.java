/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koikd.customer;

import java.io.Serializable;

/**
 *
 * @author Do Dat
 */
public class RegistrationCreateError implements Serializable{
    private String passwordLengthErr;
    private String firstNameLengthErr;
    private String lastNameLengthErr;
    private String confirmNotMacthed;
    private String emailIsExisted;

    public RegistrationCreateError() {
    }

    public RegistrationCreateError(String passwordLengthErr, String firstNameLengthErr, String lastNameLengthErr, String confirmNotMacthed, String emailIsExisted) {
        this.passwordLengthErr = passwordLengthErr;
        this.firstNameLengthErr = firstNameLengthErr;
        this.lastNameLengthErr = lastNameLengthErr;
        this.confirmNotMacthed = confirmNotMacthed;
        this.emailIsExisted = emailIsExisted;
    }

    public String getPasswordLengthErr() {
        return passwordLengthErr;
    }

    public void setPasswordLengthErr(String passwordLengthErr) {
        this.passwordLengthErr = passwordLengthErr;
    }

    public String getFirstNameLengthErr() {
        return firstNameLengthErr;
    }

    public void setFirstNameLengthErr(String firstNameLengthErr) {
        this.firstNameLengthErr = firstNameLengthErr;
    }

    public String getLastNameLengthErr() {
        return lastNameLengthErr;
    }

    public void setLastNameLengthErr(String lastNameLengthErr) {
        this.lastNameLengthErr = lastNameLengthErr;
    }

    public String getConfirmNotMacthed() {
        return confirmNotMacthed;
    }

    public void setConfirmNotMacthed(String confirmNotMacthed) {
        this.confirmNotMacthed = confirmNotMacthed;
    }

    public String getEmailIsExisted() {
        return emailIsExisted;
    }

    public void setEmailIsExisted(String emailIsExisted) {
        this.emailIsExisted = emailIsExisted;
    }      
}
