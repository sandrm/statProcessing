package net.ukr.sandrm.preProcessing;

import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import net.ukr.sandrm.preProcessing.model.ColumnAttr;
import net.ukr.sandrm.preProcessing.utils.MyUtils;


public abstract class AbstractProcessor {
	final static protected Logger logger = Logger.getLogger(AbstractProcessor.class);
	
	protected void process(XSSFSheet mySheet, Map<Integer,String> indexMap, Map<String,ColumnAttr> attrMap){
        for (Row row : mySheet) {
        	int zeroLine = row.getRowNum();
        	if (zeroLine == 0)
        		continue;
        	
        	for (Cell cell : row) {                	
    			String columnName = indexMap.get(cell.getColumnIndex());
    			if(columnName != null){	//skip columns with empty title
    		    	ColumnAttr columnAttr = attrMap.get(columnName);
		    		if(!MyUtils.isCellEmpty(cell)){
				    	//fillMinMax(row, cell, columnAttr);
		    			processConcret(row, cell, columnAttr);
		    		}
    			}
    		}            
        }
	}
	
	protected abstract void processConcret(Row row, Cell cell, ColumnAttr columnAttr);
	
}
