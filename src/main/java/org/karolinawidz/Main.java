package org.karolinawidz;

import org.karolinawidz.reader.csvReader.CsvDataReader;
import org.karolinawidz.reader.jsonReader.JsonDataReader;

public class Main {

	public static void main(String [] args) {

		SalaryCalculator salaryCalculator = new SalaryCalculator();
		System.out.println("From JSON: "+salaryCalculator.sumSalariesFromPosition("employees.csv", new CsvDataReader(),"Teacher"));
		System.out.println("From CSV: "+salaryCalculator.sumSalariesFromPosition("employees.json", new JsonDataReader(), "Teacher"));

	}
}
