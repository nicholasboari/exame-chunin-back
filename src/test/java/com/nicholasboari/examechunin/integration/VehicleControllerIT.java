package com.nicholasboari.examechunin.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nicholasboari.examechunin.util.TokenUtil;
import com.nicholasboari.examechunin.util.VehiclePostRequestBodyCreator;
import com.nicholasboari.examechunin.util.VehiclePutRequestBodyCreator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class VehicleControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final String REQUEST_ADMIN_CREDENTIALS = "{\"login\": \"nicholasboari\", \"password\": \"123\"}";

    @Test
    void deleteShouldReturnNoContentWhenIdExists() throws Exception {
        String token = TokenUtil.getToken(mockMvc, REQUEST_ADMIN_CREDENTIALS);

        ResultActions result =
                mockMvc.perform(delete("/vehicles/{id}", 1L)
                        .header("Authorization", "Bearer " + token)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNoContent());
    }

    @Test
    void insertShouldReturnVehicleCreated() throws Exception {
        String token = TokenUtil.getToken(mockMvc, REQUEST_ADMIN_CREDENTIALS);
        String jsonBody = objectMapper.writeValueAsString(VehiclePostRequestBodyCreator.createVehicleToBeSaved());
        ResultActions result = mockMvc.perform(post("/vehicles")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody)
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isCreated());

        result.andExpect(jsonPath("$.id").value(2L));
        result.andExpect(jsonPath("$.name").value("T-cross"));
    }

    @Test
    void updateShouldReturnVehicleUpdated() throws Exception {
        String token = TokenUtil.getToken(mockMvc, REQUEST_ADMIN_CREDENTIALS);
        String jsonBody = objectMapper.writeValueAsString(VehiclePutRequestBodyCreator.createVehicleToBeSaved());
        ResultActions result = mockMvc.perform(put("/vehicles/{id}", 2L)
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody)
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.name").value("Nivus"));
    }

    @Test
    void testLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(REQUEST_ADMIN_CREDENTIALS))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}