package com.sh.model;

import com.sh.model.component.Address;
import com.sh.model.component.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_teacher")
public class Teacher extends BaseEntity {

    private String name;

    private Double wages;

    private Address address;

    private Date joinDate;

    private Serializable picture;


    private String description;

    private Set<Student> students = new HashSet<Student>();

    @Temporal(TemporalType.DATE)//设置为时间类型
    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    @Column(name = "user_wages", columnDefinition = " double(12,2)")//设置属性wages对应的字段为user_wages，12位数字可保留两位小数，可以为空
    public Double getWages() {
        return wages;
    }

    public void setWages(Double wages) {
        this.wages = wages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Embedded
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Lob//对应clob
    @Basic(fetch = FetchType.LAZY)
//@Basic标记可以指定实体属性的加载方式.因为这两种类型的数据一般占用的内存空间比较大，所以通常使用延迟加载的方式，与@Basic标记同时使用，设置加载方式为FetchType.LAZY

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Lob//对应blob
    @Basic(fetch = FetchType.LAZY)
    public Serializable getPicture() {
        return picture;
    }

    public void setPicture(Serializable picture) {
        this.picture = picture;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher")
    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

}
