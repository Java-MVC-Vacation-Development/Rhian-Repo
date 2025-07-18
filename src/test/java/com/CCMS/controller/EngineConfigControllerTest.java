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
import com.CCMS.model.EngineConfig;

public class EngineConfigControllerTest extends AbstractTest{


    @BeforeEach
    @Override
    public void setUp(){

        String uriCreate = "/engineconfig/create";

        super.setUp();

        JSONObject inputJson = new JSONObject();
        try {
            inputJson.put("configName", "newEngine");
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
    public void createEngineConfig() throws Exception {

        String uriCreate = "/engineconfig/create";

        JSONObject inputJson = new JSONObject();
        inputJson.put("configName", "newEngine");

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriCreate)
            .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson.toString())).andReturn();
        
        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
    }

    // GET All Car Test
    @Test
    public void getEngineConfig() throws Exception {
        String uri = "/engineconfig/getall";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
            .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        EngineConfig[] engineCongfigList = super.mapFromJson(content, EngineConfig[].class);

        assertTrue(engineCongfigList.length > 0);
    }

    // GET Single Car test
    @Test
    public void getSingleEngineConfig() throws Exception {
        String uri = "/engineconfig/1";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
            .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        EngineConfig engine = super.mapFromJson(content, EngineConfig.class);

        assertTrue(engine != null);
    }

    // PUT Car Test
    @Test
    public void updateEngineConfig() throws Exception {
        String uri = "/engineconfig/update/1";

        JSONObject inputJsonToEdit = new JSONObject();
        inputJsonToEdit.put("id",  "1");
        inputJsonToEdit.put("configName", "newEngineEdited");

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
            .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJsonToEdit.toString())).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(202, status);
    }

    // DELETE Car Test
    @Test
    public void deleteEngineConfig() throws Exception {
        String uri = "/engineconfig/delete/2";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(202, status);
    }


}