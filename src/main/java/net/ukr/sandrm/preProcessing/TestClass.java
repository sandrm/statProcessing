package net.ukr.sandrm.preProcessing;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class TestClass {
	static String inputFile = "D:\\study\\java_proj\\debug\\debug.xlsx";
	static String outputFile = "D:\\study\\java_proj\\debug\\debug_out.xlsx";
	private static XSSFWorkbook myWorkBook; 
	private static XSSFSheet mySheet;
	
/*	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("ProcDocs.main()");
		
		try {
			
			myWorkBook = new XSSFWorkbook (new FileInputStream(inputFile)); // Return first sheet from the XLSX workbook
			mySheet = myWorkBook.getSheetAt(0);
			System.out.println("lastRowNum=" + mySheet.getLastRowNum());
			
			
//			for (Row row : mySheet) {
//				for (Cell cell : row){
//					//cell.setCellStyle(stayleOrigin);
//					int columnIndex = cell.getColumnIndex();
//					if (columnIndex == 2){
//						System.out.println("ROW = " + row.getRowNum() + " CELL = "  + columnIndex);
//						Cell rightCell = row.getCell(columnIndex++);
//						Cell newCell = row.createCell(columnIndex++);
//						row.getCell(columnIndex++).setCellType(cell.getCellType());
//						row.getCell(columnIndex++).setCellStyle(cell.getCellStyle());
//					}
//				}
//			}

			int nrRows = mySheet.getLastRowNum()+1;
			int nrCols = mySheet.getRow(0).getLastCellNum();
			int columnIndex = 2;
			
			for (int row = 0; row < nrRows; row++) {
				Row r = mySheet.getRow(row);

				if (r == null) {
					continue;
				}
				
				
				//TestClass testClass = new TestClass();
				//testClass.shifColumn(nrCols, columnIndex, r);
				TestClass testClass = new TestClass();
				ShiftCells shiftCells = testClass.new ShiftCells(nrCols, columnIndex, r, testClass);
				Thread thread = new Thread(shiftCells);
				
				int cellType = Cell.CELL_TYPE_BLANK;
				r.createCell(columnIndex, cellType);
				
				thread.start();
			}			
			
			Thread.currentThread().yield();
			
			saveNew();			
			myWorkBook.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
*/

	
	public void shifColumn1(int nrCols, int columnIndex, Row r) {
		// shift to right
		for (int col = nrCols; col > columnIndex; col--) {
			Cell rightCell = r.getCell(col);
			String cellValueStr; 
			CellType rightCellType;

			if (rightCell == null) {
				rightCell = r.createCell(col);
			}
			
			Cell leftCell = r.getCell(col - 1);			
			if (leftCell != null) {
				rightCell.setCellType(leftCell.getCellType());
				rightCell.setCellStyle(leftCell.getCellStyle());
				//rightCell.getCellStyle().setFillBackgroundColor(HSSFColor.AQUA.index);
				cloneCell(rightCell, leftCell);
			}
		}
	}	
	
	
	
/*
	public void shifColumn(int nrCols, int columnIndex, Row r) {
		// shift to right
		for (int col = nrCols; col > columnIndex; col--) {
			Cell rightCell = r.getCell(col);
			if (rightCell != null) {
				r.removeCell(rightCell);
			}

			Cell leftCell = r.getCell(col - 1);

			if (leftCell != null) {
				Cell newCell = r.createCell(col, leftCell.getCellType());
				cloneCell(newCell, leftCell);
			}
		}
	}	
*/
	
	
	public void cloneCell(Cell cNew, Cell cOld) {
		cNew.setCellComment(cOld.getCellComment());
		//cNew.setCellStyle(cOld.getCellStyle());

		switch (cOld.getCellType()) {
			case Cell.CELL_TYPE_BOOLEAN: {
				cNew.setCellValue(cOld.getBooleanCellValue());
				break;
			}
			case Cell.CELL_TYPE_NUMERIC: {
				cNew.setCellValue(cOld.getNumericCellValue());
				break;
			}
			case Cell.CELL_TYPE_STRING: {
				cNew.setCellValue(cOld.getStringCellValue());
				break;
			}
			case Cell.CELL_TYPE_BLANK: {
				cNew.setCellValue("");
				break;
			}
			case Cell.CELL_TYPE_ERROR: {
				cNew.setCellValue(cOld.getErrorCellValue());
				break;
			}
			case Cell.CELL_TYPE_FORMULA: {
				cNew.setCellFormula(cOld.getCellFormula());
				break;
			}
		}
	}	
		
	
	private static void saveNew(){
		File outWB = new File(outputFile);
		OutputStream out = null;
		try {
			out = new FileOutputStream(outWB);
			myWorkBook.write(out);
						
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
}
