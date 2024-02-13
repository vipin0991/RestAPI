package basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDrivenTest {
	
	public static ArrayList<String> getData(String sheetNm, String testcasename) throws IOException {
		FileInputStream fi = new FileInputStream(new File("../RestAPIExcelDriven/DataDemo.xlsx"));
		XSSFWorkbook wb = new XSSFWorkbook(fi);
		ArrayList<String> a = new ArrayList<String>();
		
		 int numOfSheets = wb.getNumberOfSheets(); 
		 for(int i=0;i<numOfSheets;i++) {
			 if(wb.getSheetName(i).equalsIgnoreCase(sheetNm)) {
				 XSSFSheet sheet = wb.getSheetAt(i);
				 Iterator<Row> rows= sheet.rowIterator();
				 Row firstrow = rows.next();
				 Iterator<Cell> cells = firstrow.cellIterator();
				 int k=0;
				 int column = 0;
				 while(cells.hasNext()) {
					 Cell ce = cells.next();
					 if(ce.getStringCellValue().equalsIgnoreCase("Testcases")) {
						 column = k;
					 }
					 k++;
				 }
				 
				 while(rows.hasNext()) {
					Row r = rows.next();
					if(r.getCell(column).getStringCellValue().equalsIgnoreCase(testcasename)) {
						Iterator<Cell> cls = r.cellIterator();
						while(cls.hasNext()) {
							Cell c = cls.next();
							//to handle if any cell is having string or numeric value
							if(c.getCellType()==CellType.STRING) {
								a.add(c.getStringCellValue());
							}
							else {
								a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
							}
							
							 
						}
					}
					 
				 }
				 
			 }
		 
		 }
		 return a;
		 
	/*	XSSFSheet ws = wb.getSheet("Sheet1");
		
		int totalRows = ws.getLastRowNum();
		int totalCells = ws.getRow(0).getLastCellNum();
		System.out.println(totalCells);
		System.out.println(ws.getRow(0).getCell(0));
		
		for(int i=0;i<totalRows;i++) {
			for(int j=0;j<totalCells;j++) {
				
				String cellValue = ws.getRow(i).getCell(j).getStringCellValue();
				if(cellValue.equalsIgnoreCase("Purchase")) {
					System.out.println(i+"   "+j);
				}
				
			}
		}*/
		
	}



}
