package org.epe.core.test;

import org.epe.annotations.ECell;

public class Teacher {

    @ECell(name = "姓名")
    private String name;
    @ECell(name = "学历")
    private String education;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }


}
