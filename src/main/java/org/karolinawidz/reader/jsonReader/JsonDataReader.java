package org.karolinawidz.reader.jsonReader;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.input.BOMInputStream;
import org.karolinawidz.reader.DataReader;
import org.karolinawidz.model.Employees;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JsonDataReader implements DataReader {

	private static final Logger LOGGER = Logger.getLogger(JsonDataReader.class.getName());

	@Override
	public Employees readData() {

		ObjectMapper objectMapper = new ObjectMapper();
		Employees employees = new Employees();
		try {
			URL res = getClass().getClassLoader().getResource("employees.json");
			Reader reader = new InputStreamReader(new BOMInputStream(Objects.requireNonNull(res).openStream()), StandardCharsets.UTF_8);
			employees = objectMapper.readValue(reader, Employees.class);
		} catch (JsonParseException e) {
			LOGGER.log(Level.WARNING,"Parse exception",e);
		} catch (JsonMappingException e) {
			LOGGER.log(Level.SEVERE, "Incorrect formatting of json file or missing data", e);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Cannot open file", e);
		}
		return employees;
	}
}
