package com.sh.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;

import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelUtil {

    /**
     * TODO
     */
    private String name;

    /**
     * 输出excel(本方法中为关闭输出流)
     *
     * @param sheetTitle       工作表名
     * @param headersLinkedMap 标题:对应值key
     * @param dtoList          值列表
     * @param os               输出流
     * @throws IOException io异常
     * @author zhuchh
     */
    public static void exportExcelWithHeaderFast2007(String sheetTitle, ArrayList<String> header, LinkedHashMap<String, String> headersLinkedMap, List<Map> dtoList) {
        exportExcelWithHeaderFast2007(sheetTitle, header, headersLinkedMap, dtoList);

    }

    public static void exportExcelWithHeaderFast2007(String sheetTitle, ArrayList<String> header, LinkedHashMap<String, String> headersLinkedMap, List<Map> dtoList, OutputStream os) throws IOException {
        // 声明一个工作薄
        SXSSFWorkbook workbook = new SXSSFWorkbook(1000);
        // 创建一个工作表
        Sheet sheet = workbook.createSheet(sheetTitle);
        // 设置工作表默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 15);

        // 生成单元格样式
        CellStyle cellStyle1 = workbook.createCellStyle();
        // 设置单元格样式1
        cellStyle1.setBorderTop(XSSFCellStyle.BORDER_THIN);
        cellStyle1.setBorderRight(XSSFCellStyle.BORDER_THIN);
        cellStyle1.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        cellStyle1.setBorderLeft(XSSFCellStyle.BORDER_THIN);

        // 生成字体1
        Font font1 = workbook.createFont();
        cellStyle1.setFont(font1);

        // 设置单元格样式2
        CellStyle cellStyle2 = workbook.createCellStyle();
        cellStyle2.setBorderTop(XSSFCellStyle.BORDER_THIN);
        cellStyle2.setBorderRight(XSSFCellStyle.BORDER_THIN);
        cellStyle2.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        cellStyle2.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        cellStyle2.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        cellStyle2.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        // 设置字体2
        Font font2 = workbook.createFont();
        font2.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
        int rowNum = 0;
        cellStyle2.setFont(font2);
        {

            Row row1 = sheet.createRow(rowNum);
            row1.setHeight((short) 0x200);
            rowNum++;
            short cellNum = 0;
            for (String headInfo : header) {
                Cell cell = row1.createCell(cellNum);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(headInfo);
                cell.setCellStyle(cellStyle1);
                cellNum++;
            }
        }
        // 标题
        // int rowNum = 0;
        Row row = sheet.createRow(rowNum);
        row.setHeight((short) 0x200);
        rowNum++;
        Set<String> titleSet = headersLinkedMap.keySet();
        short cellNum = 0;
        for (String title : titleSet) {
            Cell cell = row.createCell(cellNum);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(title);
            cell.setCellStyle(cellStyle1);
            cellNum++;
        }

        // 输出数据
        Collection<String> valueKeySet = headersLinkedMap.values();
        // 每次取出1000条数据进行导出?直接传入要执行的导出sql语句,在这里进行分页?
        for (Map dto : dtoList) {
            // 每1000条件写入文件一次,第一个1000条的时候创建一个文件,后面的直接向文件里面追加内容

            cellNum = 0;
            row = sheet.createRow(rowNum);
            rowNum++;
            for (String key : valueKeySet) {
                Cell cell = row.createCell(cellNum);
                cellNum++;
                Object obj = dto.get(key);

                if (obj == null) {
                    cell.setCellValue("");
                } else if (obj instanceof Integer) {
                    int intVal = (Integer) obj;
                    cell.setCellValue(intVal);
                } else if (obj instanceof BigDecimal) {
                    Double doubleVal = ((BigDecimal) obj).doubleValue();
                    cell.setCellValue(doubleVal);
                } else if (obj instanceof Float) {
                    float floatVal = (Float) obj;
                    cell.setCellValue(floatVal);
                } else if (obj instanceof Double) {
                    double doubleVal = (Double) obj;
                    cell.setCellValue(doubleVal);
                } else if (obj instanceof Long) {
                    long longVal = (Long) obj;
                    cell.setCellValue(longVal);
                } else if (obj instanceof Boolean) {
                    boolean booleanVal = (Boolean) obj;
                    cell.setCellValue(booleanVal);
                } else if (obj instanceof Date) {
                    Date date = (Date) obj;
                    // cell.setCellValue(date);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    cell.setCellValue(sdf.format(date));
                } else if (obj instanceof byte[]) {

                } else {
                    cell.setCellValue(obj.toString());
                }
                cell.setCellStyle(cellStyle2);
            }
        }
        // 临时写入文件名称
        String saveDirectory = System.getProperty("user.dir");
        String fileName = saveDirectory + "\\" + String.valueOf(new Date().getTime()) + sheetTitle + ".xlsx";
        System.out.println("##fileName==" + fileName);
        File file1 = new File(fileName);
        if (file1.exists()) {
            file1.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(file1);
        workbook.write(out);
        out.flush();
        out.close();
        // 读到流中
        if (os != null) {
            InputStream inStream = new FileInputStream(fileName);// 文件的存放路径
            byte[] b = new byte[100];
            int len;
            try {
                while ((len = inStream.read(b)) > 0) os.write(b, 0, len);
                inStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            File file = new File(fileName);
            if (file.exists()) {
                file.delete();
            }
        }
        // FileOutputStream out;
        // try {
        // out = new FileOutputStream(new File("E:/workbook2.xls"));
        // workbook.write(out);
        // out.close();
        // } catch (FileNotFoundException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // } catch (IOException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        // workbook.write(os);
    }

    public static void displayMemery() {
        DecimalFormat df = new DecimalFormat("0.00");
        long totalMem = Runtime.getRuntime().totalMemory();
        long maxMem = Runtime.getRuntime().maxMemory();
        long freeMem = Runtime.getRuntime().freeMemory();
        System.out.println("totalMery=" + df.format(totalMem / 1000000F) + "#maxMem=" + df.format(maxMem / 1000000F) + "#freeMem=" + df.format(freeMem / 1000000F) + " MB");
    }
}
