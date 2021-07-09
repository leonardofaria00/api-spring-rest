package com.dominio.api.model.nasa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "Nasa")
public class Nasa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String url;
	private String hdurl;
	private String media_type;
	private String copyright;
	private String date;
	
	@Column(length = 3000)
	private String explanation;
	private String service_version;
}
