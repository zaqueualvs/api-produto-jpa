package com.alves.conhecendoentitymanager;
import com.alves.EntityManagerTest;
import com.alves.models.Pedido;
import com.alves.models.enums.StatusPedido;
import org.junit.Test;

public class GerenciamentoTransacoesTest extends EntityManagerTest {

    @Test(expected = Exception.class)
    public void abrirFecharCancelarTransacao() {
        try {
            entityManager.getTransaction().begin();
            metodoDeNegocio();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    private void metodoDeNegocio() {
        Pedido pedido = entityManager.find(Pedido.class, 1);
        pedido.setStatus(StatusPedido.PAGO);

        if (pedido.getPagamento() == null) {
            throw new RuntimeException("Pedido ainda não foi pago.");
        }
    }
}
