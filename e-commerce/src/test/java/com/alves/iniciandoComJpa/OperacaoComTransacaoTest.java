package com.alves.iniciandoComJpa;

import com.alves.EntityManagerTest;
import com.alves.models.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class OperacaoComTransacaoTest extends EntityManagerTest {

    @Test
    public void impedirOperacaoComBancoDeDados() {
        Produto produto = new Produto(1L, "Livro digital", "Livro digital pdf", new BigDecimal(50));
        entityManager.detach(produto);
        entityManager.getTransaction().begin();
        entityManager.merge(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();
        Produto produtoVerificado = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(produtoVerificado);
    }

    @Test
    public void inserirObjetoComMerge() {
        Produto produto = new Produto(null, "Cãmera Canon", "Melhor definição de fotos", new BigDecimal(500));
        entityManager.getTransaction().begin();
        entityManager.merge(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();
        Produto produtoVerificado = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(produtoVerificado);
    }

    @Test
    public void atualizarObjetoGerenciado() {
        Produto produto = entityManager.find(Produto.class, 1);
        produto.setNome("livro digital");
        entityManager.getTransaction().begin();
        entityManager.merge(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();
        Produto produtoVerificado = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(produtoVerificado);
    }

    @Test
    public void atualizarObjeto() {
        Produto produto = new Produto(1L, "Livro digital", "Livro digital pdf", new BigDecimal(50));

        entityManager.getTransaction().begin();
        entityManager.merge(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();
        Produto produtoVerificado = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(produtoVerificado);
    }

    @Test
    public void removerObjeto() {
        Produto produto = entityManager.find(Produto.class, 3);

        entityManager.getTransaction().begin();
        entityManager.remove(produto);
        entityManager.getTransaction().commit();
        Produto produtoVeficacao = entityManager.find(Produto.class, 3);
        Assert.assertNull(produtoVeficacao);
    }

    @Test
    public void inserirObjeto() {
        Produto produto = new Produto(null, "Cãmera Canon", "Melhor definição de fotos", new BigDecimal(500));
        entityManager.getTransaction().begin();
        entityManager.persist(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();
        Produto produtoVerificado = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(produtoVerificado);
    }
}
