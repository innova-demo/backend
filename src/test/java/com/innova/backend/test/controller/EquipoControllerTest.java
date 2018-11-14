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
import com.innova.backend.config.WebConf;
import com.innova.backend.controller.EquipoController;
import com.innova.backend.model.Country;
import com.innova.backend.model.Team;
import com.innova.backend.service.EquipoService;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

public class EquipoControllerTest {
	@Autowired
	WebApplicationContext context;
	
    private MockMvc mockMvc;

    @Mock
    private EquipoService equipoService;

    @InjectMocks
    private EquipoController equipoController;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
        		.standaloneSetup(equipoController)
                .build();
    }
    
    @Test
    public void test_get_equipo_success() throws Exception {
        List<Team> teams = Arrays.asList(
        		new Team((long) 1, "equipo1", new Country((long) 1, "es", "spain"), null, null, null), 
        		new Team((long) 2, "equipo2", new Country((long) 2, "es", "spain"), null, null, null));	
        
        Mockito.when(equipoService.list()).thenReturn(teams);
        
        mockMvc.perform(MockMvcRequestBuilders.get("/equipo"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", is("equipo1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", is("equipo2")));

        
        Mockito.verify(equipoService, Mockito.times(1)).list(); 
        Mockito.verifyNoMoreInteractions(equipoService);
    }
    
    @Test
    public void test_post_equipo_success() throws Exception {
        Team team = new Team((long) 1, "equipo1", null, null, null, null);
        
        Mockito.when(equipoService.save(team)).thenReturn((long) 1);
        
        mockMvc.perform(MockMvcRequestBuilders.post("/equipo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(team)))
                		.andExpect(MockMvcResultMatchers.status().isCreated());

        verify(equipoService, times(1)).save(team);
        verifyNoMoreInteractions(equipoService);
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}