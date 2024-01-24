package com.estore.api.estoreapi;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.estore.api.estoreapi.model.Plant;
import com.estore.api.estoreapi.persistence.PlantFileDAO;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class EstoreApiApplicationTests {

	// @Test
	// public void testConstructorException() throws IOException {
	// 	// Setup
	// 	ObjectMapper mockObjectMapper = mock(ObjectMapper.class);
	// 	doThrow(new IOException()).when(mockObjectMapper).readValue(new File("doesnt_matter.txt"),Plant[].class);
	// 	assertThrows(IOException.class,() -> new PlantFileDAO("doesnt_matter.txt",mockObjectMapper),"IOException not thrown"); 
	// }
	

}
