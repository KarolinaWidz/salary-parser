package org.karolinawidz.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.karolinawidz.reader.jsonReader.DifferentSignDeserializer;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Employee {

	private Integer id;
	private String name;
	private String surname;
	private String job;
	@JsonDeserialize(using = DifferentSignDeserializer.class)
	private BigDecimal salary;

	public Employee(Integer id, String name, String surname, String job, BigDecimal salary) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.job = job;
		this.salary = salary;
	}
}