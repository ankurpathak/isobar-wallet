package com.github.ankurpathak.isobarwallet.service;

import com.github.ankurpathak.isobarwallet.domain.model.Domain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface IDomainService<T extends Domain<ID>, ID extends Serializable> {

    // read - one
    Optional<T> findById(ID id);

    // read - all
    List<T> findAll();

    Page<T> findAll(Pageable pageable);

    // write

    Page<T> findAll(Specification<T> specification, Pageable pageable);
    List<T> findAll(Specification<T> specification, Sort sort);

    T create(T entity);

    T update(T entity);

    Iterable<T> createAll(Iterable<T> entities);


    void delete(T entity);

    void deleteById(ID id);


    void deleteAll(Iterable<T> domains);

    void deleteAll();

}
