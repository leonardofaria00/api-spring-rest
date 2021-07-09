package com.dominio.api.service.nasa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.dominio.api.exception.NegocioException;
import com.dominio.api.model.nasa.Nasa;
import com.dominio.api.repository.nasa.NasaReposiroty;

@Service
public class NasaService {
	@Autowired
	private NasaReposiroty repository;

	public ResponseEntity<Nasa> getNasaAPI() {

		ResponseEntity<Nasa> response = this.getNasaService();

		if (response.getStatusCode() != HttpStatus.OK)
			return ResponseEntity.notFound().build();

		this.repository.save(response.getBody());
		return ResponseEntity.ok().body(response.getBody());
	}

	private ResponseEntity<Nasa> getNasaService() {
		UriComponents uri = UriComponentsBuilder.newInstance()
				.scheme("https")
				.host("api.nasa.gov")
				.path("/planetary")
				.path("/apod")
				.queryParam("api_key", "GLHeJvAEfvSxxV4ZE8l6qoe8y9SrdGrRlpIsGWQC")
				.build();

		try {
			RestTemplate template = new RestTemplate();
			
			ResponseEntity<Nasa> response = template.getForEntity(uri.toString(), Nasa.class);
			return response;
		} catch (Exception error) {
			System.out.println(error.getMessage());
			throw new NegocioException("Error ao chamar API Externa", error);
		}
	}

}
