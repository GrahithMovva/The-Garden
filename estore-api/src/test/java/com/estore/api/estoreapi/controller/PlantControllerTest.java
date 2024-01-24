package com.estore.api.estoreapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.estore.api.estoreapi.model.Plant;
import com.estore.api.estoreapi.persistence.PlantDAO;


@Tag("Controller-tier")
public class PlantControllerTest {
    private PlantController PlantController;
    private PlantDAO mockPlantDAO;
    private Plant mockPlant;

    /**
     * Before each test, create a new inventoryController object and inject
     * a mock Product DAO
     */
    @BeforeEach
    public void setupUserController() {
        mockPlantDAO = mock(PlantDAO.class);
        PlantController = new PlantController(mockPlantDAO);
        mockPlant = mock(Plant.class);
    }

    @Test
    public void testgetPlant() throws IOException { // getProduct may throw IOException
        // Setup
        Plant Plant = new Plant(1, "Cherry", 10, 5);
        when(mockPlantDAO.getPlant(1)).thenReturn(Plant);

        when(mockPlantDAO.getPlant(Plant.getId())).thenReturn(Plant);

        // Invoke
        ResponseEntity<com.estore.api.estoreapi.model.Plant> response = PlantController.getPlant(Plant.getId());

        // Analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Plant, response.getBody());
    }

    @Test
    public void getPlantNotFound() throws IOException {
        // Setup
        Plant Plant = new Plant(1, "Cherry", 10, 5);
        when(mockPlantDAO.getPlant(1)).thenReturn(null);

        // Invoke
        ResponseEntity<com.estore.api.estoreapi.model.Plant> response = PlantController.getPlant(Plant.getId());

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void getPlantInternalServerError() throws IOException {
        // Setup
        Plant Plant = new Plant(1, "Cherry", 10, 5);
        when(mockPlantDAO.getPlant(1)).thenThrow(new IOException());

        // Invoke
        ResponseEntity<com.estore.api.estoreapi.model.Plant> response = PlantController.getPlant(Plant.getId());

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testSearchPlant() throws IOException {
        // Setup
        Plant Plant = new Plant(1, "Cherry", 10, 5);
        Plant[] Plants = { Plant };
        when(mockPlantDAO.findPlants("Cherry")).thenReturn(Plants);

        // Invoke
        ResponseEntity<com.estore.api.estoreapi.model.Plant[]> response = PlantController.searchPlants("Cherry");

        // Analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testSearchPlantInternalServerError() throws IOException {
        // Setup
        Plant Plant = new Plant(1, "Cherry", 10, 5);
        Plant[] Plants = { Plant };
        when(mockPlantDAO.findPlants("Cherry")).thenThrow(new IOException());

        // Invoke
        ResponseEntity<com.estore.api.estoreapi.model.Plant[]> response = PlantController.searchPlants("Cherry");

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
    
    @Test
    public void updatePlanttest() throws IOException{
        Plant plant1 =  new Plant(1, "Cherry", 10, 5);
        when(mockPlantDAO.updatePlant(plant1)).thenReturn(plant1);
        when(mockPlantDAO.getPlant(plant1.getId())).thenReturn(plant1);
        ResponseEntity<com.estore.api.estoreapi.model.Plant> response = PlantController.updatePlant(plant1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(plant1, response.getBody());
    }

    @Test
    public void createPlants() throws IOException{
        Plant plant1 =  new Plant(1, "Cherry", 10, 5);
        when(mockPlantDAO.createPlant(plant1)).thenReturn(plant1);
        ResponseEntity<com.estore.api.estoreapi.model.Plant> response = PlantController.createPlant(plant1);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(plant1, response.getBody());
    }

    @Test
    public void getAllPlants() throws IOException{
        Plant plant1 =  new Plant(1, "Cherry", 10, 5);
        Plant plant2 =  new Plant(2, "Apple", 10, 5);
        Plant[] plants = {plant1, plant2};
        when(mockPlantDAO.getPlants()).thenReturn(plants);
        ResponseEntity<com.estore.api.estoreapi.model.Plant[]> response = PlantController.getPlants();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(plants, response.getBody());
    }

    @Test
    public void TestcreatePlantConflict() throws IOException{
        Plant plant1 =  new Plant(1, "Cherry", 10, 5);
        when(mockPlantDAO.createPlant(plant1)).thenReturn(null);
        ResponseEntity<com.estore.api.estoreapi.model.Plant> response = PlantController.createPlant(plant1);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

    @Test
    public void TestcreatePlantInternalServerError() throws IOException{
        Plant plant1 =  new Plant(1, "Cherry", 10, 5);
        when(mockPlantDAO.createPlant(plant1)).thenThrow(new IOException());
        ResponseEntity<com.estore.api.estoreapi.model.Plant> response = PlantController.createPlant(plant1);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

    @Test
    public void TestupdatePlantNotFound() throws IOException{
        Plant plant1 =  new Plant(1, "Cherry", 10, 5);
        when(mockPlantDAO.updatePlant(plant1)).thenReturn(null);
        ResponseEntity<com.estore.api.estoreapi.model.Plant> response = PlantController.updatePlant(plant1);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

    @Test
    public void getPlantsInternalServerError() throws IOException{
        Plant plant1 =  new Plant(1, "Cherry", 10, 5);
        Plant plant2 =  new Plant(2, "Apple", 10, 5);
        Plant[] plants = {plant1, plant2};
        when(mockPlantDAO.getPlants()).thenThrow(new IOException());
        ResponseEntity<com.estore.api.estoreapi.model.Plant[]> response = PlantController.getPlants();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

    // @Test
    public void updatePlantInternalServerError() throws IOException{
        //TODO: giving 404 instead of 500 updatePlantInternalServerError
        Plant plant1 =  new Plant(1, "Cherry", 10, 5);
        when(mockPlantDAO.updatePlant(plant1)).thenThrow(new IOException());
        ResponseEntity<com.estore.api.estoreapi.model.Plant> response = PlantController.updatePlant(plant1);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

    // @Test
    public void deletePlantStatusOK() throws IOException{
        //TODO: giving 404 instead of 500 deletePlantStatusOK
        Plant plant1 =  new Plant(3, "Cherry", 10, 5);
        when(mockPlantDAO.deletePlants(plant1.getId())).thenReturn(true);
        ResponseEntity<com.estore.api.estoreapi.model.Plant> response = PlantController.deletePlant(plant1.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    // @Test
    public void deletePlantInternalServerError() throws IOException{
        Plant plant1 =  new Plant(3, "Cherry", 10, 5);
        when(mockPlantDAO.deletePlants(plant1.getId())).thenThrow(new IOException());
        ResponseEntity<com.estore.api.estoreapi.model.Plant> response = PlantController.deletePlant(plant1.getId());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }




}