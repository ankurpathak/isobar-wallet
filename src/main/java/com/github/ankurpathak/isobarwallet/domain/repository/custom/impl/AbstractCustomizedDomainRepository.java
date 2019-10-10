package com.github.ankurpathak.isobarwallet.domain.repository.custom.impl;

import com.github.ankurpathak.isobarwallet.domain.model.Domain;
import com.github.ankurpathak.isobarwallet.domain.repository.custom.ICustomizedDomainRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;

public abstract class AbstractCustomizedDomainRepository<T extends Domain<ID>, ID extends Serializable> implements ICustomizedDomainRepository<T, ID> {
    protected final EntityManager entityManager;

    protected AbstractCustomizedDomainRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
