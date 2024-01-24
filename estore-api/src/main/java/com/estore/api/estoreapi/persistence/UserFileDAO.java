package com.estore.api.estoreapi.persistence;

import com.estore.api.estoreapi.model.Plant;
import com.estore.api.estoreapi.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserFileDAO implements UserDAO {
    Map<Integer,User> users;
    private ObjectMapper objectMapper;
    private static int nextId;
    private String filename;
    private PlantFileDAO plantDAO;
    

    public UserFileDAO(@Value("${users.file}") String filename, ObjectMapper objectMapper,@Value("${plants.file}") String filename2) throws IOException{
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();
        
        this.plantDAO = new PlantFileDAO(filename2, objectMapper);
    }

    private synchronized static int nextId(){
        int id = nextId;
        ++nextId;
        return id;
    }

    

    private User[] getUsersArray(String text){
        ArrayList<User> userArrayList = new ArrayList<>();

        for(User User : users.values()){
            if(text == null || User.getUsername().contains(text)){
                userArrayList.add(User);
            }
        }
        User[] UserArray = new User[userArrayList.size()];
        userArrayList.toArray(UserArray);
        return UserArray;
    }

    private User[] getUsersArray(){
        return getUsersArray(null);
    }

    private boolean save() throws IOException{
        User[] userArray = getUsersArray();
        objectMapper.writeValue(new File(filename),userArray);
        return true;
    }

    private boolean load() throws IOException{
        users = new TreeMap<>();
        nextId = 0;

        User[] UserArray = objectMapper.readValue(new File(filename), User[].class);
        for(User User : UserArray){
            users.put(User.getId(),User);
            if(User.getId() > nextId)
                nextId = User.getId();
        }
        ++nextId;
        return true;
    }

    @Override
    public User[] getUsers() throws IOException {
        synchronized(users){
            return getUsersArray();
        }
    }

    @Override
    public User[] findUsers(String text) throws IOException {
        synchronized(users){
            return getUsersArray(text);
        }
    }

    @Override
    public User getUser(int id) throws IOException {
        synchronized(users){
            if(users.containsKey(id))
                return users.get(id);
            else
                return null;
        }
    }

    @Override
    public User createUser(User User) throws IOException {
        synchronized(users){
            List<Plant> cart = User.getCart();
            if(User.getCart() == null){
                cart = new ArrayList<Plant>();
            }
            User newUser = new User(nextId(), User.getUsername(), User.getPassword(),cart);
            users.put(newUser.getId(),newUser);
            save();
            return newUser;
        }
    }

    @Override
    public User updateUser(User user) throws IOException {
        synchronized(users){
            if(users.containsKey(user.getId()) == false)
                return null;
            
            users.put(user.getId(), user);
            save();
            return user;
            
        }
    }

    @Override
    public boolean deleteUsers(int id) throws IOException {
        synchronized(users){
            if(users.containsKey(id)){
                users.remove(id);
                --nextId;
                return save();
            }
            else
                return false;
        }
    }

   
    @Override
    public Plant[] addtocart(int id, Plant plant) throws IOException {
        synchronized(users){
            User user = this.getUser(id);
            if(plantDAO.findPlants(plant.getName()).length > 0){
                user.getCart().add(plantDAO.findPlants(plant.getName())[0]);
            }
            Plant[] plantArray = new Plant[user.getCart().size()];
            user.getCart().toArray(plantArray);
            save();
            return plantArray;
        }
        
    }

    @Override
    public Plant[] removefromcart(int id, Plant plant) throws IOException {
        synchronized(users){
            User user = this.getUser(id);
            if(plantDAO.findPlants(plant.getName()).length > 0){
                // if(user.getCart().contains(plantDAO.findPlants(plant.getName())[0])){
                //     user.getCart().remove(plantDAO.findPlants(plant.getName())[0]);
                // if(user.getCart().contains(plantDAO.getPlant(plant.getId()))){
                Plant tempPlant = plantDAO.findPlants(plant.getName())[0];
                for(int i = 0; i < user.getCart().size();i++){
                    if(user.getCart().get(i).getId() == tempPlant.getId()){
                        user.getCart().remove(user.getCart().get(i));
                        
                        
                    }
                }
                
            }
            Plant[] plantArray = new Plant[user.getCart().size()];
            user.getCart().toArray(plantArray);
            save();
            return plantArray;
        }
    }

    @Override
    public Plant[] checkout(int id) throws IOException {
        synchronized(users){
            User user = this.getUser(id);
            user.getCart().clear();
            Plant[] plantArray = new Plant[user.getCart().size()];
            user.getCart().toArray(plantArray);
            return plantArray;
            
        }
    }

    
}
