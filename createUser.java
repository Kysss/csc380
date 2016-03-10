/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readingexcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author Reeves
 */
public class createUser {
    
    public createUser(String User, String password, String email) throws FileNotFoundException, IOException{
        FileInputStream f = new FileInputStream(new File("/Users/Reeves/Desktop/excel.xls"));
        HSSFWorkbook wb = new HSSFWorkbook(f);
        HSSFSheet sheet = wb.getSheetAt(0);
        Cell cell = null;
        Row row = null;
        sheet.getLastRowNum();

        row = sheet.createRow(sheet.getLastRowNum());
        sheet.createRow(sheet.getLastRowNum()+1);

        for (int i = 0; i < 5; i++) {
            cell = row.createCell(i);
            if(i==0){
                cell.setCellValue(User);
            }
            if(i == 1){
                cell.setCellValue(password);
            }
            
            if(i == 2){
                cell.setCellValue(email);
            }

        }
        f.close();
        FileOutputStream output_file = new FileOutputStream(new File("/Users/Reeves/Desktop/excel.xls"));
        wb.write(output_file);
        output_file.close();
    }
    
}
