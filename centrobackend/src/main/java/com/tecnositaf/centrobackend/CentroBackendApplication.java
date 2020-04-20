package com.tecnositaf.centrobackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.tecnositaf.centrobackend.mapper")
public class CentroBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(CentroBackendApplication.class, args);
	}
	
}
