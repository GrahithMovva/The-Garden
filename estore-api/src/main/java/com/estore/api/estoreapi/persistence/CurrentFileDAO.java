package com.estore.api.estoreapi.persistence;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.estore.api.estoreapi.model.Plant;
import com.estore.api.estoreapi.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CurrentFileDAO implements CurrentDAO {
    private User user;
    private ObjectMapper objectMapper;
    private String filename;
    private PlantDAO plantDAO;
    private UserDAO userFile;
    

    public CurrentFileDAO(@Value("${current.file}") String filename, ObjectMapper objectMapper, @Value("${users.file}") String filename2, @Value("${plants.file}") String filename3) throws IOException{
        this.filename = filename;
        this.objectMapper = objectMapper;
        this.userFile = new UserFileDAO(filename2, objectMapper, filename3);
        this.plantDAO = new PlantFileDAO(filename3, objectMapper);

        
    }


    private boolean save() throws IOException{
        User user = this.user;
        objectMapper.writeValue(new File(filename),user);
        return true;
    }

    @Override
    public User getCurrentUser() throws IOException {
        synchronized(user){
            return objectMapper.readValue(new File(filename),User.class);
    }
}

    @Override
    public User setCurrentUser(User user) throws IOException {
        synchronized(user){
            this.user = userFile.getUser(user.getId());
            save();
            return user;
        }
    }

    @Override
    public Plant[] AddtoCart(Plant plant) throws IOException {
        synchronized(user){
            User user = this.user;
            if(plantDAO.findPlants(plant.getName()).length > 0){
                user.getCart().add(plantDAO.findPlants(plant.getName())[0]);
            }
            Plant[] plantArray = new Plant[user.getCart().size()];
            user.getCart().toArray(plantArray);
            save();
            userFile.updateUser(this.user);

            return plantArray;
        }
    }

    @Override
    public Plant[] RemoveFromCart(Plant plant) throws IOException {
        synchronized(user){
            User user = this.user;
            if(plantDAO.findPlants(plant.getName()).length > 0){
                Plant tempPlant = plantDAO.findPlants(plant.getName())[0];
                for(int i = 0; i < user.getCart().size();i++){
                    if(user.getCart().get(i).getId() == tempPlant.getId()){
                        user.getCart().remove(user.getCart().get(i));
                        break;
                    }
                }
                
            }
            Plant[] plantArray = new Plant[user.getCart().size()];
            user.getCart().toArray(plantArray);
            save();
            return plantArray;
        }
    }

}
