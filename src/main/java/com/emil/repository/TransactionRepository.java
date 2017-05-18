package com.emil.repository;

import org.springframework.data.repository.CrudRepository;

import com.emil.domain.Transaction;
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

}
