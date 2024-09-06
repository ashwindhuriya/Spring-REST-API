package com.example.crudBackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crudBackend.models.Director;
import com.example.crudBackend.models.Movie;
import com.example.crudBackend.repositories.DirectorRepository;
import com.example.crudBackend.repositories.MovieRepository;

@Service
public class MovieService {
	@Autowired
	private MovieRepository movieRepository;
	
	public Movie addMovieDetails(Movie movie) {
		return movieRepository.save(movie);
	}
	
	public List<Movie> fetchAllMovies() {
		return movieRepository.findAll();
	}
	
	public Movie updateMovieData(int movieId, Movie updatedMovie) {
		Movie existingMovie = movieRepository.findById(movieId).get();
		
		if (updatedMovie.getMovieTitle() != null) {
			existingMovie.setMovieTitle(updatedMovie.getMovieTitle());
		}
		
		if(updatedMovie.getMovieReleaseYear() != 0)
		{
			existingMovie.setMovieReleaseYear(updatedMovie.getMovieReleaseYear());
		}
		
		movieRepository.save(existingMovie);
		return existingMovie;
	}
	
	public void deleteMovie(int movieId) {
		movieRepository.deleteById(movieId);
	}
}
