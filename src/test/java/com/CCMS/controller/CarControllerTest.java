package com.CCMS.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.CCMS.model.Car;
import com.CCMS.CCMS.AbstractTest;

public class CarControllerTest extends AbstractTest{

    @BeforeEach
    @Override
    public void setUp(){

        String uriCreate = "/car/create";

        super.setUp();

        JSONObject inputJson = new JSONObject();
        try {
            inputJson.put("name", "newCar");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            mvc.perform(MockMvcRequestBuilders.post(uriCreate)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson.toString())).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Main API Tests

    // POST Car Test
    @Test
    public void createCar() throws Exception {

        String uriCreate = "/car/create";

        JSONObject inputJson = new JSONObject();
        inputJson.put("name", "newCar");

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriCreate)
            .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson.toString())).andReturn();
        
        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
    }

    // GET All Car Test
    @Test
    public void getCar() throws Exception {
        String uri = "/car/getall";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
            .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
        Car[] carList = super.mapFromJson(content, Car[].class);

        assertTrue(carList.length > 0);
    }

    // GET Single Car test
    @Test
    public void getSingleCar() throws Exception {
        String uri = "/car/1";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
            .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        Car car = super.mapFromJson(content, Car.class);

        assertTrue(car != null);
    }

    // PUT Car Test
    @Test
    public void updateCar() throws Exception {
        String uri = "/car/update/1";

        JSONObject inputJsonToEdit = new JSONObject();
        inputJsonToEdit.put("id",  "1");
        inputJsonToEdit.put("name", "newCarEdited");

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
            .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJsonToEdit.toString())).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(202, status);
    }

    // DELETE Car Test
    @Test
    public void deleteCar() throws Exception {
        String uri = "/car/delete/2";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(202, status);
    }

}