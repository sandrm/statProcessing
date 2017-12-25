package net.ukr.sandrm.preProcessing.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;

public class CodingMap extends LinkedHashMap<String,Integer>{
//	private Map<String, Integer> codingMap = new TreeMap<String,Integer>();
//	
//	public Map<String, Integer> getCodingMap() {
//		return codingMap;
//	}
//
//	public void setCodingMap(Map<String, Integer> codingMap) {
//		this.codingMap = codingMap;
//	}
	
	private static final long serialVersionUID = 6808132289433279738L;
	
	public void incValue(String textValue){
		if(this.containsKey(textValue)){
			int counts = (int)this.get(textValue) + 1;
			this.put(textValue,  new Integer(counts));
		}else{
			this.put(textValue, 1);
		}
	}
	
	public int getCode(String textValue){
		//int id = (new ArrayList<String>(codingMap.values()).indexOf(codingMap.get("team2")) + 1;
		//TreeSet<String> keys = (TreeSet)codingMap.keySet();
		//return keys.headSet(textValue).size();
		Set<String> set = this.keySet();
		return (new ArrayList<String>(set)).indexOf(textValue);
	}
	
}
