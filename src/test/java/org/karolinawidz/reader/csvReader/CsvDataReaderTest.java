package org.karolinawidz.reader.csvReader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.karolinawidz.model.Employees;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class CsvDataReaderTest {


	private CsvDataReader csvDataReader;

	@BeforeEach
	void setUp() {
		this.csvDataReader = new CsvDataReader();
	}


	@CsvFileSource(resources = "/employees.csv", numLinesToSkip = 1, delimiter = ';')
	@ParameterizedTest
	void shouldNotBeNull(Integer id, String name, String surname, String job, String salary ) {
		assertAll(
				()->assertNotEquals(0,id),
				()->assertNotNull(name),
				()->assertNotNull(surname),
				()->assertNotNull(job),
				()->assertNotEquals(0,job.length()),
				()->assertNotEquals(BigDecimal.ZERO,csvDataReader.createBigDecimal(salary)),
				()->assertNotEquals(0,salary.length())
		);
	}

	@Test
	void shouldReturnNewBigDecimal(){
		BigDecimal expected=new BigDecimal("2919.20");
		assertAll(
				()->assertEquals(expected,csvDataReader.createBigDecimal("2919.20")),
				()->assertEquals(expected,csvDataReader.createBigDecimal("2919,20")),
				()->assertEquals(BigDecimal.ZERO,csvDataReader.createBigDecimal(""))
		);
	}

	@Test
	void shouldConvertString(){
		assertAll(
				()->assertEquals("Mark",csvDataReader.recordParser("\"Mark\"")),
				()->assertEquals("Mark",csvDataReader.recordParser("Mark")),
				()->assertEquals("Mark",csvDataReader.recordParser("		Mark	"))
		);
	}

	@Test
	void shouldHaveRightNames(){
		assertAll(
				()->assertEquals("id",Headers.id.toString()),
				()->assertEquals("name",Headers.name.toString()),
				()->assertEquals("surname",Headers.surname.toString()),
				()->assertEquals("job",Headers.job.toString()),
				()->assertEquals("salary",Headers.salary.toString())
		);
	}

	@Test
	void shouldCreateEmployeesList(){
		Employees employees = csvDataReader.parseData("employees.csv");
		assertAll(
				()->assertFalse(employees.getEmployees().isEmpty()),
				()->assertEquals(1,employees.getEmployees().get(0).getId()),
				()->assertEquals(5,employees.getEmployees().size()),
				()->assertEquals("Spear",employees.getEmployees().get(4).getSurname())
		);

	}

}