package com.dominio.api.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dominio.api.model.Files;
import com.dominio.api.repository.FileRepository;

@RestController
@RequestMapping(path = "/file")
public class FileController {

	@Autowired
	private FileRepository fileRepository;

	@GetMapping
	public ResponseEntity<Iterable<Files>> getAllUsers() {
		return ResponseEntity.ok(fileRepository.findAll());
	}

	@PostMapping
	public ResponseEntity<Object> uploadFiles(@RequestParam("file") MultipartFile file) {
		try {
			ArrayList<String> list = new ArrayList<String>();
			list.add("image/jpeg");
			list.add("image/png");

			if (file.getSize() > (1024 * 1024 * 5))
				return ResponseEntity.ok("Size > 25Mg");

			if (file.isEmpty())
				return ResponseEntity.badRequest().body("File is not present");

			if (!list.contains(file.getContentType()))
				return ResponseEntity.badRequest().body("Format of file invalid");

			// Save filename on database.
			Files fileEntity = new Files();
			fileEntity.setName(file.getOriginalFilename());
			fileRepository.save(fileEntity);

			// Save file on directory
			File convert = new File("C:\\desenvolvimento\\workspace\\api-spring-rest\\upload-files\\"
					+ OffsetDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHms")) + "-"
					+ file.getOriginalFilename());

			convert.createNewFile();

			FileOutputStream stream = new FileOutputStream(convert);
			stream.write(file.getBytes());
			stream.close();
			return ResponseEntity.ok("Success, created at: " + convert.getAbsolutePath());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.ok("Error to insert file: " + file.getOriginalFilename());
		}

	}

}