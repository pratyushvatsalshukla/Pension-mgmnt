package com.pensionerDisbursementMicroservice.Model;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CustomErrorResponse {
	
	private LocalDateTime timestamp;
	private HttpStatus status;
	private String reason;
	private String message;

}
