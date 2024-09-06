package com.example.crudBackend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.*;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.Model;

import com.example.crudBackend.controllers.DirectorController;
import com.example.crudBackend.models.Director;
import com.example.crudBackend.repositories.DirectorRepository;
import com.example.crudBackend.services.DirectorService;

@SpringBootTest
@ContextConfiguration(classes = {CrudBackendApplication.class})
@ExtendWith(MockitoExtension.class)
class CrudBackendApplicationTests {
	
//	@Mock
//	private DirectorRepository directorRepository;
	
	@Mock
	private ModelMapper modelMapper;
	
	@Mock
	private DirectorService directorService;
	
	@InjectMocks
	private DirectorController directorController;
	
	private List<Director> mockDirectors;
	private Director mockDirector;
	private Director existingDirector;
	private Director updatedDirector;

	@Test
	void contextLoads() {
	}
	
	@BeforeEach
    public void setUp() {
        // Create mock data for directors
        mockDirectors = Arrays.asList(
            new Director(1, "Christopher Nolan", "British-American", null),
            new Director(2, "Quentin Tarantino", "American", null)
        );
        mockDirector = new Director(1, "Steven Spielberg", "American", null);
        existingDirector = new Director(1, "Steven Spielberg", "American", null);
        updatedDirector = new Director(1, "Christopher Nolan", "British-American", null);
    }

    @Test
    public void testGetAllDirectors() {
        // Mock the service layer to return mock data
        when(directorService.fetchAllDirectors()).thenReturn(mockDirectors);

        // Call the controller method directly
        ResponseEntity<List<Director>> response = directorController.getAllDirectors();

        // Verify the response status and body
        assertNotNull(response); // Check if the response is not null
        assertEquals(HttpStatus.OK, response.getStatusCode()); // Verify status code is 200 OK
        assertNotNull(response.getBody()); // Check if the body is not null
        assertEquals(2, response.getBody().size()); // Verify that 2 directors are returned
        assertEquals("Christopher Nolan", response.getBody().get(0).getDirectorName()); // Check the first director's name
        assertEquals("Quentin Tarantino", response.getBody().get(1).getDirectorName()); // Check the second director's name
    }
    
    @Test
    public void testAddDirectorDetails() {
        // Mock the service method to return the saved Director
        when(directorService.addDirectorDetails(mockDirector)).thenReturn(mockDirector);

        // Call the controller method
        ResponseEntity<Director> response = directorController.addDirectorDetails(mockDirector);

        // Verify the response status and body
        assertNotNull(response); // Check that response is not null
        assertEquals(HttpStatus.CREATED, response.getStatusCode()); // Verify status code is 201 Created
        assertNotNull(response.getBody()); // Check that response body is not null
        assertEquals(mockDirector.getDirectorName(), response.getBody().getDirectorName()); // Check director name
        assertEquals(mockDirector.getDirectorNationality(), response.getBody().getDirectorNationality()); // Check nationality
    }
    
    @Test
    public void testUpdateDirectorDetails() {
        // Mock the service method to return the updated director
        when(directorService.updateDirectorData(1, updatedDirector)).thenReturn(updatedDirector);

        // Call the controller method
        ResponseEntity<Director> response = directorController.updateDirectorDetails(1, updatedDirector);

        // Verify the response status and body
        assertNotNull(response); // Check that response is not null
        assertEquals(HttpStatus.OK, response.getStatusCode()); // Verify status code is 200 OK
        assertNotNull(response.getBody()); // Check that response body is not null

        // Verify the updated director details
        assertEquals(updatedDirector.getDirectorName(), response.getBody().getDirectorName()); // Check director name
        assertEquals(updatedDirector.getDirectorNationality(), response.getBody().getDirectorNationality()); // Check nationality
    }
    
    @Test
    public void testDeleteDirectorDetails() {  	
    	int directorId = 1;

        // Mock the service method to do nothing when called
        doNothing().when(directorService).deleteDirector(directorId);

        // Call the controller method
        ResponseEntity<String> response = directorController.deleteCustomer(directorId);

        // Verify that the service method was called with the correct directorId
        verify(directorService).deleteDirector(directorId);

        // Check that the response is not null
        assertNotNull(response);

        // Verify the response status is 200 OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verify the response body contains the correct message
        assertEquals("Director Deleted!", response.getBody());
    }
}
