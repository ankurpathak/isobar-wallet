package com.github.ankurpathak.isobarwallet.domain.repository.custom.impl;

import com.github.ankurpathak.isobarwallet.domain.model.Account;
import com.github.ankurpathak.isobarwallet.domain.repository.custom.CustomAccountRepository;
import com.github.ankurpathak.isobarwallet.domain.repository.custom.ICustomizedDomainRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.math.BigInteger;

@Repository
public class CustomAccountRepositoryImpl extends AbstractCustomizedDomainRepository<Account, BigInteger> implements ICustomizedDomainRepository<Account, BigInteger>,  CustomAccountRepository {
    public CustomAccountRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }


}
