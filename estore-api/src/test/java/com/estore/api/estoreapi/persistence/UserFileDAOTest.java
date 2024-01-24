package com.estore.api.estoreapi.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import com.estore.api.estoreapi.model.Plant;
import com.estore.api.estoreapi.model.User;
import com.estore.api.estoreapi.persistence.UserFileDAO;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Persistence-tier")
public class UserFileDAOTest {

    UserFileDAO userFileDAO;
    Plant cactus = new Plant(1, "Cactus", 10, 10);
    Plant fern = new Plant(2, "Fern", 20, 20);

    //a mock shopping cart for a customer
    private List<Plant> testProducts = new ArrayList<Plant>(){{
        add(cactus);
        add(fern);
    }};


    //a mock array of customers with an admin
    private User[] tCustomers = {
        new User(0, "admin", "", testProducts),
        new User(1, "user", "testpassword", testProducts)
    };

    String filepath = "testUsers.json";
    
    @BeforeEach
    public void setup() throws IOException {

        this.userFileDAO = mock(UserFileDAO.class);
        File file = new File(filepath);
        file.createNewFile();
    }

    // @Test
    public void testCreateUser() throws IOException {
        User user = new User(0, "admin", "", testProducts);
        User user2 = userFileDAO.createUser(user);

        assertEquals(user2.getId(), 0);
    }
}
