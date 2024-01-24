package com.estore.api.estoreapi.persistence;
import com.estore.api.estoreapi.model.Plant;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PlantFileDAO implements PlantDAO {
    Map<Integer,Plant> plants;
    private ObjectMapper objectMapper;
    private static int nextId;
    private String filename;

    public PlantFileDAO(@Value("${plants.file}") String filename, ObjectMapper objectMapper) throws IOException{
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();
    }

    private synchronized static int nextId(){
        int id = nextId;
        ++nextId;
        return id;
    }

    private Plant[] getPlantsArray(String text){
        ArrayList<Plant> plantArrayList = new ArrayList<>();

        for(Plant plant : plants.values()){
            if(text == null || plant.getName().contains(text)){
                plantArrayList.add(plant);
            }
        }
        Plant[] plantArray = new Plant[plantArrayList.size()];
        plantArrayList.toArray(plantArray);
        return plantArray;
    }

    private Plant[] getPlantsArray(){
        return getPlantsArray(null);
    }

    private boolean save() throws IOException{
        Plant[] plantArray = getPlantsArray();
        objectMapper.writeValue(new File(filename),plantArray);
        return true;
    }

    private boolean load() throws IOException{
        plants = new TreeMap<>();
        nextId = 0;

        Plant[] plantArray = objectMapper.readValue(new File(filename), Plant[].class);
        for(Plant plant : plantArray){
            plants.put(plant.getId(),plant);
            if(plant.getId() > nextId)
                nextId = plant.getId();
        }
        ++nextId;
        return true;
    }

    @Override
    public Plant[] getPlants() throws IOException {
        synchronized(plants){
            return getPlantsArray();
        }
    }

    @Override
    public Plant[] findPlants(String text) throws IOException {
        synchronized(plants){
            return getPlantsArray(text);
        }
    }

    @Override
    public Plant getPlant(int id) throws IOException {
        synchronized(plants){
            if(plants.containsKey(id))
                return plants.get(id);
            else
                return null;
        }
    }

    @Override
    public Plant createPlant(Plant plant) throws IOException {
        synchronized(plants){
            Plant newPlant = new Plant(nextId(), plant.getName(), plant.getprice(), plant.getQuantity());
            plants.put(newPlant.getId(),newPlant);
            save();
            return newPlant;
        }
    }

    @Override
    public Plant updatePlant(Plant plant) throws IOException {
        synchronized(plants){
            if(plants.containsKey(plant.getId()) == false)
                return null;
            
            plants.put(plant.getId(), plant);
            save();
            return plant;
            
        }
    }

    @Override
    public boolean deletePlants(int id) throws IOException {
        synchronized(plants){
            if(plants.containsKey(id)){
                plants.remove(id);
                --nextId;
                return save();
            }
            else
                return false;
        }
    }
}