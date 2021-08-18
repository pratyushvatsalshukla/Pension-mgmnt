package com.pensionManagementSystem.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@Setter
public class PensionerInput {
	private String name;
	
	private @DateTimeFormat(pattern="yyyy-MM-dd") Date dateOfBirth;
	private String pan;
	private long aadharNumber;
	private String pensionType;

}
