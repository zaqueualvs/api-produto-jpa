package com.alves.models;

import com.alves.models.enums.StatusPagamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "pagamento_boleto")
public class PagamentoBoleto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Pedido pedido;
    @Enumerated(EnumType.STRING)
    private StatusPagamento status;
    private String codigoBarras;
}
