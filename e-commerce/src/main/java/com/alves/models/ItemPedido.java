package com.alves.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "item_pedido")
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
    @ManyToOne(optional = false)
    @JoinColumn(name = "produto_id")
    private Produto produto;
    private BigDecimal precoProduto;
    private Integer quantidade;
    private BigDecimal subTotal;

    @PrePersist
    public void aoPersistir() {
        calcularSubTotal();
    }

    @PreUpdate
    public void aoAtualizar() {
        calcularSubTotal();
    }

    public void calcularSubTotal() {
        subTotal = getPrecoProduto().multiply(new BigDecimal(quantidade));
    }
}
