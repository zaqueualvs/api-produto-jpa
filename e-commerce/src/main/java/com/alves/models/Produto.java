package com.alves.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "produto", uniqueConstraints = {
        @UniqueConstraint(name = "uniq_nome", columnNames = {"nome"})},
        indexes = {@Index(name = "idx_nome", columnList = "nome")})
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Produto extends EntidadeBaseLong {
    private String nome;
    @Column(name = "codigo_barra")
    private String codigoBarra;
    @Column(name = "data_criacao", updatable = false)
    private OffsetDateTime dataCriacao;
    @Column(name = "data_atualizacao", insertable = false)
    private OffsetDateTime dataAtualizacao;
    @Lob
    private String descricao;
    @Column(precision = 19, scale = 2)
    private BigDecimal preco;
    @Lob
    private byte[] foto;

    @ManyToMany(cascade = CascadeType.ALL)
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
