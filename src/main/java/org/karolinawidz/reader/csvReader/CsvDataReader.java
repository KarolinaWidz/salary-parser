package org.karolinawidz.reader.csvReader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.BOMInputStream;
import org.karolinawidz.reader.DataReader;
import org.karolinawidz.model.Employee;
import org.karolinawidz.model.Employees;

import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CsvDataReader implements DataReader {

	private static final Logger LOGGER = Logger.getLogger(CsvDataReader.class.getName());

	@Override
	public Employees readData() {

		Employees employees = new Employees();
		List<Employee> employeesList = new ArrayList<>();


		try{

			URL res = getClass().getClassLoader().getResource("employees.csv");
			Reader reader = new InputStreamReader(new BOMInputStream(Objects.requireNonNull(res).openStream()), StandardCharsets.UTF_8);
			Iterable<CSVRecord> employeesData = CSVFormat.DEFAULT.withDelimiter(';').withFirstRecordAsHeader().parse(reader);

			for (CSVRecord record : employeesData) {
				Integer id = Integer.parseInt(record.get(Headers.id).trim());
				String name = recordParser(record, Headers.name);
				String surname = recordParser(record, Headers.surname);
				String job = recordParser(record, Headers.job);
				String salary = recordParser(record, Headers.salary);
				if (salary.contains(",")) salary = salary.replace(",", ".");
				employeesList.add(new Employee(id, name, surname, job, new BigDecimal(salary)));
			}

		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Cannot open file", e);
		}
		employees.setEmployees(employeesList);
		return employees;
	}


	private String recordParser(CSVRecord record, Headers header){
		return record.get(header).trim().replace("\"","");
	}

}
