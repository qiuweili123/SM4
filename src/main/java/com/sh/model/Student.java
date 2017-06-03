package com.sh.model;

import com.sh.model.component.BaseEntity;
import com.share.annotations.Comment;

import javax.persistence.*;

@Entity
@Table(name = "t_student")
@Comment("考生表")
public class Student extends BaseEntity {


    private String name;


    private String cardId;

    private Teacher teacher;

    @Comment("姓名")
    @Column(length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Comment("身份证件号")
    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    @ManyToOne
    @JoinColumn(name = "t_teacher_id")
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", cardId=" + cardId + ", teacher=" + teacher + "]";
    }


}
