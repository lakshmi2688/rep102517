package supporting.classes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelRead {

	XSSFWorkbook XWBook;
	XSSFSheet XWSheet;
	XSSFCell XCell;

	String path = "C:\\Users\\N0290327\\Desktop\\SeleniumScreenshots\\PolicyCreationInput.xlsx";

	public String[][] readExcel(String sheet) throws IOException {

		String[][] testData = null;

		try {
			FileInputStream inp = new FileInputStream(path);
			XWBook = new XSSFWorkbook(inp);
			XWSheet = XWBook.getSheet(sheet);
			System.out.println("XWSheet.getLastRowNum() : " + XWSheet.getLastRowNum());
			System.out.println("XWSheet.getFirstRowNum() : " + XWSheet.getFirstRowNum());
			int rowCount = XWSheet.getLastRowNum() - XWSheet.getFirstRowNum();
			int colCount = XWSheet.getRow(0).getLastCellNum();
			//System.out.println("colCount: " +colCount);
			testData = new String[rowCount][colCount];
			for (int i = 1; i < rowCount + 1; i++) {
				Row row = XWSheet.getRow(i);
				if (row != null) {
					for (int j = 0; j < row.getLastCellNum(); j++) {
						DataFormatter df = new DataFormatter();
						Cell cell = row.getCell(j);
						testData[i - 1][j] = df.formatCellValue(cell);
						System.out.println("****: " + testData[i-1][j]);
					}
				}

				// System.out.println();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return testData;

	}

}
