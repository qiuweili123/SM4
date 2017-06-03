package org.epe.core;

import java.util.List;

public class BeanItem {

    private List<?> list;
    private StringBuffer message;

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public StringBuffer getMessage() {
        return message;
    }

    public void setMessage(StringBuffer message) {
        this.message = message;
    }

}
