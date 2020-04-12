package org.karolinawidz.reader.jsonReader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.karolinawidz.model.Employees;
import static org.junit.jupiter.api.Assertions.*;

class JsonDataReaderTest {

	private JsonDataReader jsonDataReader;

	@BeforeEach
	void setUp() {
		this.jsonDataReader = new JsonDataReader();
	}

	@Test
	void shouldCreateEmployeeList(){
		Employees employees = jsonDataReader.parseData("employees.json");
		assertAll(
				()->assertFalse(employees.getEmployees().isEmpty()),
				()->assertEquals(1,employees.getEmployees().get(0).getId()),
				()->assertEquals(5,employees.getEmployees().size()),
				()->assertEquals("Spear",employees.getEmployees().get(4).getSurname())
		);
	}

}
