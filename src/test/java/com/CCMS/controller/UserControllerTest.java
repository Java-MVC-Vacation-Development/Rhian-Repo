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

import com.CCMS.CCMS.AbstractTest;
import com.CCMS.model.User;

public class UserControllerTest extends AbstractTest{

    @BeforeEach
    @Override
    public void setUp(){

        String uriCreate = "/user/create";

        super.setUp();

        JSONObject inputJson = new JSONObject();
        try {
            inputJson.put("name", "newEngine");
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
    public void createEngine() throws Exception {

        String uriCreate = "/user/create";

        JSONObject inputJson = new JSONObject();
        inputJson.put("name", "newEngine");

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriCreate)
            .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson.toString())).andReturn();
        
        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
    }

    // GET All Car Test
    @Test
    public void getEngine() throws Exception {
        String uri = "/user/getall";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
            .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        User[] userList = super.mapFromJson(content, User[].class);

        assertTrue(userList.length > 0);
    }

    // GET Single Car test
    @Test
    public void getSingleEngine() throws Exception {
        String uri = "/user/1";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
            .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        User user = super.mapFromJson(content, User.class);

        assertTrue(user != null);
    }

    // PUT Car Test
    @Test
    public void updateEngine() throws Exception {
        String uri = "/user/update/1";

        JSONObject inputJsonToEdit = new JSONObject();
        inputJsonToEdit.put("id",  "1");
        inputJsonToEdit.put("name", "newEngineEdited");
        inputJsonToEdit.put("electrical", "false");

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
            .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJsonToEdit.toString())).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(202, status);
    }

    // DELETE Car Test
    @Test
    public void deleteEngine() throws Exception {
        String uri = "/user/delete/2";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(202, status);
    }



}
