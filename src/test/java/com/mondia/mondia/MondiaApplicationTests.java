package com.mondia.mondia;

import java.util.HashMap;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MondiaApplicationTests {

	@Test
	public void contextLoads() {
	}
	@Autowired
    private MockMvc mockMvc;

   
//  @Before
//  public void setUp() {
//
//      user = new HashMap<>();
//      user.put("username", "malike");
//      user.put("password", "password");
//      user.put("email", "st.malike@gmail.com");
//      user.put("firstName", "Malike");
//      user.put("lastName", "Kendeh");
//
//
//      RestAssuredMockMvc.standaloneSetup(signupController);
//  }


}
