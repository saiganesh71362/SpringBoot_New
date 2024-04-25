package com.user.excepionhandle;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError
{
	private String message;
	private String errorDetails;
	private Date timeStamp;
}
