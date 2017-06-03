package org.epe.core;

import java.util.List;

/**
 * sheet���϶���,����洢�����е�sheet�е�����
 *
 * @author ������
 */
public class SheetItem {

    List<HeadCell> headCells;
    private String name;
    private List<?> list;

    /**
     * �õ�����sheet�е�����
     *
     * @return
     */
    public List<HeadCell> getHeadCells() {
        return headCells;
    }

    /**
     * ���õ���sheet�е�����
     */
    public void setHeadCells(List<HeadCell> headCells) {
        this.headCells = headCells;
    }

    /**
     * sheet����
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * sheet����
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

}
