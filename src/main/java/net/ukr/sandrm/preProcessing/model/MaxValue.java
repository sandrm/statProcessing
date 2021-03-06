package net.ukr.sandrm.preProcessing.model;

public class MaxValue {
	int rowNum;
	Double value = null;
	
	public MaxValue(int rowNum, Double value) {
		super();
		this.rowNum = rowNum;
		this.value = value;
	}
	
	public int getRowNum() {
		return rowNum;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	
	
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	
	
	@Override
	public String toString() {
		return "MaxValue [rowNum=" + rowNum + ", value=" + value + "]";
	}
}
