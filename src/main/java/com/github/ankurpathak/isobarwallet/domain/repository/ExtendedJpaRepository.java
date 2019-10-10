package com.github.ankurpathak.isobarwallet.domain.repository;

import com.github.ankurpathak.isobarwallet.domain.model.AccountLedger;
import com.github.ankurpathak.isobarwallet.domain.model.Domain;
import com.github.ankurpathak.isobarwallet.domain.repository.custom.ICustomizedDomainRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface ExtendedJpaRepository<T extends Domain<ID>, ID extends Serializable> extends JpaRepository<T, ID>, ICustomizedDomainRepository<T, ID>, JpaSpecificationExecutor<T> {
}
