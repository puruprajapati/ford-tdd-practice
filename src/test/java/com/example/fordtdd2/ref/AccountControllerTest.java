package com.example.fordtdd2.ref;

import accounts.service.AccountDTO;
import accounts.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
@AutoConfigureDataJpa
public class AccountControllerTest {
  @Autowired
  MockMvc mock;

  @MockBean
  AccountService accountService;

  @Test
  public void testGetAccountByAccountNumber() throws Exception{
    Mockito.when(accountService.getAccount("1")).thenReturn(
      new AccountDTO("1", 1000,"Puru"));
    mock.perform(MockMvcRequestBuilders.get("/account/1"))
      .andExpect(status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$.accountNumber").value("1"))
      .andExpect(MockMvcResultMatchers.jsonPath("$.balance").value(1000))
      .andExpect(MockMvcResultMatchers.jsonPath("$.accountHolder").value("Puru"));
  }
}
