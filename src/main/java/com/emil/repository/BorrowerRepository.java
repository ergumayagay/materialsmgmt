package com.emil.repository;

import org.springframework.data.repository.CrudRepository;

import com.emil.domain.Borrower;
public interface BorrowerRepository extends CrudRepository<Borrower, Long> {

}
