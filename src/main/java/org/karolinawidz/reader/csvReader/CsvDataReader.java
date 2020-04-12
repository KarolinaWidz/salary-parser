package org.karolinawidz.reader.csvReader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.karolinawidz.model.Employee;
import org.karolinawidz.model.Employees;
import org.karolinawidz.reader.DataReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CsvDataReader implements DataReader {

	private static final Logger LOGGER = Logger.getLogger(CsvDataReader.class.getName());

	@Override
	public Employees parseData(String fileName) {

		Employees employees = new Employees();
		List<Employee> employeesList = new ArrayList<>();
		Reader reader = readData(fileName);

		try {
			Iterable<CSVRecord> employeesData = CSVFormat.DEFAULT.withDelimiter(';').withFirstRecordAsHeader().parse(reader);
			for (CSVRecord record : employeesData) {
				Integer id = Integer.parseInt(record.get(Headers.id).trim());
				String name = recordParser(record.get(Headers.name));
				String surname = recordParser(record.get(Headers.surname));
				String job = recordParser(record.get(Headers.job));
				String salary = recordParser(record.get(Headers.salary));
				employeesList.add(new Employee(id, name, surname, job, createBigDecimal(salary)));
			}
		} catch (IllegalArgumentException e) {
			LOGGER.log(Level.SEVERE, "Reader is null", e);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Cannot open file", e);
		}
		employees.setEmployees(employeesList);
		return employees;
	}

	String recordParser(String text){
		return text.trim().replace("\"","");
	}
	BigDecimal createBigDecimal(String text){
		if(text.isEmpty()) return BigDecimal.ZERO;
		return text.contains(",")? new BigDecimal(text.replace(",",".")) : new BigDecimal(text);
	}
}
