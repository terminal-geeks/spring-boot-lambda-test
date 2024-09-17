package com.geeks.terminal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/", produces = "application/json")
public class TestController {
	
	@GetMapping("/test")
	public ResponseEntity<String> testController(){
		return new ResponseEntity<String>("success:::::::",HttpStatus.OK);
	}

}
