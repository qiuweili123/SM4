package com.sh.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * 供应商实体
 * <P>File name : Vendor.java </P>
 * <P>Author : ZhangLingFeng </P>
 * <P>Date : 2014年8月12日 </P>
 */
@Entity
@Table(name = "u_users")
public class Users implements Serializable {
    /**
     * 字段或域定义：<code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -5313723380552512084L;
    /**
     * 主键ID
     */
    private Long id;
    /**
     * 供应商名称
     */
    private String name;
    /**
     * 供应商注册地址
     */
    private String address;
    private String makeContractDate;
    /**
     * 创建时间
     */
    private Date createdAt;

    public Users(String name, String address) {

        this.name = name;
        this.address = address;
    }

    public Users() {

    }

    public Users(Long id, String name, Date createdAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
    }

    public Users(Long id, String name, String address, Date createdAt) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Users [id=" + id + ", name=" + name + ", address=" + address + ", makeContractDate=" + makeContractDate + "]";
    }

    /**
     * 对时间的格式化
     */
    @JsonProperty("ContractDate")
    @Column(name = "make_Contract_Date")
    public String getMakeContractDate() {
        return makeContractDate;
    }

    /**
     * @param makeContractDate 对域 makeContractDate 的设置方法.
     */
    public void setMakeContractDate(String makeContractDate) {
        this.makeContractDate = makeContractDate;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "created_At")
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Id
    /**
     * @return Long 取得域 id 的方法。
     */ public Long getId() {

        return id;
    }

    /**
     * @param id 对域 id 的设置方法.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return String 取得域 name 的方法。
     */
    public String getName() {
        return name;
    }

    /**
     * @param name 对域 name 的设置方法.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return String 取得域 address 的方法。
     */
    @JsonInclude(Include.NON_EMPTY)
    public String getAddress() {
        return address;
    }

    /**
     * @param address 对域 address 的设置方法.
     */
    public void setAddress(String address) {
        this.address = address;
    }

}
