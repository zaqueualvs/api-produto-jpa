package com.alves;

import com.alves.models.ItemPedido;
import com.alves.models.Pedido;
import com.alves.models.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("test");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Produto produto = entityManager.find(Produto.class, 1);
        System.out.println(produto.getNome());
        Pedido itemPedido = entityManager.find(Pedido.class, 1);
        System.out.println("r");

        entityManager.close();
        entityManagerFactory.close();
    }
}