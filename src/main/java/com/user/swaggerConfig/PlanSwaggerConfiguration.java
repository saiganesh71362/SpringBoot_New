package com.user.swaggerConfig;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlanSwaggerConfiguration 
{
	@Bean
	public GroupedOpenApi controllerApi()
	{
	        return GroupedOpenApi.builder()
	                .group("UserManagement")
	                .packagesToScan("com.user.controller") // Specify the package to scan
	                .build();
	 }
}

//"planName": "SAIGANESH",
//"planStartDate": "2024-03-10",
//"planEndDate": "2024-03-10",
//"planCatagiryId": 0,
//"activeSwitch": "string",
//"cerateDate": "2024-03-10",
//"updateDate": "2024-03-10",
//"createdBy": "SAIGNAESH",
//"updateBy": "HARI"
