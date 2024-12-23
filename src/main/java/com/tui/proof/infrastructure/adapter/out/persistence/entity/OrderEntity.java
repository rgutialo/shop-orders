package com.tui.proof.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Order entity
 */
@Entity
@Getter
@Builder
@Table(name= "ORDERS")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="pilotes")
    private int pilotes;

    @Column(name="total")
    private double orderTotal;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity client;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "address_id", nullable = false)
    private AddressEntity deliveryAddress;
}
