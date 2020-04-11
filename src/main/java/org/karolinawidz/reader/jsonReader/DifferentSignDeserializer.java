package org.karolinawidz.reader.jsonReader;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.math.BigDecimal;

public class DifferentSignDeserializer extends JsonDeserializer {
	@Override
	public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
		String value = jsonParser.getText();
		return value.contains(",")? new BigDecimal(value.replace(",",".")): new BigDecimal(value);

	}
}
