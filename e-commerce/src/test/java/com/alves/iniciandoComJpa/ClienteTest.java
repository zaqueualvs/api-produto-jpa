package com.alves.iniciandoComJpa;

import com.alves.EntityManagerTest;
import com.alves.models.Cliente;
import com.alves.models.enums.SexoCliente;
import org.junit.Assert;
import org.junit.Test;

public class ClienteTest extends EntityManagerTest {

    @Test
    public void findByid() {
        Cliente cliente = entityManager.find(Cliente.class, 1);
        Assert.assertNotNull(cliente);
        Assert.assertEquals("João Silva", cliente.getNome());
    }

    @Test
    public void save() {
        Cliente cliente = new Cliente(null, "Maria", SexoCliente.MASCULINO);
        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();
        entityManager.clear();
        Cliente clientePersistido = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertEquals("Maria", clientePersistido.getNome());
    }

    @Test
    public void update() {
        Cliente cliente = entityManager.find(Cliente.class, 1);
        entityManager.getTransaction().begin();
        cliente.setNome("Zé Moca");
        entityManager.merge(cliente);
        entityManager.getTransaction().commit();
        entityManager.clear();
        Cliente clienteAtualizado = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertEquals("Zé Moca", clienteAtualizado.getNome());
    }

    @Test
    public void delete() {
        Cliente cliente = entityManager.find(Cliente.class, 1);
        entityManager.getTransaction().begin();
        entityManager.remove(cliente);
        entityManager.getTransaction().commit();
        entityManager.clear();
        Cliente clienteDeletado = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertNull(clienteDeletado);
    }

}
