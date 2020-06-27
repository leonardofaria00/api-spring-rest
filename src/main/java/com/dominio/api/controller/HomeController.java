package com.dominio.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
public class HomeController {

	private static final String template = "Hello, %s!";

	@GetMapping
	public ResponseEntity<String> index(@RequestParam(value = "name", defaultValue = "World") String name) {
		return ResponseEntity.ok(String.format(template, name));
	}
}
