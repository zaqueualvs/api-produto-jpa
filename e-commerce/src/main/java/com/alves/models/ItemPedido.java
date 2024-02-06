package com.alves.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "item_pedido")
public class ItemPedido {

    @EmbeddedId
    private ItemPedidoId id;
    @MapsId("pedidoId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
    @MapsId("produtoId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "produto_id")
    private Produto produto;
    @Column(name = "preco_produto")
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
