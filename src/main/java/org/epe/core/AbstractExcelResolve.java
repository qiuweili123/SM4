package org.epe.core;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.epe.core.emnu.FileExtendsName;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 授权说明：
 * <p>
 * Copyright(c) 2011 ERE1.2
 * <p>
 * ERE为一个开源项目，目前我重构了ERE解析部份，待久化模块仍在进行中，ERE遵循GPL协议，
 * 用户可以自由修改本软件,如在互联网上传或者发表ERE，请注明出处。
 * <p>
 * ERE1.2支持office2003与office2007,基于POI3.8,为目前最新版本，ERE1.2只重构了逐行读取，
 * 逐列读取与复杂表头算法还没有完成重构，将在后续完成。ERE1.2可以满足项目中简单的表格
 * 导入导出，以及多Sheet的导入导出，不支持VBA，如果您的Excel中有VBA，ERE可能无法读取，
 * 出现这种情况，请将VBA删除。 ERE成功应用于山西移动内审管理系统、浙江移动需求管理平台。
 * <p>
 * 改变日志
 * 1、重构逐行读取
 * 2、增加注解配置，在需要进行EOM的类中标注注解可以映射指定字段
 * 3、支持多sheet
 * 4、增加EOM，ERE1.2以前的版本不支持EOM，即Excel对象关系映射，可以方便的映射对象。
 * 5、逐列读取与复杂表头暂未重构，将在后续版本推出，ERE1.2以前的版本支持。
 * 6、暂不支持Excel公式
 * 7、EDM（Excel数据库映射）暂未重构，ERE1.2以前的版本支持。
 * <p>
 * ERE核心解析类，实现了EOM（Excel object Mapping）,OEM(Object Excel mapping)
 * 譔类可以导出List<T>中的数据，也可以有将Excel中的数据以List<T>的形式返回。 list中的对象可由用户自行定义。
 * 在目标实体中标注ECell可以将对象映射到Excel中
 *
 * @author 李声波
 */
public abstract class AbstractExcelResolve {

    protected Workbook[] workbook;
    protected Field[] fields;
    protected String serverPath;
    protected int fieldCount;
    protected List<ResolveException> exceptionlist;
    private InputStream is;
    private List<SheetItem> sheetItems;
    private List<HeadCell> headCells;
    private String sheetName;

    /**
     * 生成数据源
     *
     * @param serverPath 服务器路径
     * @return
     */
    public void genericDataSource(String serverPath) {
        for (int i = 0; i < this.workbook.length; i++) {
            Workbook wb = this.workbook[i];
            String filename = serverPath;
            if (wb instanceof SXSSFWorkbook) {
                System.out.println("##" + wb.getClass().getSimpleName());
                filename = filename + "x";
            }

            FileOutputStream out;
            try {
                out = new FileOutputStream(filename);
                wb.write(out);
                out.flush();
                out.close();
                // return true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 读取数据源
     *
     * @param serverPath
     */
    public void readerDataSource() {
        try {
            is = new FileInputStream(serverPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取数据源
     *
     * @param serverPath
     */
    public void readerDataSource(InputStream inputStream) {
        try {
            is = inputStream;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建工作薄
     */
    protected void createWorkbook() {
        try {
            System.out.println("##==" + (is == null));
            if (is != null) {
                String extendsName = getServerPath().substring(getServerPath().lastIndexOf(".") + 1);
                if (extendsName.equals(FileExtendsName.xls.getFileExtendsName()))
                    this.workbook = new Workbook[]{new HSSFWorkbook(is)};
                if (extendsName.equals(FileExtendsName.xlsx.getFileExtendsName()))
                    this.workbook = new Workbook[]{new XSSFWorkbook(is)};
            } else {
                this.workbook = new Workbook[]{new SXSSFWorkbook(1000)};
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理数据，该方法将处理包含List<T>中的对象数据，ERE目前只支持list
     *
     * @param obj List<T>
     * @return List<HeadCell>
     * HeadCell是ERE专用的Excel与Object的数据存储对象，所有的EOM都将通过此对象存储数据
     */
    public abstract List<HeadCell> dataProcessFactory(Object obj);

    /**
     * 导入Excel，结果集以List<SheetItem>返回，SheetItem存储了多个sheet中的数据
     *
     * @return List<SheetItem>
     */
    public abstract <T extends Object> List<SheetItem> inputExcel();

    /**
     * 导出Excel
     *
     * @param list
     */
    public abstract void exportExcel(SheetItem... sheetItems);


    /**
     * excel object 映射方法
     *
     * @return
     */
    public abstract <T extends Object> Map<String, List<T>> excelObjectMapping(List<HeadCell> list, Class<T> clss);

    /**
     * 导出报表
     *
     * @param clss
     * @return
     */
    public abstract <T extends Object> boolean exportReport(Sheet sheet);

    /**
     * 获取Excel的Sheet
     *
     * @return
     */
    public abstract List<Sheet> getSheet(String sheetName);

    /**
     * 服务器径
     *
     * @return 服务器路径
     */
    public String getServerPath() {
        return serverPath;
    }

    /**
     * 服务器路径
     *
     * @param serverPath
     */
    public void setServerPath(String serverPath) {
        this.serverPath = serverPath;
    }

    /**
     * 得到sheet集合
     *
     * @return
     */
    public List<SheetItem> getSheetItems() {
        return sheetItems;
    }

    /**
     * 设置sheet集合
     *
     * @param sheetItems
     */
    public void setSheetItems(SheetItem... sheetItems) {
        this.sheetItems = new ArrayList<SheetItem>();
        for (SheetItem sheetItem : sheetItems) {
            this.sheetItems.add(sheetItem);
        }
    }

    /**
     * 得到数据集
     *
     * @return
     */
    public List<HeadCell> getHeadCells() {
        return headCells;
    }

    /**
     * 设置数据集
     *
     * @param headCells
     */
    public void setHeadCells(List<HeadCell> headCells) {
        this.headCells = headCells;
    }

    /**
     * 得到sheet名称，只限单个sheet使用
     *
     * @return
     */
    public String getSheetName() {
        return sheetName;
    }

    /**
     * 设置sheet名称，只限单个sheet使用
     *
     * @param sheetName
     */
    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public Integer getFieldCount() {
        return fieldCount;
    }

    public void setFieldCount(Integer fieldCount) {
        this.fieldCount = fieldCount;
    }

    public List<ResolveException> getExceptionlist() {
        return exceptionlist;
    }

    public void setExceptionlist(List<ResolveException> exceptionlist) {
        this.exceptionlist = exceptionlist;
    }

}
