package org.karolinawidz.reader;

import org.karolinawidz.model.Employees;

import java.math.BigDecimal;

public interface DataReader {
	Employees readData();

	default BigDecimal sumSalaries(Employees employees, String position) {
		BigDecimal salaries = BigDecimal.ZERO;
		return employees.getEmployees().stream()
				.filter(employee -> employee.getJob().equals(position))
				.map(employee -> salaries.add(employee.getSalary()))
				.reduce(salaries,BigDecimal::add);
	}
}
