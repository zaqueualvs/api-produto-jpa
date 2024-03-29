package com.alves.models;

import com.alves.listener.GenericoListener;
import com.alves.listener.GerarNotaFiscalListener;
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
@EntityListeners({GerarNotaFiscalListener.class, GenericoListener.class})
public class Pedido extends EntidadeBaseLong {

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id",
            foreignKey = @ForeignKey(name = "fk_pedido_cliente"))
    private Cliente cliente;
    @OneToMany(mappedBy = "pedido",cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<ItemPedido> itemPedidos = new ArrayList<>();
    @OneToOne(mappedBy = "pedido")
    private Pagamento pagamento;
    @OneToOne(mappedBy = "pedido")
    private NotaFiscal notaFiscal;
    @Column(name = "data_criacao", updatable = false)
    private OffsetDateTime dataCriacao;
    @Column(name = "data_atualizacao", insertable = false)
    private OffsetDateTime dataAtualizacao;
    @Column(name = "data_conclusao", insertable = false)
    private OffsetDateTime dataConclusao;
    @Enumerated(EnumType.STRING)
    private StatusPedido status;
    private BigDecimal total;
    @Embedded
    private EnderecoEntregaPedido enderecoEntrega;


    //call back
    @PrePersist
    public void aoPersistir() {
        calcularTotal();
        dataCriacao = OffsetDateTime.now();
    }

    @PreUpdate
    public void aoAtualizar() {
        calcularTotal();
        dataAtualizacao = OffsetDateTime.now();
    }

    public void calcularTotal() {
        if (itemPedidos != null) {
            total = itemPedidos.stream()
                    .map(ItemPedido::getSubTotal)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

        }
    }

    public Boolean isPago() {
        return StatusPedido.PAGO.equals(status);
    }
}
