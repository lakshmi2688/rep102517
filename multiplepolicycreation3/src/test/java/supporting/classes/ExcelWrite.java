package supporting.classes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWrite {

	XSSFWorkbook XWBook;
	XSSFSheet XWSheet;
	XSSFCell Cell;
	XSSFRow Row;
	//int rowCount =0;

	String path = "C:\\Users\\N0290327\\Desktop\\SeleniumScreenshots\\PolicyCreationInput.xlsx";

	public void writeExcel(String sheet) throws IOException {

		//String[] testData = null;
		try{
		FileInputStream inp = new FileInputStream(path);
		XWBook = new XSSFWorkbook(inp);
		System.out.println("XWBook: " +XWBook);
		XWSheet = XWBook.getSheet(sheet);
		System.out.println("XWSheet: " +XWSheet);
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		//System.out.println("XWSheet.getLastRowNum() : " + XWSheet.getLastRowNum());
		//System.out.println("XWSheet.getFirstRowNum() : " + XWSheet.getFirstRowNum());
		//rowCount = XWSheet.getLastRowNum() - XWSheet.getFirstRowNum();
		

	}

	public String getCellData(int row, int col) {
		Cell = XWSheet.getRow(row).getCell(col);
		DataFormatter df = new DataFormatter();
		String cellData = df.formatCellValue(Cell);
		return cellData;
	}

	public void setCellData(int row, String name, String agency, String policy) throws IOException {
		Row = XWSheet.getRow(row);
		//System.out.println("Row ^^: " +Row);
			if(Row == null)
			{
				Row = XWSheet.createRow(row);
			}
		//Cell = Row.getCell(0);
			if ( Row.getCell(0) == null) {
				Row.createCell(0);
				Row.getCell(0).setCellValue(name);
			} else {
				Row.getCell(0).setCellValue(name);

			}
			
			if ( Row.getCell(1) == null) {
				Row.createCell(1);
				Row.getCell(1).setCellValue(agency);
			} else {
				Row.getCell(1).setCellValue(agency);

			}
			
			if ( Row.getCell(2) == null) {
				Row.createCell(2);
				Row.getCell(2).setCellValue(policy);
			} else {
				Row.getCell(2).setCellValue(policy);

			}
		FileOutputStream out = new FileOutputStream(path);

		XWBook.write(out);

		out.flush();
		out.close();
		

	}

}
