package com.kata.user.endToEnd;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.kata.user.entity.User;
import com.kata.user.repository.UserRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.time.LocalDate;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;


import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserEndToEndTest {
	
	
	
    @Autowired
    private MockMvc mockMvc;
   
    
    @MockBean
	private UserRepository mockRepository;
	 
   

    @Test
    public void should_returnBadRequest_when_argumentIsWrong() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/user/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":null," +
                                "\"name\":\"John Allou\"," +
                                "\"birthDate\":\"1945-10-11\"," +
                                "\"country\":\"USA\"," +
                                "\"phoneNumber\":\"0613151419\"," +
                                "\"gender\":\"sdsd\"}")
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    @Test
    public void sould_getUserById_when_userIsPresent() throws Exception {
    	LocalDate date = LocalDate.of(1997, 7, 5);
        User user = new User("Axel Aboyeji",date,"France","0652532936","male");

    	Mockito.when(mockRepository.findUserById(Mockito.eq(1L))).thenReturn(Optional.of(user));
        mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}",1))
        		.andExpect(MockMvcResultMatchers.status().isOk())
        		.andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", containsString("Axel")));
        
    }
    @Test
    public void sould_returnNotFound_when_userIsNotPresent() throws Exception {
    	
        mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}",1))
        		.andDo(print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
        
    }
    @Test
  public void sould_returnBadRequest_when_userIsNull() throws Exception {
    	
        mockMvc.perform(MockMvcRequestBuilders.get("/user/null"))
        		.andDo(print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        
    }
    @Test
    public void sould_postUser_when_userNotAlreadyCreated() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/user/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1," +
                                "\"name\":\"Axel Aboyeji\"," +
                                "\"birthDate\":\"1996-12-25\"," +
                                "\"country\":\"France\"," +
                                "\"phoneNumber\":\"0601020304\"," +
                                "\"gender\":\"male\"}")
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", containsString("Axel")));
        

    }
    @Test
    public void should_returnBadRequest_when_userAlreadyExist() throws Exception {
    	LocalDate date = LocalDate.of(1997, 07, 05);
        User user = new User("Axel Aboyeji",date,"France","0652532936","male");

    	Mockito.when(mockRepository.findUserByNameAndBirthDate(user.getName(),user.getBirthDate())).thenReturn(Optional.of(user));
    	mockMvc.perform(MockMvcRequestBuilders
                        .post("/user/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1," +
                                "\"name\":\"Axel Aboyeji\"," +
                                "\"birthDate\":\"1997-07-05\"," +
                                "\"country\":\"France\"," +
                                "\"phoneNumber\":\"0652532936\"," +
                                "\"gender\":\"male\"}")
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    
}
