package com.alves.mapeamentoavancao;

import com.alves.EntityManagerTest;
import com.alves.models.Atributo;
import com.alves.models.Cliente;
import com.alves.models.Produto;
import org.junit.Assert;
import org.junit.Test;

import javax.swing.event.CaretListener;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class ElementCollectionTest extends EntityManagerTest {

    @Test
    public void aplicarTags() {
        entityManager.getTransaction().begin();

        Produto produto = entityManager.find(Produto.class, 1);
        produto.setTags(Arrays.asList("ebook", "livro-digital"));
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVeficacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertFalse(produtoVeficacao.getTags().isEmpty());
    }

    @Test
    public void aplicarAtributos() {
        entityManager.getTransaction().begin();

        Produto produto = entityManager.find(Produto.class, 1);
        produto.setAtributos(Arrays.asList(new Atributo("tela", "720X480"), new Atributo("Cor", "Azul")));
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVeficacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertFalse(produtoVeficacao.getAtributos().isEmpty());
    }

    @Test
    public void aplicarContato() {
        entityManager.getTransaction().begin();

        Cliente cliente = entityManager.find(Cliente.class, 1);
        cliente.setContatos(Collections.singletonMap("email", "fernando@emil.com"));
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertEquals("fernando@emil.com", clienteVerificacao.getContatos().get("email"));
    }
}
