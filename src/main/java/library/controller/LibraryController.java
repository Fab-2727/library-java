package library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
		
}
