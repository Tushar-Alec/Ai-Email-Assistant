package com.email.writer.app;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/api/email")
public class EmailGeneratorController {

	public ResponseEntity<String> generateEmail(@RequestBody EmailRequest emailRequest){
		return ResponseEntity.ok("jxx");
	}
}
