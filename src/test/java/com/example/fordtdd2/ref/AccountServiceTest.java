package com.example.fordtdd2.ref;

import accounts.domain.Account;
import accounts.repositories.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
public class AccountServiceTest {
  @TestConfiguration
  static class AccountServiceImplTestContextConfiguration{
    @Bean
    public AccountService accountService(){
      return new AccountService();
    }
  }

  @Autowired
  private AccountService accountService;

  @MockBean
  private AccountRepository accountRepository;

  @Before
  public void setUp(){
    Account ac = new Account("1", 20000, "Puru Prajapati");
    Optional<Account> optionalAccount = Optional.of(ac);
    Mockito.when(accountRepository.findById("1")).thenReturn(optionalAccount);
  }

  @Test
  public void whenValidAccountThenAccountShouldBeFound(){
    AccountDTO found = accountService.getAccount("1");
    assertThat(found.getAccountNumber()).isEqualTo("1");
  }


  @Test
  void payOrder() {
    Order order = new Order(1L, false);
    when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
    when(paymentRepository.save(any())).then(returnsFirstArg());

    Payment payment = orderService.pay(1L, "4532756279624064");

    assertThat(payment.getOrder().isPaid()).isTrue();
    assertThat(payment.getCreditCardNumber()).isEqualTo("4532756279624064");
  }
}
