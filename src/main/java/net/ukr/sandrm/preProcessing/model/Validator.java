package net.ukr.sandrm.preProcessing.model;

public interface Validator<Cell> {
	boolean validate(Cell cell);
}
