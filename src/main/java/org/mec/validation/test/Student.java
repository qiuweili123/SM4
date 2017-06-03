package org.mec.validation.test;

import org.mec.validation.annotation.*;

import java.util.Date;

public class Student {
    private String name;
    @IDCard
    private String idCard;
    @Phone
    private String phone;
    @Tel
    private String tel;
    @ZipCode
    private String zipCode;
    @PInteger
    private Integer pinteger;
    @PFloat
    private float pfloat;
    @Email
    private String email;

    @PInteger
    private Integer TestInte;
    @org.mec.validation.annotation.Date
    private Date date;
    private int sid;

    public Integer getTestInte() {
        return TestInte;
    }

    public void setTestInte(Integer testInte) {
        TestInte = testInte;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Integer getPinteger() {
        return pinteger;
    }

    public void setPinteger(Integer pinteger) {
        this.pinteger = pinteger;
    }

    public float getPfloat() {
        return pfloat;
    }

    public void setPfloat(float pfloat) {
        this.pfloat = pfloat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
