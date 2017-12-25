package net.ukr.sandrm.preProcessing.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

public class MyUtils {
	
	public static HashMap sortByValues(HashMap map) { 
		List list = new LinkedList(map.entrySet());
   
	   // Defined Custom Comparator here
	   Collections.sort(list, new Comparator() {
	        public int compare(Object o1, Object o2) {
	        	int result = ((Comparable) ((Map.Entry) (o2)).getValue()).compareTo(((Map.Entry) (o1)).getValue());
	        	return result;
	        }
	   });
	
	   // Here I am copying the sorted list in HashMap
	   // using LinkedHashMap to preserve the insertion order
	   HashMap sortedHashMap = new LinkedHashMap();
	   for (Iterator it = list.iterator(); it.hasNext();) {
		   Map.Entry entry = (Map.Entry) it.next();
		   sortedHashMap.put(entry.getKey(), entry.getValue());
	   } 
	   return sortedHashMap;
	}
	
	
	//public static boolean isCellEmpty(final XSSFCell cell) {
	
	public static boolean isCellEmpty(final Cell cell) {	
	    if (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
	        return true;
	    }

	    if (cell.getCellType() == Cell.CELL_TYPE_STRING && cell.getStringCellValue().trim().isEmpty()) {
	        return true;
	    }

	    return false;
	}

	
	public static void shifColumn(int nrCols, int columnIndex, Row r) {
		// shift to right
		for (int col = nrCols; col > columnIndex; col--) {
			Cell rightCell = r.getCell(col);
			//String cellValueStr; 
			//CellType rightCellType;

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

	
	private static void cloneCell(Cell cNew, Cell cOld) {
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
	
}
