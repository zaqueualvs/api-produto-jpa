package com.alves.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.logging.XMLFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "nota_fiscal")
public class NotaFiscal extends EntidadeBaseLong{
    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
    @Lob
    private byte[] xml;
    private OffsetDateTime dataEmissao;
}
