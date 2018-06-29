package com.library.tool;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.File;
import java.util.List;

/**
 * Created by mobk on 2015/11/25.
 */
public class NewExcel {
    //文档对象
    Workbook workbook = null;
    //工作本对象
    Sheet sheet = null;
    //单元格对象
    Cell cell = null;
    public String[][] openExcel(String excelPath){
        try {
            //打开文档
            workbook = Workbook.getWorkbook(new File(excelPath));
            try {
                //打开第一个工作本
                sheet = workbook.getSheet(0);
                String array[][] = new String[sheet.getRows()][sheet.getColumns()];
                for (int i = 0; i < array.length; i++) {
                    //sheet.getCell(列, 行);
                    for(int j = 0; j < array[i].length; j++){
                        array[i][j] = sheet.getCell(j, i).getContents();
                    }
                }
                return array;
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            String[][] a = {{"无法打开!!!!"}};
            System.out.println("无法打开");
            return a;
        }finally {
            //关闭文档
            workbook.close();
        }
        return null;
    }

    public void createExcel(String fileName, String tableName, List<String> topLine, List<List<String>> content){
        WritableWorkbook writableWorkbook = null;
        try {
            // 创建文档
            writableWorkbook = Workbook.createWorkbook(new File(fileName));
            // 生成工作表，参数0表示这是第一页
            WritableSheet writableSheet = writableWorkbook.createSheet(tableName, 0);
            // 指定单元格位置是第一列第一行(0, 0)以及单元格内容为张三
            // 将定义好的单元格添加到工作表中
            for(int i = 0; i < topLine.size(); i++) {
                writableSheet.addCell(new Label(i, 0, topLine.get(i)));
            }
            for(int i = 0; i<content.size(); i++){
                for(int j = 0; j<content.get(i).size(); j++) {
                    writableSheet.addCell(new Label(j, i + 1, content.get(i).get(j)));
                }
            }
            // 写入数据并关闭文件
            writableWorkbook.write();
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            if(writableWorkbook!=null){
                try {
                    writableWorkbook.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
