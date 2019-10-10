package com.github.ankurpathak.isobarwallet.service.impl;

import com.github.ankurpathak.isobarwallet.domain.model.Account;
import com.github.ankurpathak.isobarwallet.domain.model.AccountLedger;
import com.github.ankurpathak.isobarwallet.domain.model.AccountLedger_;
import com.github.ankurpathak.isobarwallet.domain.repository.IAccountRepository;
import com.github.ankurpathak.isobarwallet.domain.repository.specs.AccountLedgerSpecs;
import com.github.ankurpathak.isobarwallet.exception.InvalidException;
import com.github.ankurpathak.isobarwallet.exception.NotFoundException;
import com.github.ankurpathak.isobarwallet.service.IAccountLedgerService;
import com.github.ankurpathak.isobarwallet.service.IAccountService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class AccountService extends AbstractDomainService<Account, BigInteger> implements IAccountService {
    private final IAccountLedgerService accountLedgerService;

    protected AccountService(IAccountRepository dao, IAccountLedgerService accountLedgerService) {
        super(dao);
        this.accountLedgerService = accountLedgerService;
    }

    @Override
    @Transactional
    public void addFunds(BigInteger id, BigDecimal amount) {
        if (BigDecimal.ZERO.compareTo(amount) < 0) {
            findById(id).map(x -> x.addFunds(amount)).map(this::update).ifPresentOrElse(x -> {
                accountLedgerService.create(AccountLedger.getInstance().account(x)
                        .amount(amount)
                );
            }, () -> {
                throw new NotFoundException(String.valueOf(id), "id", Account.class.getSimpleName());
            });
        } else {
            throw new InvalidException("amount", String.valueOf(amount));
        }
    }

    @Override
    @Transactional
    public void removeFunds(BigInteger id, BigDecimal amount) {removeFunds(id, amount, null);}

    @Override
    @Transactional
    public void removeFunds(BigInteger id, BigDecimal amount, String note) {
        if (BigDecimal.ZERO.compareTo(amount) < 0) {
            findById(id)
                    .map(x -> {
                        if (amount.compareTo(x.getAmount()) < 0)
                            return x;
                        else throw new InvalidException("amount", String.valueOf(amount));
                    })
                    .map(x -> x.removeFunds(amount)).map(this::update).ifPresentOrElse(x -> {
                accountLedgerService.create(AccountLedger.getInstance().account(x)
                        .type(AccountLedger.AccountLedgerEntryType.DEBIT)
                        .note(note)
                        .amount(amount)
                );
            }, () -> {
                throw new NotFoundException(String.valueOf(id), "id", Account.class.getSimpleName());
            });
        } else {
            throw new InvalidException("amount", String.valueOf(amount));
        }
    }

    @Override
    public List<AccountLedger> recentAccountLedger(BigInteger id) {
        return findById(id)
                .map(account ->
                        accountLedgerService.findAll(AccountLedgerSpecs.accountLedgerOfAccount(account), PageRequest.of(0, 30, Sort.by(Sort.Order.desc(AccountLedger_.CREATED)))))
                .map(Page::getContent)
                .orElseThrow(() -> new NotFoundException(String.valueOf(id), "id", Account.class.getSimpleName()));
    }

    @Override
    public Object accountLedgerInInterval(BigInteger id, Instant fromDate, Instant toDate) {
        return findById(id)
                .map(account ->
                        accountLedgerService.findAll(AccountLedgerSpecs.accountLedgerOfAccountInInterval(account, fromDate, toDate), Sort.by(Sort.Order.desc(AccountLedger_.CREATED))))
                .orElseThrow(() -> new NotFoundException(String.valueOf(id), "id", Account.class.getSimpleName()));
    }
}
