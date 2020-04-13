package org.karolinawidz.reader;

import org.apache.commons.io.input.BOMInputStream;
import org.karolinawidz.model.Employees;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public interface DataReader {
	Logger LOGGER = Logger.getLogger(DataReader.class.getName());

	default Reader readData(String fileName){
		Reader reader = null;

		try {
			URL res = getClass().getClassLoader().getResource(fileName);
			reader = new InputStreamReader(new BOMInputStream(Objects.requireNonNull(res,"File not found").openStream()));

		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Cannot open file", e);
		}

		return reader;
	}

	Employees parseData(String fileName);
	default BigDecimal sumSalaries(Employees employees, String position) {
		BigDecimal salaries = BigDecimal.ZERO;
		if(employees!=null) {
			return employees.getEmployees().stream()
					.filter(employee -> employee.getJob().equals(position))
					.map(employee -> salaries.add(employee.getSalary()))
					.reduce(salaries, BigDecimal::add);
		}
		else return salaries;
	}
}
