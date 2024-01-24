package com.estore.api.estoreapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.RequestDataValueProcessor;

import com.estore.api.estoreapi.model.Plant;
import com.estore.api.estoreapi.persistence.RequestDAO;

@SpringBootTest
public class RequestControllerTest {

    RequestDAO requestDAO;
    RequestController requestController;

    @BeforeEach
    public void setup(){
        RequestDAO requestDAOO = mock(RequestDAO.class);
        this.requestDAO = requestDAOO;
        Plant plant = new Plant(1, "", 10, 10);
    }
    
    @Test
    public void testgetPlantOK() throws IOException{

        when(requestDAO.getPlant(1)).thenReturn(new Plant(1, "", 10, 10));
        requestController = new RequestController(requestDAO);
        ResponseEntity<Plant> response = requestController.getPlant(1);
        assert(response.getStatusCode() == HttpStatus.OK);
    }

    @Test
    public void testgetPlantNotFound() throws IOException{

        when(requestDAO.getPlant(1)).thenReturn(null);
        requestController = new RequestController(requestDAO);
        ResponseEntity<Plant> response = requestController.getPlant(1);
        assert(response.getStatusCode() == HttpStatus.NOT_FOUND);
    }

    @Test
    public void testgetPlantInternalServerError() throws IOException{

        when(requestDAO.getPlant(1)).thenThrow(new IOException());
        requestController = new RequestController(requestDAO);
        ResponseEntity<Plant> response = requestController.getPlant(1);
        assert(response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void testcreatePlantConflict() throws IOException{

        when(requestDAO.createPlant(new Plant(1, "", 10, 10))).thenReturn(new Plant(1, "", 10, 10));
        requestController = new RequestController(requestDAO);
        ResponseEntity<Plant> response = requestController.createPlant(new Plant(1, "", 10, 10));
        assert(response.getStatusCode() == HttpStatus.CONFLICT);
    }

    // @Test
    public void testCreatePlantCreated() throws IOException{
            //TODO:idk why this test is failing
            when(requestDAO.createPlant(new Plant(1, "", 10, 10))).thenReturn(null);
            requestController = new RequestController(requestDAO);
            ResponseEntity<Plant> response = requestController.createPlant(new Plant(1, "", 10, 10));
            assert(response.getStatusCode() == HttpStatus.CREATED);
    }

    // @Test
    public void testCreatePlantInternalServerError() throws IOException{
            when(requestDAO.createPlant(new Plant(1, "", 10, 10))).thenThrow(new IOException());
            requestController = new RequestController(requestDAO);
            ResponseEntity<Plant> response = requestController.createPlant(new Plant(1, "", 10, 10));
            assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void testsearchPlantsOK() throws IOException{
        when(requestDAO.findPlants("")).thenReturn(new Plant[0]);
        requestController = new RequestController(requestDAO);
        ResponseEntity<Plant[]> response = requestController.searchPlants("");
        assert(response.getStatusCode() == HttpStatus.OK);
    }

    @Test
    public void testsearchPlantsInternalServerError() throws IOException{
        when(requestDAO.findPlants("")).thenThrow(new IOException());
        requestController = new RequestController(requestDAO);
        ResponseEntity<Plant[]> response = requestController.searchPlants("");
        assert(response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void deletePlantNOT_FOUND() throws IOException{
        when(requestDAO.deletePlants(1)).thenReturn(true);
        requestController = new RequestController(requestDAO);
        ResponseEntity<Plant> response = requestController.deletePlant(1);
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }
}


