// package com.slp.arch.util;
// import org.apache.poi.ss.usermodel.CellStyle;
// import org.apache.poi.ss.usermodel.DataFormat;
// import org.apache.poi.xssf.usermodel.XSSFWorkbook;

// public class ExelUtil {

//   public static CellStyle styleCurrency(XSSFWorkbook workbook ) {
//     DataFormat format = workbook.createDataFormat();
//     CellStyle styleNumber = workbook.createCellStyle();
//     styleNumber.setDataFormat(format.getFormat("#,##0.00;-#,##0.00"));
//     return styleNumber;
//   }

//   public static CellStyle styleDate(XSSFWorkbook workbook ) {
//     DataFormat format = workbook.createDataFormat();
//     CellStyle styleDate = workbook.createCellStyle();
//     styleDate.setDataFormat(format.getFormat("dd/MM/yyyy"));
//     return styleDate;
//   }
// }
