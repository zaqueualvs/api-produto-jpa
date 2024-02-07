package com.alves.models;

import com.alves.models.enums.StatusPagamento;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@DiscriminatorColumn(name = "tipo_pagamento",
        discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
@Table(name = "pagamento")
public abstract class Pagamento  extends EntidadeBaseLong{
    @OneToOne(optional = false)
    @MapsId
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status;
}
