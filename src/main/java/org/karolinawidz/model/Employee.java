package org.karolinawidz.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.karolinawidz.reader.jsonReader.DifferentSignDeserializer;
import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@ToString
public class Employee {

	private Integer id;
	private String name;
	private String surname;
	private String job;
	@JsonDeserialize(using = DifferentSignDeserializer.class)
	private BigDecimal salary;

	public Employee() {
	}

	public Employee(Integer id, String name, String surname, String job, BigDecimal salary) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.job = job;
		this.salary = salary;
	}
}