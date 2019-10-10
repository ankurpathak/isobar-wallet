package com.github.ankurpathak.isobarwallet.service.impl;

import com.github.ankurpathak.isobarwallet.domain.model.AccountLedger;
import com.github.ankurpathak.isobarwallet.domain.repository.IAccountLedgerRepository;
import com.github.ankurpathak.isobarwallet.service.IAccountLedgerService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class AccountLedgerService extends  AbstractDomainService<AccountLedger, BigInteger> implements IAccountLedgerService {
    protected AccountLedgerService(IAccountLedgerRepository dao) {
        super(dao);
    }
}
