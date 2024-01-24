package com.estore.api.estoreapi.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estore.api.estoreapi.model.Plant;
import com.estore.api.estoreapi.persistence.PlantDAO;

@RestController
@RequestMapping("plants")
public class PlantController {
    private static final Logger LOG = Logger.getLogger(PlantController.class.getName());
    private PlantDAO plantDAO;

    public PlantController(PlantDAO plantDAO){
        this.plantDAO = plantDAO;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plant> getPlant(@PathVariable int id){
        LOG.info("GET /plants/" + id);

        try{
            Plant plant = plantDAO.getPlant(id);
            if(plant != null)
                return new ResponseEntity<Plant>(plant,HttpStatus.OK);
            
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);    
        }
        catch(IOException e){
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<Plant> createPlant(@RequestBody Plant plant){
        LOG.info("POST /plants " + plant);
        try{
            Plant newPlant = plantDAO.createPlant(plant);
            if(newPlant != null)
                return new ResponseEntity<Plant>(newPlant,HttpStatus.CREATED);  
            else
                return new ResponseEntity<Plant>(newPlant,HttpStatus.CONFLICT);    
        }
        catch(IOException e){
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public ResponseEntity<Plant[]> searchPlants(@RequestParam String name){
        LOG.info("GET /plants/?name=" + name);

        try{
            return new ResponseEntity<Plant[]>(plantDAO.findPlants(name),HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Plant> deletePlant(@PathVariable int id){
        LOG.info("DELETE /plants/" + id);
        try {
            if (plantDAO.getPlant(id) != null) {
                plantDAO.deletePlants(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("")
    public ResponseEntity<Plant> updatePlant(@RequestBody Plant plant) {
        LOG.info("PUT /plants " + plant);
        try {
            if (plantDAO.getPlant(plant.getId()) != null) {
                return new ResponseEntity<Plant>(plantDAO.updatePlant(plant), HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public ResponseEntity<Plant[]> getPlants() {
        LOG.info("GET /plants");
        try {
            Plant[] plants = plantDAO.getPlants();
            return new ResponseEntity<Plant[]>(plants,HttpStatus.OK);    
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
