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
@DiscriminatorValue("cartao")
public class PagamentoCartao extends Pagamento{

    @Column(name = "numero_cartao")
    private String numeroCartao;
}
