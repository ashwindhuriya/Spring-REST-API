package com.example.crudBackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crudBackend.models.Director;
import com.example.crudBackend.repositories.DirectorRepository;

@Service
public class DirectorService {
	@Autowired
	private DirectorRepository directorRepository;
	
	public Director addDirectorDetails(Director director) {
		return directorRepository.save(director);
	}
	
	public List<Director> fetchAllDirectors() {
		return directorRepository.findAll();
	}
	
	public Director updateDirectorData(int directorId, Director updatedDirector) {
		Director existingDirector = directorRepository.findById(directorId).get();
		
		if (updatedDirector.getDirectorName() != null) {
			existingDirector.setDirectorName(updatedDirector.getDirectorName());
		}
		
		if(updatedDirector.getDirectorNationality() != null)
		{
			existingDirector.setDirectorNationality(updatedDirector.getDirectorNationality());
		}
		
		directorRepository.save(existingDirector);
		return existingDirector;
	}
	
	public void deleteDirector(int directorId) {
		directorRepository.deleteById(directorId);
	}
}
