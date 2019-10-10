package com.github.ankurpathak.isobarwallet.domain.repository;

import com.github.ankurpathak.isobarwallet.domain.model.Account;
import com.github.ankurpathak.isobarwallet.domain.repository.custom.CustomAccountRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigInteger;

public interface IAccountRepository extends ExtendedJpaRepository<Account, BigInteger>, CustomAccountRepository {
}
