package com.commons.test;

import java.util.List;

/**
 * Created by lenovo on 2018/1/3.
 */
public class Filed {
    public String filedName;
    public Integer dataType;

    public List<FiledValidator> filedValidatorList;

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public List<FiledValidator> getFiledValidatorList() {
        return filedValidatorList;
    }

    public void setFiledValidatorList(List<FiledValidator> filedValidatorList) {
        this.filedValidatorList = filedValidatorList;
    }

    public String getFiledName() {
        return filedName;
    }

    public void setFiledName(String filedName) {
        this.filedName = filedName;
    }
}
