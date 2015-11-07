package org.balthie.demo.opensource.apache.poi.excel.HSSF;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

//Old HSSF Code
public class XLSFileDemo
{
public static void main(String[] args) throws IOException
{
 // import org.apache.poi.hssf.usermodel.*;
    HSSFWorkbook wb = new HSSFWorkbook();
    // create a new sheet
    HSSFSheet s = wb.createSheet();
    // declare a row object reference
    HSSFRow r = null;
    // declare a cell object reference
    HSSFCell c = null;
    // create 2 cell styles
    HSSFCellStyle cs = wb.createCellStyle();
    HSSFCellStyle cs2 = wb.createCellStyle();
    HSSFDataFormat df = wb.createDataFormat();

    // create 2 fonts objects
    HSSFFont f = wb.createFont();
    HSSFFont f2 = wb.createFont();

    // Set font 1 to 12 point type, blue and bold
    f.setFontHeightInPoints((short) 12);
    f.setColor( HSSFColor.RED.index );
    f.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

    // Set font 2 to 10 point type, red and bold
    f2.setFontHeightInPoints((short) 10);
    f2.setColor( HSSFColor.RED.index );
    f2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

    // Set cell style and formatting
    cs.setFont(f);
    cs.setDataFormat(df.getFormat("#,##0.0"));

    // Set the other cell style and formatting
    cs2.setBorderBottom(cs2.BORDER_THIN);
    cs2.setDataFormat(HSSFDataFormat.getBuiltinFormat("text"));
    cs2.setFont(f2);


    // Define a few rows
    for(short rownum = (short)0; rownum < 30; rownum++) {
        r = s.createRow(rownum);
        for(short cellnum = (short)0; cellnum < 10; cellnum += 2) {
            c = r.createCell(cellnum);
            HSSFCell c2 = r.createCell(cellnum+1);

            c.setCellValue((double)rownum + (cellnum/10));
            c2.setCellValue(new HSSFRichTextString("Hello! " + cellnum));
        }
    }

    // Save
    FileOutputStream out = new FileOutputStream("d:/workbook.xls");
    wb.write(out);
    out.close();
}
}
