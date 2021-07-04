package com.dominio.api.model;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Files {

	@Id
	private Long id;
	private String name;
}
