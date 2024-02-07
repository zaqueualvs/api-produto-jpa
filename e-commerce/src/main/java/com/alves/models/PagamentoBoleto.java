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
@DiscriminatorValue("boleto")
public class PagamentoBoleto extends Pagamento{

    @Column(name = "codigo_barras")
    private String codigoBarras;
}
