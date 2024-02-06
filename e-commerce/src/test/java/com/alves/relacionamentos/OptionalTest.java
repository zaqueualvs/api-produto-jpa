package com.alves.relacionamentos;
import com.alves.EntityManagerTest;
import com.alves.models.Pedido;
import org.junit.Test;

import java.util.List;

public class OptionalTest extends EntityManagerTest {

    @Test
    public void verficarComportamento() {
        Pedido pedido = entityManager.find(Pedido.class, 1);

    }
}
