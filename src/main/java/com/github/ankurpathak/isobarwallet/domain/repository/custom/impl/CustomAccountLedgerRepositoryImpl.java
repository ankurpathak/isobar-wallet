package com.github.ankurpathak.isobarwallet.domain.repository.custom.impl;

import com.github.ankurpathak.isobarwallet.domain.model.AccountLedger;
import com.github.ankurpathak.isobarwallet.domain.repository.custom.CustomAccountLedgerRepository;
import com.github.ankurpathak.isobarwallet.domain.repository.custom.ICustomizedDomainRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.math.BigInteger;

@Repository
public class CustomAccountLedgerRepositoryImpl extends AbstractCustomizedDomainRepository<AccountLedger, BigInteger> implements ICustomizedDomainRepository<AccountLedger, BigInteger>, CustomAccountLedgerRepository {
    public CustomAccountLedgerRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }
}
