package com.estore.api.estoreapi.persistence;

import java.io.IOException;

import com.estore.api.estoreapi.model.Plant;

public interface PlantDAO {

    Plant[] getPlants() throws IOException;

    Plant[] findPlants(String text) throws IOException;

    Plant getPlant(int id) throws IOException;

    Plant createPlant(Plant plant) throws IOException;

    Plant updatePlant(Plant plant) throws IOException;

    boolean deletePlants(int id) throws IOException;

    
    
}
