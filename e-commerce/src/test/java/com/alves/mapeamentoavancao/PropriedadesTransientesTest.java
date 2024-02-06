package com.alves.mapeamentoavancao;

import com.alves.EntityManagerTest;
import com.alves.models.Cliente;
import org.junit.Assert;
import org.junit.Test;

public class PropriedadesTransientesTest extends EntityManagerTest {

    @Test
    public void validarPrimeiroNome(){
        Cliente cliente = entityManager.find(Cliente.class, 1);

        Assert.assertEquals("Fernando", cliente.getPrimeiroNome());
    }
}
