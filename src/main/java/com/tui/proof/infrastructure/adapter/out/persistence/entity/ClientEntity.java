package com.tui.proof.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

/** Client entity */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "CLIENTS")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clientId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "telephone")
    private String telephone;

    @Builder
    public ClientEntity(
            final int clientId,
            final String firstName,
            final String lastName,
            final String telephone) {
        this.clientId = clientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
    }
}
