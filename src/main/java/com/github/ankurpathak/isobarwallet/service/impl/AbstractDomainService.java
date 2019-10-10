package com.github.ankurpathak.isobarwallet.service.impl;

import com.github.ankurpathak.isobarwallet.domain.model.Domain;
import com.github.ankurpathak.isobarwallet.domain.repository.ExtendedJpaRepository;
import com.github.ankurpathak.isobarwallet.service.IDomainService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public abstract class AbstractDomainService<T extends Domain<ID>, ID extends Serializable> implements IDomainService<T, ID> {
    private final ExtendedJpaRepository<T, ID> dao;
    protected AbstractDomainService(ExtendedJpaRepository<T, ID> dao) {
        this.dao = dao;
    }

    @Override
    public Optional<T> findById(final ID id) {
        if(Objects.isNull(id))
            return Optional.empty();
        return dao.findById(id);
    }

    @Override
    public List<T> findAll() {
        return dao.findAll();
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return dao.findAll(pageable);
    }

    @Override
    public Page<T> findAll(Specification<T> specification, Pageable pageable) {
        return dao.findAll(specification, pageable);
    }

    @Override
    public List<T> findAll(Specification<T> specification, Sort sort) {
        return dao.findAll(specification, sort);
    }

    @Override
    @Transactional
    public T create(T entity) {
        return dao.save(entity);
    }

    @Override
    @Transactional
    public T update(T entity) {
        return dao.save(entity);
    }

    @Override
    @Transactional
    public Iterable<T> createAll(Iterable<T> entities) {
        return dao.saveAll(entities);
    }

    @Override
    @Transactional
    public void delete(final T entity) {
        dao.delete(entity);
    }

    @Override
    @Transactional
    public void deleteById(ID id) {
        dao.deleteById(id);
    }



    @Override
    @Transactional
    public void deleteAll() {
        dao.deleteAll();
    }

    @Override
    @Transactional
    public void deleteAll(Iterable<T> domains) {
        dao.deleteAll(domains);
    }
}
