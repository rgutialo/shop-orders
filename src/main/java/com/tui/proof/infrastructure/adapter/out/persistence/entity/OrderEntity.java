package com.tui.proof.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

/** Order entity */
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ORDERS")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "pilotes")
    private int pilotes;

    @Column(name = "total")
    private double orderTotal;

    @Column(name = "creation_date", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime creationDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity client;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "address_id", nullable = false)
    private AddressEntity deliveryAddress;

    @Builder
    public OrderEntity(
            final int id,
            final int pilotes,
            final double orderTotal,
            final LocalDateTime creationDate,
            final ClientEntity client,
            final AddressEntity address) {
        this.id = id;
        this.pilotes = pilotes;
        this.orderTotal = orderTotal;
        this.creationDate = creationDate;
        this.client = client;
        this.deliveryAddress = address;
    }
}
