package com.CCMS.controller;

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
import com.CCMS.model.Engine;

public class CarControllerTest extends AbstractTest{

    @Override
    @BeforeEach
    public void setUp(){

        super.setUp();

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
        String uri = "/car/3";

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
        String uri = "/car/update/3";

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

    // Car Engine API Test

    // POST Car Engine Test
    @Test
    public void createCarEngine() throws Exception{

        String uriCreateEngine = "/car/1/engine";

        JSONObject inputJson = new JSONObject();
        inputJson.put("name", "newEngine");
        inputJson.put("electrical", "false");
        inputJson.put("cilinders", "8");
        inputJson.put("fuel", "0");

        MvcResult mvcResultEngine = mvc.perform(MockMvcRequestBuilders.post(uriCreateEngine)
            .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson.toString())).andReturn();
        
        int status = mvcResultEngine.getResponse().getStatus();
        assertEquals(201, status);

    }

    // PUT Car Engine Test
    @Test
    public void updateCarEngine() throws Exception{
    
            String uriCreateEngine = "/car/1/engine";
    
            JSONObject inputJson = new JSONObject();
            inputJson.put("name", "newEngineEdited");
            inputJson.put("electrical", "false");
            inputJson.put("cilinders", "8");
            inputJson.put("fuel", "0");
    
            MvcResult mvcResultEngine = mvc.perform(MockMvcRequestBuilders.put(uriCreateEngine)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson.toString())).andReturn();
            
            int status = mvcResultEngine.getResponse().getStatus();
            assertEquals(202, status);
            
        }

    // GET Car Engine Test
    @Test
    public void getCarEngine() throws Exception{

        String uri = "/car/3/engine";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
            .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        
        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);

        String content = mvcResult.getResponse().getContentAsString();
        Engine engine = super.mapFromJson(content, Engine.class);

        assertTrue(engine != null);

    }

    // DELETE Car Engine Test
    @Test
    public void deleteCarEngine() throws Exception{
        String uri = "/car/1/engine";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(202, status);
    }
    
   
}