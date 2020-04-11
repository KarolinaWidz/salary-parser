package org.karolinawidz.reader.csvReader;

import org.apache.commons.csv.CSVRecord;

import java.math.BigDecimal;

public enum Headers {
	//name,surname,job,salary;

	id {
		@Override
		public Integer parse(CSVRecord record){
			return Integer.parseInt(record.get(this).trim());
		}
		},
	name{
		@Override
		public String parse(CSVRecord record){
			return recordParser(record, this);
		}
	},
	surname{
		@Override
		public String parse(CSVRecord record){
			return recordParser(record, this);
		}
	},
	job{
		@Override
		public String parse(CSVRecord record){
			return recordParser(record, this);
		}
	},
	salary{
		@Override
		public BigDecimal parse(CSVRecord record){
			String salary = recordParser(record, Headers.salary);
			if (salary.contains(",")) salary = salary.replace(",", ".");
			return new BigDecimal(salary);
		}
	};
	public abstract <T> T parse(CSVRecord record);
	private static String recordParser(CSVRecord record, Headers header){
		return record.get(header).trim().replace("\"","");
	}
};


