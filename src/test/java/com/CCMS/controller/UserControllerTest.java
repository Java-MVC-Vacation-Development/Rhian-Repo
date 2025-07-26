package com.CCMS.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.CCMS.CCMS.AbstractTest;
import com.CCMS.model.Car;
import com.CCMS.model.User;

public class UserControllerTest extends AbstractTest{

    @BeforeEach
    @Override
    public void setUp(){

        super.setUp();

    }

    // Main API Tests

    // POST User Test
    @Test
    public void createUser() throws Exception {

        String uriCreate = "/user/create";

        JSONObject inputJson = new JSONObject();
        inputJson.put("name", "newUser");

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriCreate)
            .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson.toString())).andReturn();
        
        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
    }

    // GET All User Test
    @Test
    public void getUser() throws Exception {
        String uri = "/user/getall";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
            .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        User[] userList = super.mapFromJson(content, User[].class);

        assertTrue(userList.length > 0);
    }

    // GET Single User test
    @Test
    public void getSingleUser() throws Exception {
        String uri = "/user/3";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
            .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        User user = super.mapFromJson(content, User.class);

        assertTrue(user != null);
    }

    // PUT User Test
    @Test
    public void updateUser() throws Exception {
        String uri = "/user/update/3";

        JSONObject inputJsonToEdit = new JSONObject();
        inputJsonToEdit.put("id",  "3");
        inputJsonToEdit.put("name", "newUserEdited");

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
            .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJsonToEdit.toString())).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(202, status);
    }

    // DELETE User Test
    @Test
    public void deleteUser() throws Exception {
        String uri = "/user/delete/2";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(202, status);
    }

    // User Car API Test

    // POST User Car Test
    @Test
    public void createUserCar() throws Exception {

        String uriCreate = "/user/3/cars";

        JSONArray inputJsonArray = new JSONArray();

        JSONObject inputJson = new JSONObject();
        inputJson.put("name", "newCar");

        inputJsonArray.put(inputJson);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriCreate)
            .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJsonArray.toString())).andReturn();
        
        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
    }

    // GET All User Car Test
    @Test
    public void getUserCar() throws Exception {
        String uri = "/user/3/cars";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
            .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        Car[] userCarList = super.mapFromJson(content, Car[].class);

        assertTrue(userCarList.length > 0);
    }

    // GET Single User Car Test
    @Test
    public void getSingleUserCar() throws Exception {
        String uri = "/user/3/cars/3";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
            .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        Car userCar = super.mapFromJson(content, Car.class);

        assertTrue(userCar != null);
    }

    // PUT User Car Test
    @Test
    public void updateUserCar() throws Exception {
        String uri = "/user/3/cars/1";

        JSONObject inputJsonToEdit = new JSONObject();
        inputJsonToEdit.put("name", "newUserCarEdited");

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
            .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJsonToEdit.toString())).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(202, status);
    }

    // DELETE User Car Test
    @Test
    public void deleteUserCar() throws Exception {
        String uri = "/user/1/cars/1";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(202, status);
    }



}
