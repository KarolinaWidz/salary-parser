package org.karolinawidz.reader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.karolinawidz.model.Employee;
import org.karolinawidz.model.Employees;
import org.karolinawidz.reader.jsonReader.JsonDataReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataReaderTest {

	private DataReader dataReader;
	private Employees employees;
	private Employees nullEmployees;

	@BeforeEach
	void setUp() {
		this.dataReader = new JsonDataReader();
		List<Employee> employeeList = new ArrayList<>();
		this.employees = new Employees();
		this.nullEmployees = null;
		employeeList.add(new Employee(1,"John","Blue","Teacher",new BigDecimal("1200.32")));
		employeeList.add(new Employee(2,"Anna","Blue","Teacher",new BigDecimal("1000.00")));
		employeeList.add(new Employee(3,"Mark","Green","Janitor",new BigDecimal("1200.32")));
		employees.setEmployees(employeeList);
	}

	@Test
	void readData() {
		assertNotNull(dataReader.readData("employees.json"));
	}

	@Test
	void sumSalaries() {
		assertAll(
				()->assertEquals(new BigDecimal("2200.32"),dataReader.sumSalaries(employees,"Teacher")),
				()->assertEquals(BigDecimal.ZERO,dataReader.sumSalaries(nullEmployees,"Teacher"))
		);

	}
}