package net.ukr.sandrm.preProcessing;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import net.ukr.sandrm.preProcessing.model.ColumnAttr;
import net.ukr.sandrm.preProcessing.model.ColumnType;


public class CodingProcessor extends AbstractProcessor {

	@Override
	protected void processConcret(Row row, Cell cell, ColumnAttr columnAttr) {
		logger.debug("CodingProcessor processConcret(...) called");
		
		if(columnAttr.getColumnType() == ColumnType.TEXT){
			String textValue = cell.getStringCellValue() ;
			boolean loadingFactor = columnAttr.getCodingMap().size() <= ColumnAttr.FACTOR_CODING + 1;
			if(loadingFactor)
				columnAttr.getCodingMap().incValue(textValue);
		}
	}

}
