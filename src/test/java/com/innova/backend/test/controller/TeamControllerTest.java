package com.innova.backend.test.controller;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.innova.backend.config.AppInitializer;
import com.innova.backend.config.WebConfig;
import com.innova.backend.controller.TeamController;
import com.innova.backend.model.Country;
import com.innova.backend.model.Team;
import com.innova.backend.service.TeamService;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

public class TeamControllerTest {
	@Autowired
	WebApplicationContext context;
	
    private MockMvc mockMvc;

    @Mock
    private TeamService teamService;

    @InjectMocks
    private TeamController teamController;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
        		.standaloneSetup(teamController)
                .build();
    }
    
    @Test
    public void test_get_team_success() throws Exception {
        List<Team> teams = Arrays.asList(
        		new Team((long) 1, "team1", new Country((long) 1, "es", "spain"), null, null, null), 
        		new Team((long) 2, "team2", new Country((long) 2, "es", "spain"), null, null, null));	
        
        Mockito.when(teamService.list()).thenReturn(teams);
        
        mockMvc.perform(MockMvcRequestBuilders.get("/team"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", is("team1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", is("team2")));

        
        Mockito.verify(teamService, Mockito.times(1)).list(); 
        Mockito.verifyNoMoreInteractions(teamService);
    }
    
    @Test
    public void test_post_team_success() throws Exception {
        Team team = new Team((long) 1, "team1", null, null, null, null);
        
        Mockito.when(teamService.save(team)).thenReturn((long) 1);
        
        mockMvc.perform(MockMvcRequestBuilders.post("/team")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(team)))
                		.andExpect(MockMvcResultMatchers.status().isCreated());

        verify(teamService, times(1)).save(team);
        verifyNoMoreInteractions(teamService);
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}