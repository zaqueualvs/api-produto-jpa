package com.alves.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "categoria")
public class Categoria extends EntidadeBaseLong{

    private String nome;

    @ManyToOne
    @JoinColumn(name = "categoria_pai_id")
    private Categoria categoriaPai;
    @OneToMany(mappedBy = "categoriaPai")
    private List<Categoria> categorias = new ArrayList<>();
    @ManyToMany(mappedBy = "categorias")
    private List<Produto> produtos = new ArrayList<>();
}
