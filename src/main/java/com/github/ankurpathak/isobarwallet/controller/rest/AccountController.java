package com.github.ankurpathak.isobarwallet.controller.rest;

import com.github.ankurpathak.isobarwallet.controller.rest.dto.AccountDto;
import com.github.ankurpathak.isobarwallet.controller.rest.util.ControllerUtil;
import com.github.ankurpathak.isobarwallet.domain.model.Account;
import com.github.ankurpathak.isobarwallet.exception.NotFoundException;
import com.github.ankurpathak.isobarwallet.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.groups.Default;
import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @PostMapping("/account")
    public ResponseEntity<?> create(HttpServletRequest request, @RequestBody @Validated({Default.class}) AccountDto accountDto, BindingResult result){
        ControllerUtil.processValidation(result);
        var account = accountService.create(Account.getInstance(accountDto));
        return ResponseEntity.created(ServletUriComponentsBuilder.fromRequest(request).path("/{id}").build(account.getId())).build();
    }


    @GetMapping("/account/{id}")
    public ResponseEntity<?> byId(@PathVariable("id") BigInteger id){
        return ResponseEntity.ok(accountService.findById(id).orElseThrow(() -> new NotFoundException(String.valueOf(id), "id", Account.class.getSimpleName())));
    }

    @PutMapping("/account/{id}")
    public ResponseEntity<?> update(@PathVariable("id") BigInteger id, @RequestBody @Validated({Default.class}) AccountDto accountDto, BindingResult result){
        accountService.findById(id)
                .map(account -> {
                    account.address(accountDto.getAddress())
                            .name(accountDto.getName())
                            .email(accountDto.getEmail())
                            .phone(accountDto.getPhone())
                            .password(accountDto.getPassword());
                    return accountService.update(account);
                })
                .orElseThrow(() -> new NotFoundException(String.valueOf(id), "id", Account.class.getSimpleName()));
        return ResponseEntity.ok().build();
    }



    @PostMapping("/account/{id}/funds/{amount}")
    public ResponseEntity<?> addFunds(@PathVariable("id") BigInteger id, @PathVariable("amount") BigDecimal amount){
        accountService.addFunds(id,amount);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/account/{id}/funds/{amount}")
    public ResponseEntity<?> removeFunds(@PathVariable("id") BigInteger id, @PathVariable("amount") BigDecimal amount){
        accountService.removeFunds(id,amount);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/account/{id}/statement")
    public ResponseEntity<?> getRecentStatement(@PathVariable("id") BigInteger id){
       return ResponseEntity.ok(accountService.recentAccountLedger(id));
    }


    @GetMapping("/account/{id}/statement/{fromDate}/{toDate}")
    public ResponseEntity<?> getStatement(@PathVariable("id") BigInteger id, @PathVariable("fromDate")  Instant fromDate, @PathVariable("toDate")  Instant toDate){
       return ResponseEntity.ok(accountService.accountLedgerInInterval(id, fromDate, toDate));
    }


}
