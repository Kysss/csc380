/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.*;

/**
 *
 * @author Reeves
 */
public class createUser {
    
    public createUser(String User, String password, String email) throws FileNotFoundException, IOException{
       // File file = new File("testing.xls");
       // URL url = createUser.class.getClassLoader().getResource("C:/Users/Yingying Xia/desktop/userdata.xls");
     //   System.out.println(url);
        FileInputStream f = new FileInputStream(new File("‪C:/Users/Yingying Xia/desktop/userdata.xls"));
        HSSFWorkbook wb = new HSSFWorkbook(f); //create a HSSFWorkbook using at this path
        HSSFSheet sheet = wb.getSheetAt(0); //Get the HSSFSheet object at the given index.
        Cell cell = null;
        Row row = null;
        sheet.getLastRowNum(); //Gets the number last row on the sheet.

        row = sheet.createRow(sheet.getLastRowNum()); //last row
        sheet.createRow(sheet.getLastRowNum()+1);  //create a new row after the last row

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
        FileOutputStream output_file = new FileOutputStream(new File("‪C:/Users/Yingying Xia/desktop/userdata.xls"));
        wb.write(output_file); //HSSFWorkbook
        output_file.close();
    }
    
}
