package parameters;
import java.io.File;

import java.io.FileInputStream;

import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;

import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadFromExcel {
	private String pathFileExcel = "src\\data\\";
	private String filename;

	public ReadFromExcel(String file) {
		this.filename = file;
	}

	public String[][] ReadData(String SheetName) throws Exception {
		File file = new File(pathFileExcel + this.filename);
		FileInputStream inputStream = new FileInputStream(file);

		if (!this.filename.endsWith("xlsx")) {
			throw new Exception("Need .xlsx file");
		}

		Workbook WorkBook = new XSSFWorkbook(inputStream);

		Sheet sheet = WorkBook.getSheet(SheetName);
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		int cellCount = sheet.getRow(1).getLastCellNum();

		DataFormatter formatter = new DataFormatter();
		String[][] array = new String[rowCount][cellCount];

		for (int i = 0; i < rowCount; i++) {
			Row row = sheet.getRow(i + 1);
			for (int j = 0; j < row.getLastCellNum(); j++) {
				array[i][j] = formatter.formatCellValue(row.getCell(j));
			}
		}
		inputStream.close();
		return array;
	}

}
