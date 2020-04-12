package org.karolinawidz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.karolinawidz.reader.csvReader.CsvDataReader;
import org.karolinawidz.reader.jsonReader.JsonDataReader;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SalaryCalculatorTest {

	private SalaryCalculator salaryCalculator;

	@BeforeEach
	void setUp() {
		this.salaryCalculator = new SalaryCalculator();
	}

	@Test
	void sumSalariesFromPosition() {
		assertAll(
				()->assertEquals(new BigDecimal("26920.90"),salaryCalculator.sumSalariesFromPosition("employees.json",new JsonDataReader(),"Janitor")),
				()->assertEquals(new BigDecimal("26920.90"),salaryCalculator.sumSalariesFromPosition("employees.csv",new CsvDataReader(),"Janitor"))
		);
	}
}