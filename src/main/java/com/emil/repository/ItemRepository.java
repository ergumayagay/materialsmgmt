package com.emil.repository;

import org.springframework.data.repository.CrudRepository;

import com.emil.domain.Item;
public interface ItemRepository extends CrudRepository<Item, Long> {

}
