package com.tui.proof.infrastructure.adapter.out.persistence.repository;

import com.tui.proof.infrastructure.adapter.out.persistence.entity.OrderEntity;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/** Manages the Order I/O operations */
public interface IOrderRepo extends CrudRepository<OrderEntity, Integer> {

    @Query(
            "SELECT o FROM OrderEntity o, ClientEntity c WHERE o.client.clientId = c.clientId "
                    + "AND (c.firstName LIKE %:clientData% OR c.lastName LIKE %:clientData% OR c.telephone LIKE %:clientData%)")
    List<OrderEntity> findByClientData(final @Param("clientData") String clientData);
}
