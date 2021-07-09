package com.dominio.api.controller.nasa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dominio.api.model.nasa.Nasa;
import com.dominio.api.service.nasa.NasaService;

@RestController
@RequestMapping(path = "/nasa")
public class NasaController {

	@Autowired
	private NasaService service;

	@GetMapping
	public ResponseEntity<Nasa> getImagesNasa() {
		return this.service.getNasaAPI();
	}
}
