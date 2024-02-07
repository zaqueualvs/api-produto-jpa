package com.alves.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "produto")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Produto extends EntidadeBaseLong{
    private String nome;
    @Column(name = "data_criacao", updatable = false)
    private OffsetDateTime dataCriacao;
    @Column(name = "data_atualizacao", insertable = false)
    private OffsetDateTime dataAtualizacao;
    private String descricao;
    private BigDecimal preco;
    @Lob
    private byte[] foto;

    @ManyToMany
    @JoinTable(name = "produto_categoria",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private List<Categoria> categorias = new ArrayList<>();
    @OneToOne(mappedBy = "produto")
    private Estoque estoque;
    @ElementCollection
    @CollectionTable(name = "produto_tag",
            joinColumns = @JoinColumn(name = "produto_id"))
    @Column(name = "tag")
    private List<String> tags;
    @ElementCollection
    @CollectionTable(name = "produto_atributo",
    joinColumns = @JoinColumn(name = "produto_id"))
    private List<Atributo> atributos;
}
