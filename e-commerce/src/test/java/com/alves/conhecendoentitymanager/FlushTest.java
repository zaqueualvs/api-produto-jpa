package com.alves.conhecendoentitymanager;
import com.alves.EntityManagerTest;
import com.alves.models.Pedido;
import com.alves.models.enums.StatusPedido;
import org.junit.Assert;
import org.junit.Test;

public class FlushTest extends EntityManagerTest {

    @Test(expected = Exception.class)
    public void chamarFlush() {
        try {
            entityManager.getTransaction().begin();

            Pedido pedido = entityManager.find(Pedido.class, 1);
            pedido.setStatus(StatusPedido.PAGO);

            entityManager.flush();

            if (pedido.getPagamentoCartao() == null) {
                throw new RuntimeException("Pedido ainda não foi pago.");
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
}
