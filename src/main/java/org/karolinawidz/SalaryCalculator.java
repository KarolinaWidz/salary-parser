package org.karolinawidz;

import org.karolinawidz.reader.DataReader;

import java.math.BigDecimal;

class SalaryCalculator {
	BigDecimal sumSalariesFromPosition(String fileName, DataReader dataReader, String position){
		return dataReader.sumSalaries(dataReader.parseData(fileName),position);
	}
}
