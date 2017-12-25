package net.ukr.sandrm.preProcessing;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import net.ukr.sandrm.preProcessing.model.ColumnAttr;
import net.ukr.sandrm.preProcessing.model.ColumnType;
import net.ukr.sandrm.preProcessing.utils.MyUtils;


public class WrongProcessor extends AbstractProcessor {

	@Override
	protected void processConcret(Row row, Cell cell, ColumnAttr columnAttr) {
		logger.info("WrongProcessor processConcret(...) called");
		
		if(columnAttr.getColumnType() == ColumnType.TEXT){
			if(columnAttr.getCodingMap().size() > 1){
				Map<ColumnType, Integer> sortedTypeMap = MyUtils.sortByValues((HashMap<ColumnType, Integer>)columnAttr.getTypeMap());
				Set<ColumnType> columnTypeSet = sortedTypeMap.keySet();
				Iterator iterator = columnTypeSet.iterator();
/*				
				while(iterator.hasNext()) {
			    	ColumnType next = (ColumnType)iterator.next();
			    	if(particularlyFilled(columnTypeSet, next))
			    		next = (ColumnType)iterator.next();
			    	
			    	columnType = next;
			    	break;
			    }
			}
			String textValue = cell.getStringCellValue() ;
			//boolean loadingFactor = columnAttr.getCodingMap().size() <= ColumnAttr.FACTOR_CODING + 1;
			//if(loadingFactor)
			//	columnAttr.getCodingMap().incValue(textValue);
*/			
		}

		}
	}
}
