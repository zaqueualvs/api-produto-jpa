package com.alves.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ItemPedidoId implements Serializable {

    @EqualsAndHashCode.Include
    private Long pedidoId;
    @EqualsAndHashCode.Include
    private Long produtoId;
}
