package com.tui.proof.infrastructure.adapter.out.persistence.repository;

import com.tui.proof.infrastructure.adapter.out.persistence.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;

/** Manages the Order I/O operations */
public interface IOrderRepo extends CrudRepository<OrderEntity, Integer> {}
