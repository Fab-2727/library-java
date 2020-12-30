package library.controller;

import org.apache.coyote.http11.Http11AprProtocol;
import org.json.HTTP;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import library.service.LibraryServiceImpl;

@RestController
@RequestMapping("/library")
public class LibraryController {
	
	@Autowired
	private LibraryServiceImpl libraryService;
	
	@GetMapping(path = "/index")
	public String index() {
		return "Welcome, this is the index page";
	}
	
	@PostMapping(path = "/add-publisher", consumes = "application/json", produces = "application/json")
	public String addNewPublisher(@RequestParam JSONObject jsonMessage) {
		return jsonMessage.toString();
	}	
		
}
