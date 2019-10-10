package com.github.ankurpathak.isobarwallet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ankurpathak.isobarwallet.controller.rest.dto.AccountDto;
import com.github.ankurpathak.isobarwallet.domain.model.Account;
import com.github.ankurpathak.isobarwallet.domain.model.AccountLedger;
import com.github.ankurpathak.isobarwallet.service.IAccountLedgerService;
import com.github.ankurpathak.isobarwallet.service.IAccountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class AccountControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private IAccountService accountService;


    @Autowired
    private IAccountLedgerService accountLedgerService;

    @Test
    public void testAccountCreate() throws Exception{
        AccountDto accountDto = AccountDto.getInstance()
                .name("Ankur Pathak")
                .email("ankurpathak@live.in")
                .phone("+917588011779")
                .address("New Address").password("password").confirmPassword("password");
        mockMvc.perform(post("/api/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(accountDto))
        )
                .andDo(print())
                .andExpect(status().isCreated());
    }


    @Test
    public void testAccountPasswordValidationFails() throws Exception{
        AccountDto accountDto = AccountDto.getInstance()
                .name("Ankur Pathak")
                .email("ankurpathak@live.in")
                .phone("+917588011779")
                .address("New Address");
        mockMvc.perform(post("/api/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(accountDto))
        )
                .andDo(print())
                .andExpect(status().isConflict());
    }


    @Test
    public void testAccountGet() throws Exception{
        Account account = Account.getInstance()
                .name("Prateek Pathak")
                .email("prateekpathak@live.in")
                .phone("+918087715135")
                .address("New Address").password("password");
        account = accountService.create(account);
        mockMvc.perform(get("/api/account/{id}", String.valueOf(account.getId()))
        )
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    public void testAccountGetNotFound() throws Exception{
        mockMvc.perform(get("/api/account/{id}", 1000)
        )
                .andDo(print())
                .andExpect(status().isNotFound());
    }


    @Test
    public void testAccountUpdate() throws Exception{

        Account account = Account.getInstance()
                .name("Prateek Pathak")
                .email("prateekpathak@live.in")
                .phone("+918087715135")
                .address("New Address").password("password");
        account = accountService.create(account);

        AccountDto accountDto = AccountDto.getInstance()
                .name(String.format("%s New",account.getName()))
                .email(account.getEmail())
                .phone(account.getMobile())
                .address(account.getAddress()).password(account.getPassword()).confirmPassword(account.getPassword());
        mockMvc.perform(put("/api/account/{id}", String.valueOf(account.getId()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(accountDto))
        )
                .andDo(print())
                .andExpect(status().isOk());

        var result = accountService.findById(account.getId());
        assertThat(result).isNotNull();
        assertThat(result.isPresent()).isTrue();
        result.ifPresent(x -> {
            assertThat(x.getName()).isEqualTo("Prateek Pathak New");
        });
    }


    @Test
    public void testAccountAddFunds() throws Exception {

        Account account = Account.getInstance()
                .name("Prateek Pathak")
                .email("prateekpathak@live.in")
                .phone("+918087715135")
                .address("New Address").password("password");
        account = accountService.create(account);

        mockMvc.perform(post("/api/account/{id}/funds/{amount}", account.getId(), BigDecimal.valueOf(129.63))
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());


        var result = accountService.findById(account.getId());
        assertThat(result).isNotNull();
        assertThat(result.isPresent()).isTrue();
        result.ifPresent(x -> {
            assertThat(x.getAmount()).isEqualTo(BigDecimal.valueOf(129.63));
        });


        var ledger = accountLedgerService.findAll();
        assertThat(ledger).isNotEmpty();
        assertThat(accountLedgerService.findAll().get(0).getAccount()).isNotNull();
        assertThat(accountLedgerService.findAll().get(0).getAccount().getId()).isEqualTo(account.getId());
        assertThat(accountLedgerService.findAll().get(0).getAmount()).isEqualTo(BigDecimal.valueOf(129.63));
        assertThat(accountLedgerService.findAll().get(0).getType()).isEqualTo(AccountLedger.AccountLedgerEntryType.CREDIT);
    }

    @Test
    public void testAccountAddFundsNotFound() throws Exception{
        mockMvc.perform(post("/api/account/{id}/funds/{amount}", 1000,20)
        )
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void testAccountAddFundsConflict() throws Exception{

        Account account = Account.getInstance()
                .name("Prateek Pathak")
                .email("prateekpathak@live.in")
                .phone("+918087715135")
                .address("New Address").password("password");
        account = accountService.create(account);

        mockMvc.perform(post("/api/account/{id}/funds/{amount}", account.getId(), -5)
        )
                .andDo(print())
                .andExpect(status().isConflict());
    }


    @Test
    public void testAccountRemoveFundsConflict() throws Exception{

        Account account = Account.getInstance()
                .name("Prateek Pathak")
                .email("prateekpathak@live.in")
                .phone("+918087715135")
                .address("New Address").password("password");
        account = accountService.create(account);

        mockMvc.perform(delete("/api/account/{id}/funds/{amount}", account.getId(), -5)
        )
                .andDo(print())
                .andExpect(status().isConflict());
    }


    @Test
    public void testAccountRemoveFundsInsufficientBalance() throws Exception{

        Account account = Account.getInstance()
                .name("Prateek Pathak")
                .email("prateekpathak@live.in")
                .phone("+918087715135")
                .address("New Address").password("password").amount(BigDecimal.valueOf(20));
        account = accountService.create(account);

        mockMvc.perform(delete("/api/account/{id}/funds/{amount}", account.getId(), 25)
        )
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Before
    public void setUp(){
        accountLedgerService.deleteAll();
        accountService.deleteAll();
    }


    @Test
    public void testAccountRemoveFunds() throws Exception {



        Account account = Account.getInstance()
                .name("Prateek Pathak")
                .email("prateekpathak@live.in")
                .phone("+918087715135")
                .address("New Address").password("password").amount(BigDecimal.valueOf(300));
        account = accountService.create(account);

        mockMvc.perform(delete("/api/account/{id}/funds/{amount}", account.getId(), BigDecimal.valueOf(129.63))
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());


        var result = accountService.findById(account.getId());
        assertThat(result).isNotNull();
        assertThat(result.isPresent()).isTrue();
        result.ifPresent(x -> {
            assertThat(x.getAmount()).isEqualTo(BigDecimal.valueOf(300).subtract(BigDecimal.valueOf(129.63)));
        });


        var ledger = accountLedgerService.findAll();
        assertThat(ledger).isNotEmpty();
        assertThat(accountLedgerService.findAll().get(0).getAccount()).isNotNull();
        assertThat(accountLedgerService.findAll().get(0).getAccount().getId()).isEqualTo(account.getId());
        assertThat(accountLedgerService.findAll().get(0).getAmount()).isEqualTo(BigDecimal.valueOf(129.63));
        assertThat(accountLedgerService.findAll().get(0).getType()).isEqualTo(AccountLedger.AccountLedgerEntryType.DEBIT);
    }



    @Test
    public void testAccountLedgerRecent() throws Exception {

        Account account = Account.getInstance()
                .name("Prateek Pathak")
                .email("prateekpathak@live.in")
                .phone("+918087715135")
                .address("New Address").password("password").amount(BigDecimal.valueOf(1000.00));
        account = accountService.create(account);

       accountService.addFunds(account.getId(), BigDecimal.valueOf(30));
       accountService.addFunds(account.getId(), BigDecimal.valueOf(40));
       accountService.addFunds(account.getId(), BigDecimal.valueOf(50));
       accountService.addFunds(account.getId(), BigDecimal.valueOf(60));
       accountService.addFunds(account.getId(), BigDecimal.valueOf(70));
       accountService.addFunds(account.getId(), BigDecimal.valueOf(80));
       accountService.addFunds(account.getId(), BigDecimal.valueOf(90));

       accountService.removeFunds(account.getId(), BigDecimal.valueOf(30));
       accountService.removeFunds(account.getId(), BigDecimal.valueOf(40));
       accountService.removeFunds(account.getId(), BigDecimal.valueOf(50));
       accountService.removeFunds(account.getId(), BigDecimal.valueOf(60));
       accountService.removeFunds(account.getId(), BigDecimal.valueOf(70));
       accountService.removeFunds(account.getId(), BigDecimal.valueOf(80));
       accountService.removeFunds(account.getId(), BigDecimal.valueOf(90));

        mockMvc.perform(get("/api/account/{id}/statement", account.getId())
        )
                .andDo(print())
                .andExpect(status().isOk());



        var result = accountService.findById(account.getId());
        assertThat(result).isNotNull();
        assertThat(result.isPresent()).isTrue();
        result.ifPresent(x -> {
            assertThat(x.getAmount().toBigInteger()).isEqualTo(BigInteger.valueOf(1000));
        });



        var ledger = accountLedgerService.findAll();
        assertThat(ledger).isNotEmpty();

        var created = ledger.stream().map(AccountLedger::getCreated);
        assertThat(created).isSorted();
    }


    @Test
    public void testAccountLedger() throws Exception {

        Account account = Account.getInstance()
                .name("Prateek Pathak")
                .email("prateekpathak@live.in")
                .phone("+918087715135")
                .address("New Address").password("password").amount(BigDecimal.valueOf(1000.00));
        account = accountService.create(account);

        Instant start = Instant.now();

        accountService.addFunds(account.getId(), BigDecimal.valueOf(30));
        accountService.addFunds(account.getId(), BigDecimal.valueOf(40));
        accountService.addFunds(account.getId(), BigDecimal.valueOf(50));
        accountService.addFunds(account.getId(), BigDecimal.valueOf(60));
        accountService.addFunds(account.getId(), BigDecimal.valueOf(70));
        accountService.addFunds(account.getId(), BigDecimal.valueOf(80));
        accountService.addFunds(account.getId(), BigDecimal.valueOf(90));

        accountService.removeFunds(account.getId(), BigDecimal.valueOf(30));
        accountService.removeFunds(account.getId(), BigDecimal.valueOf(40));
        accountService.removeFunds(account.getId(), BigDecimal.valueOf(50));
        accountService.removeFunds(account.getId(), BigDecimal.valueOf(60));
        accountService.removeFunds(account.getId(), BigDecimal.valueOf(70));
        accountService.removeFunds(account.getId(), BigDecimal.valueOf(80));
        accountService.removeFunds(account.getId(), BigDecimal.valueOf(90));

        Instant end = Instant.now();

        mockMvc.perform(get("/api/account/{id}/statement/{fromDate}/{toDate}", account.getId(), start,end)
        )
                .andDo(print())
                .andExpect(status().isOk());



        var result = accountService.findById(account.getId());
        assertThat(result).isNotNull();
        assertThat(result.isPresent()).isTrue();
        result.ifPresent(x -> {
            assertThat(x.getAmount().toBigInteger()).isEqualTo(BigInteger.valueOf(1000));
        });



        var ledger = accountLedgerService.findAll();
        assertThat(ledger).isNotEmpty();

        var created = ledger.stream().map(AccountLedger::getCreated);
        assertThat(created).isSorted();
    }


    @Test
    public void testAccountStatementInvalidInstant() throws Exception{
        mockMvc.perform(get("/api/account/{id}/statement/{fromDate}/{toDate}", 100, "2019-10-1004:53:5","2019-10-10K")
        )
                .andDo(print())
                .andExpect(status().isConflict());
    }


    @Test
    public void testAccountGetInvalidId() throws Exception{
        mockMvc.perform(get("/api/account/{id}", "Unknown")
        )
                .andDo(print())
                .andExpect(status().isConflict());
    }


}
