package com.example.crudBackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crudBackend.models.Director;

public interface DirectorRepository extends JpaRepository<Director, Integer> {

}
