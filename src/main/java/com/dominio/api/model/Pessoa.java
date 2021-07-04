package com.dominio.api.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "pessoa")
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {

	@Id
	private String id;
	private String nome;

	@JsonProperty(access = Access.READ_ONLY)
	private LocalDateTime cadastro;

}
