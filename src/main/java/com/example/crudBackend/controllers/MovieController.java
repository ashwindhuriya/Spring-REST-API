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
import org.springframework.web.bind.annotation.RestController;
import com.example.crudBackend.models.Movie;

import com.example.crudBackend.services.MovieService;

@RestController
@RequestMapping("/movie")
@CrossOrigin(origins = "http://localhost:4200")
public class MovieController {
	@Autowired
	private MovieService movieService;
	
	@PostMapping(path = "/addMovie")
	public ResponseEntity<Movie> addMovieDetails(@RequestBody Movie movie) {
		return new ResponseEntity<Movie>(movieService.addMovieDetails(movie), HttpStatus.CREATED);
	}
	
	@GetMapping(path = "/getAllMovies")
	public ResponseEntity<List<Movie>> getAllMovies() {
		return new ResponseEntity<List<Movie>> (movieService.fetchAllMovies(), HttpStatus.OK);
	}
	
	@PutMapping(path = "/updateMovieDetails/{movieId}")		
    public ResponseEntity<Movie> updateMovieDetails(@PathVariable int movieId, @RequestBody Movie movie) {
		System.out.println("IDDDDD" + movieId);
        Movie updatedMovieData = movieService.updateMovieData(movieId, movie);
        return new ResponseEntity<Movie>(updatedMovieData, HttpStatus.OK);
    }
	
	@DeleteMapping(path ="/deleteMovie/{movieId}")
	public ResponseEntity<String> deleteCustomer(@PathVariable int movieId)
	{
		movieService.deleteMovie(movieId);
		return new ResponseEntity<String>("Movie Deleted!", HttpStatus.OK);
	}
}
