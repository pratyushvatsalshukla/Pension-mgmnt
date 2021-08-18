package com.pensionManagementSystem.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ProcessPensionInput {

	private Long aadharNumber;
	private Double pensionAmount;
	private double serviceCharge;

}
