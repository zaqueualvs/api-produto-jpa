package com.alves.models;

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
@Table(name = "estoque")
public class Estoque extends EntidadeBaseLong{

    @OneToOne(optional = false)
    @JoinColumn(name = "produto_id")
    private Produto produto;
    private Long quantidade;
}
