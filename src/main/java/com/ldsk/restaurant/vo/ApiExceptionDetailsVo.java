package com.ldsk.restaurant.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ApiExceptionDetailsVo {
	
	private final String title;
	private final int httpStatus;
	private final String message;
	private final String details;
	private final LocalDateTime timestamp;

}
