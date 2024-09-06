package com.example.crudBackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crudBackend.models.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

}
