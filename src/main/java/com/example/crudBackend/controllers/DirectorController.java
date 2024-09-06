package com.example.crudBackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.crudBackend.models.Director;
import com.example.crudBackend.services.DirectorService;

@RestController
@RequestMapping("/director")
@CrossOrigin(origins = "http://localhost:4200")
public class DirectorController {
	
	@Autowired
	private DirectorService directorService;
	
	@PostMapping(path = "addDirector")
	public ResponseEntity<Director> addDirectorDetails(@RequestBody Director director) {
		return new ResponseEntity<Director>(directorService.addDirectorDetails(director), HttpStatus.CREATED);
	}
	
	@GetMapping(path = "getAllDirectors")
	public ResponseEntity<List<Director>> getAllDirectors() {
		return new ResponseEntity<List<Director>> (directorService.fetchAllDirectors(), HttpStatus.OK);
	}
	
	@PutMapping("/updateDirectorDetails/{directorId}")
    public ResponseEntity<Director> updateDirectorDetails(@PathVariable int directorId, @RequestBody Director director) {
        Director updatedDirector = directorService.updateDirectorData(directorId, director);
        return new ResponseEntity<Director>(updatedDirector, HttpStatus.OK);
    }
	
	@DeleteMapping(path ="/deleteDirector/{directorId}")
	public ResponseEntity<String> deleteCustomer(@PathVariable int directorId)
	{
		directorService.deleteDirector(directorId);
		return new ResponseEntity<String>("Director Deleted!", HttpStatus.OK);
	}
}
