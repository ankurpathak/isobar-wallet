package com.github.ankurpathak.isobarwallet.domain.repository.custom.impl;

import com.github.ankurpathak.isobarwallet.domain.model.Domain;
import com.github.ankurpathak.isobarwallet.domain.repository.custom.ICustomizedDomainRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.hibernate5.HibernateTemplate;

import javax.persistence.EntityManager;
import java.io.Serializable;

public abstract class AbstractCustomizedDomainRepository<T extends Domain<ID>, ID extends Serializable> implements ICustomizedDomainRepository<T, ID> {
    protected final EntityManager entityManager;

    protected AbstractCustomizedDomainRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
