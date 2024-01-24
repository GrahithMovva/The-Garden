package com.estore.api.estoreapi.model;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class UserTests {
    @Test
	public void testToString_user() {

		int id = 99;
		String name = "Wi-Fire";
		String password = "1234";
		String expected_string = String.format(User.STRING_FORMAT,id,name,password);
		List<Plant> list = new ArrayList<Plant>();
		User user = new User(id,name,password,list);

		String actual_string = user.toString();

		assertEquals(expected_string,actual_string);
	}

	@Test
	public void testGetID(){
		int id = 99;
		String name = "Wi-Fire";
		String password = "1234";
		String expected_string = String.format(User.STRING_FORMAT,id,name,password);
		List<Plant> list = new ArrayList<Plant>();
		User user = new User(id,name,password,list);
		user.getId();
		String actual_string = user.toString();
		assertEquals(expected_string,actual_string);
	}

	@Test
	public void TestgetUsername(){
		int id = 99;
		String name = "Wi-Fire";
		String password = "1234";
		String expected_string = String.format(User.STRING_FORMAT,id,name,password);
		List<Plant> list = new ArrayList<Plant>();
		User user = new User(id,name,password,list);
		user.getUsername();
		String actual_string = user.toString();
		assertEquals(expected_string,actual_string);
	}

	@Test
	public void TestgetPassword(){
		int id = 99;
		String name = "Wi-Fire";
		String password = "1234";
		String expected_string = String.format(User.STRING_FORMAT,id,name,password);
		List<Plant> list = new ArrayList<Plant>();
		User user = new User(id,name,password,list);
		user.getPassword();
		String actual_string = user.toString();
		assertEquals(expected_string,actual_string);
	}

	@Test
	public void TestgetCart(){
		int id = 99;
		String name = "Wi-Fire";
		String password = "1234";
		String expected_string = String.format(User.STRING_FORMAT,id,name,password);
		List<Plant> list = new ArrayList<Plant>();
		User user = new User(id,name,password,list);
		user.getCart();
		String actual_string = user.toString();
		assertEquals(expected_string,actual_string);
	}
}