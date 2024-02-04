package com.alves.mapeamentoBasico;

import com.alves.EntityManagerTest;
import com.alves.iniciandoComJpa.ClienteTest;
import com.alves.models.Cliente;
import com.alves.models.enums.SexoCliente;
import org.junit.Assert;
import org.junit.Test;

public class MapeamentoEnumerador extends EntityManagerTest {

    @Test
    public void testarEnum() {
        Cliente cliente = new Cliente(4L, "Paulo", SexoCliente.MASCULINO);
        entityManager.getTransaction().begin();
        entityManager.merge(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();
        Cliente clientePersistido = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertNotNull(clientePersistido);
    }
}
