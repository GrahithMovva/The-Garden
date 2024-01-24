package com.estore.api.estoreapi.model;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("model-tier")
class PlantsTests {

    @Test
	public void testToString_plant() {
	// Setup
		int id = 99;
		String name = "Wi-Fire";
		int price = 12;
		int quantity = 14;
		String expected_string = String.format(Plant.STRING_FORMAT,id,name,price,quantity);
		Plant plant = new Plant(id,name,price,quantity);
		// Invoke
		String actual_string = plant.toString();
		// Analyze
		assertEquals(expected_string,actual_string);
	}

	@Test
	public void testSetname(){
		// Setup
		int id = 99;
		String name = "Wi-Fire";
		int price = 12;
		int quantity = 14;
		String expected_string = String.format(Plant.STRING_FORMAT,id,name,price,quantity);
		Plant plant = new Plant(id,name,price,quantity);
		plant.setName(name);
		String actual_string = plant.toString();
		assertEquals(expected_string,actual_string);
	}

	@Test
	public void testGetname(){
		// Setup
		int id = 99;
		String name = "Wi-Fire";
		int price = 12;
		int quantity = 14;
		Plant plant = new Plant(id,name,price,quantity);
		String actual_string = plant.getName();
		assertEquals(name,actual_string);
	}

	@Test
	public void testGetprice(){
		// Setup
		int id = 99;
		String name = "Wi-Fire";
		int price = 12;
		int quantity = 14;
		Plant plant = new Plant(id,name,price,quantity);
		int actual_string = plant.getprice();
		assertEquals(price,actual_string);
	}

	@Test
	public void testGetquantity(){
		// Setup
		int id = 99;
		String name = "Wi-Fire";
		int price = 12;
		int quantity = 14;
		Plant plant = new Plant(id,name,price,quantity);
		int actual_string = plant.getQuantity();
		assertEquals(quantity,actual_string);
	}

	@Test
	public void testGetid(){
		// Setup
		int id = 99;
		String name = "Wi-Fire";
		int price = 12;
		int quantity = 14;
		Plant plant = new Plant(id,name,price,quantity);
		int actual_string = plant.getId();
		assertEquals(id,actual_string);
	}
}
