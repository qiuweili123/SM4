package org.epe.core;

import java.util.ArrayList;
import java.util.List;

/**
 * EREר��Excel Object���ݴ洢��
 *
 * @author ������
 */
public class HeadCell {
    private String name;
    private String columnName;
    private Object value;
    private Class<?> fieldType;
    private boolean isHidden;
    private String paten = "yyyy-MM-dd";

    public List<HeadCell> addHeadCell(HeadCell... headCell) {
        List<HeadCell> list = new ArrayList<HeadCell>();
        for (HeadCell headCell2 : headCell) {
            list.add(headCell2);
        }
        return list;
    }

    /**
     * �õ���ͷ������
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * ������ͷ������
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * �õ���ͷӢ����
     *
     * @return
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * ������ͷӢ����
     *
     * @param columnName
     */
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    /**
     * �õ�ֵ
     *
     * @return
     */
    public Object getValue() {
        return value;
    }

    /**
     * ����ֵ
     *
     * @param value
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * �Ƿ����أ���δʵ��
     *
     * @return
     */
    public boolean isHidden() {
        return isHidden;
    }

    /**
     * �Ƿ����أ���δʵ��
     *
     * @return
     */
    public void setHidden(boolean isHidden) {
        this.isHidden = isHidden;
    }

    /**
     * �õ��ֶ�����
     *
     * @return
     */
    public Class<?> getFieldType() {
        return fieldType;
    }

    /**
     * �����ֶ�����
     *
     * @param fieldType
     */
    public void setFieldType(Class<?> fieldType) {
        this.fieldType = fieldType;
    }

    public String getPaten() {
        return paten;
    }

    public void setPaten(String paten) {
        this.paten = paten;
    }

}
