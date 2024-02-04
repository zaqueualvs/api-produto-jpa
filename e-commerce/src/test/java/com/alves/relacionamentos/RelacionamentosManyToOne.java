package com.alves.relacionamentos;

import com.alves.EntityManagerTest;
import com.alves.models.Cliente;
import com.alves.models.Pedido;
import com.alves.models.enums.StatusPedido;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class RelacionamentosManyToOne extends EntityManagerTest {

    @Test
    public void verificarRelacionamento() {
        Cliente cliente = entityManager.find(Cliente.class, 1);
        Pedido pedido = new Pedido();
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setDataPedido(OffsetDateTime.now());
        pedido.setTotal(BigDecimal.TEN);

        pedido.setCliente(cliente);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificar = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertNotNull(pedidoVerificar.getCliente());
    }
}
