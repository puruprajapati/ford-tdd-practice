package com.example.fordtdd2.ref;

import accounts.domain.Account;
import accounts.repositories.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTest {
  @Autowired
  private TestEntityManager em;
  @Autowired
  private AccountRepository acRepository;

  @Before
  public void setUp(){

  }

  @Test
  public void whenFindByAccountHolder_thenReturnAccount(){
    // given
    Account ac = new Account("1", 20000, "Puru Prajapati");
    em.persist(ac);
    em.flush();
    // when
    Account foundAccount = acRepository.findByAccountHolder(ac.getAccountHolder());
    // then
    assertThat(foundAccount.getAccountHolder()).isEqualTo(ac.getAccountHolder());

  }

  @Test
  public void whenFindByAccountNumber_thenReturnAccount(){
    Account ac = new Account("1", 20000, "Puru Prajapati");
    em.persist(ac);
    em.flush();
    Account foundAccount = acRepository.findByAccountNumber(ac.getAccountNumber());
    assertThat(foundAccount.getAccountNumber()).isEqualTo(ac.getAccountNumber());
  }

  @Test
  public void whenFindAccountHavingBalanceGreaterThan1000_thenReturnAccount(){
    Account ac = new Account("1", 20000, "Puru Prajapati");
    em.persist(ac);
    em.flush();
    List<Account> foundAccounts = acRepository.findAllAccountBalanceGreaterThan(1000);
    assertThat(foundAccounts.stream().findFirst().get().getAccountNumber()).isEqualTo(ac.getAccountNumber());
  }
}
