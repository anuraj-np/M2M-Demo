package com.auth.m2m.controller;

import com.auth.m2m.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(authorities = {"SCOPE_read"})
    void testGetUser_WithAuthorization() throws Exception {
        mockMvc.perform(get("/user/1")
                        //.header(HttpHeaders.AUTHORIZATION, "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Il9RTFBiQ0JnZXMxaG5tbkZFa2N1RCJ9.eyJpc3MiOiJodHRwczovL2Rldi0wb2g2ZXVsY2RsYzE1Y2Z5LnVzLmF1dGgwLmNvbS8iLCJzdWIiOiJjTHhvd2xiUDBuV0FKald5dVdlUWk4eXdCVGxlTmhsUEBjbGllbnRzIiwiYXVkIjoiaHR0cHM6Ly91c2VyLWFwaSIsImlhdCI6MTcyNTU5ODEyMywiZXhwIjoxNzI1Njg0NTIzLCJzY29wZSI6InJlYWQgdXBkYXRlIHdyaXRlIiwiZ3R5IjoiY2xpZW50LWNyZWRlbnRpYWxzIiwiYXpwIjoiY0x4b3dsYlAwbldBSmpXeXVXZVFpOHl3QlRsZU5obFAifQ.gJqae41MeYh9vYrXFtT9N8I8J9-rb_kTYVH2izLBd30M3gBtVjYFlPwf-A1hkGkbCxYGdT50-havTBCY2evDeIFHI1yDAg8CRG0su8N_R-NAlBB-6Bah1mo64vUM4blXIWH72cffDr9CyLrzoqW0I9aJ6HTAgZDd59BpKKF7FdxvfwWtsVBoTkuNKAzKbIcRGvRn9WjGc0T2EcEksflu0_pDr5hiE05Q5FjCoU9pctB_imdGvw9bVENtehHEa76B9gmjdLr5Batp-DhdJP2HXTNp9XhK3Vs8i_n7oanK1h_9TkiD3vx7w5db-twyUZE6o5ExHt8ngm2X98cygCP1Rw")  // Mocked token
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("User 1 successfully retrieved!"));
    }

    @Test
    @WithMockUser(authorities = {"SCOPE_write"})  // Wrong scope
    void testGetUser_Forbidden() throws Exception {
        mockMvc.perform(get("/user/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    void testGetUser_Unauthorized() throws Exception {
        mockMvc.perform(get("/user/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }
}
