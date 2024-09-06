package com.example.crudBackend.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "director_details")
public class Director {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int directorId;
	private String directorName;
	private String directorNationality;
	
	@OneToMany(mappedBy = "director", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Movie> movies;
}
