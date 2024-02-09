package com.alves.models;

import com.alves.models.enums.SexoCliente;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.OffsetTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "cliente", uniqueConstraints = {@UniqueConstraint(
        name = "unq_cpf", columnNames = {"cpf"})},
        indexes = {@Index(name = "idx_nome", columnList = "nome")})
@AllArgsConstructor
@NoArgsConstructor
@SecondaryTable(name = "cliente_detalhe", pkJoinColumns = @PrimaryKeyJoinColumn(name = "cliente_id"))
@Setter
@Getter
public class Cliente extends EntidadeBaseLong {

    private String nome;
    private String cpf;
    @Transient
    private String primeiroNome;

    @Column(table = "cliente_detalhe")
    @Enumerated(EnumType.STRING)
    private SexoCliente sexo;
    @Column(name = "data_nascimento", table = "cliente_detalhe")
    private LocalDate dataNascimento;
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos = new ArrayList<>();
    @ElementCollection
    @CollectionTable(name = "cliente_contato",
            joinColumns = @JoinColumn(name = "cliente_id"))
    @MapKeyColumn(name = "tipo")
    @Column(name = "descricao")
    private Map<String, String> contatos;

    @PostLoad
    public void configurarPrimeiroNome() {
        if (!nome.isBlank()) {
            int index = nome.indexOf(" ");
            if (index > -1) {
                primeiroNome = nome.substring(0, index);
                System.out.println(primeiroNome);
            }
        }
    }
}
