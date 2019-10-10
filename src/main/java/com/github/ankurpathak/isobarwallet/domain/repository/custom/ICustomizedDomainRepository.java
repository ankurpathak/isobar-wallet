package com.github.ankurpathak.isobarwallet.domain.repository.custom;

import com.github.ankurpathak.isobarwallet.domain.model.Domain;

import java.io.Serializable;

public interface ICustomizedDomainRepository<T extends Domain<ID>, ID extends Serializable> {


    /*
    Page<T> findAllPaginated(Pageable pageable, Class<T> type);
    long countByCriteria(Criteria criteria, Class<T> type);
    List<T> findByCriteria(Criteria criteria, Pageable pageable, Class<T> type);
    Page<T> findByCriteriaPaginated(Criteria criteria, Pageable pageable, Class<T> type);

    */
}
