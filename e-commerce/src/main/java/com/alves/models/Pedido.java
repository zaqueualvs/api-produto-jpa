package com.alves.models;

import com.alves.models.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itemPedidos = new ArrayList<>();
    @OneToOne(mappedBy = "pedido")
    private PagamentoCartao pagamentoCartao;
    @OneToOne(mappedBy = "pedido")
    private NotaFiscal notaFiscal;
    private OffsetDateTime dataPedido;
    private OffsetDateTime dataConclusao;
    @Enumerated(EnumType.STRING)
    private StatusPedido status;
    private BigDecimal total;
    @Embedded
    private EnderecoEntregaPedido enderecoEntrega;
}
