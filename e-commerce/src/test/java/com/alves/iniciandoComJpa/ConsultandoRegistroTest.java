package com.alves.iniciandoComJpa;

import com.alves.EntityManagerTest;
import com.alves.models.Produto;
import org.junit.Assert;
import org.junit.Test;

public class ConsultandoRegistroTest extends EntityManagerTest {

    @Test
    public void buscarPorId(){
        Produto produto = entityManager.find(Produto.class, 1);
        System.out.println("q1"+ produto.getId());
        Assert.assertNotNull(produto);
        Assert.assertEquals("Kindle", produto.getNome());
    }

    @Test
    public void atualizarAReferencia() {
        Produto produto = entityManager.find(Produto.class, 1);
        produto.setNome("Microfone Samson");

        entityManager.refresh(produto);

        Assert.assertEquals("Kindle", produto.getNome());
    }
}
