package org.karolinawidz.reader.jsonReader;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.karolinawidz.model.Employees;
import org.karolinawidz.reader.DataReader;

import java.io.IOException;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JsonDataReader implements DataReader {

	private static final Logger LOGGER = Logger.getLogger(JsonDataReader.class.getName());

	@Override
	public Employees parseData(String fileName) {

		ObjectMapper objectMapper = new ObjectMapper();
		Employees employees = new Employees();
		Reader reader = readData(fileName);
		try {
			if(reader!=null) employees = objectMapper.readValue(reader, Employees.class);
		} catch (JsonParseException e) {
			LOGGER.log(Level.WARNING,"Parse exception",e);
		} catch (JsonMappingException e) {
			LOGGER.log(Level.SEVERE, "Incorrect formatting of json file", e);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Cannot open file", e);
		}
		return employees;
	}
}
