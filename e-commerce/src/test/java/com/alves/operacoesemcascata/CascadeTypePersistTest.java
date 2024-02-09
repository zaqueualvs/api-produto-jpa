package com.alves.operacoesemcascata;
import com.alves.EntityManagerTest;
import com.alves.models.*;
import com.alves.models.enums.SexoCliente;
import com.alves.models.enums.StatusPedido;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Arrays;

public class CascadeTypePersistTest extends EntityManagerTest {

    // @Test
    public void persistirProdutoComCategoria() {
        Produto produto = new Produto();
        produto.setDataCriacao(OffsetDateTime.now());
        produto.setPreco(BigDecimal.TEN);
        produto.setNome("Fones de Ouvido");
        produto.setDescricao("A melhor qualidade de som");

        Categoria categoria = new Categoria();
        categoria.setNome("Áudio");

        produto.setCategorias(Arrays.asList(categoria)); // CascadeType.PERSIST

        entityManager.getTransaction().begin();
        entityManager.persist(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Categoria categoriaVerificacao = entityManager.find(Categoria.class, categoria.getId());
        Assert.assertNotNull(categoriaVerificacao);
    }

    // @Test
    public void persistirPedidoComItens() {
        Cliente cliente = entityManager.find(Cliente.class, 1);
        Produto produto = entityManager.find(Produto.class, 1);

        Pedido pedido = new Pedido();
        pedido.setDataCriacao(OffsetDateTime.now());
        pedido.setCliente(cliente);
        pedido.setTotal(produto.getPreco());
        pedido.setStatus(StatusPedido.AGUARDANDO);

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setId(new ItemPedidoId());
        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);
        itemPedido.setQuantidade(1);
        itemPedido.setPrecoProduto(produto.getPreco());

        pedido.setItemPedidos(Arrays.asList(itemPedido)); // CascadeType.PERSIST

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertNotNull(pedidoVerificacao);
        Assert.assertFalse(pedidoVerificacao.getItemPedidos().isEmpty());
    }

    @Test
    public void persistirItemPedidoComPedido() {
        Cliente cliente = entityManager.find(Cliente.class, 1);
        Produto produto = entityManager.find(Produto.class, 1);

        Pedido pedido = new Pedido();
        pedido.setDataCriacao(OffsetDateTime.now());
        pedido.setCliente(cliente);
        pedido.setTotal(produto.getPreco());
        pedido.setStatus(StatusPedido.AGUARDANDO);

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setId(new ItemPedidoId());
        itemPedido.setPedido(pedido);// Não é necessário CascadeType.PERSIST porque possui @MapsId.
        itemPedido.setProduto(produto);
        itemPedido.setQuantidade(1);
        itemPedido.setPrecoProduto(produto.getPreco());

        entityManager.getTransaction().begin();
        entityManager.persist(itemPedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertNotNull(pedidoVerificacao);
    }

    // @Test
    public void persistirPedidoComCliente() {
        Cliente cliente = new Cliente();
        cliente.setDataNascimento(LocalDate.of(1980, 1, 1));
        cliente.setSexo(SexoCliente.MASCULINO);
        cliente.setNome("José Carlos");
        cliente.setCpf("01234567890");

        Pedido pedido = new Pedido();
        pedido.setDataCriacao(OffsetDateTime.now());
        pedido.setCliente(cliente); // CascadeType.PERSIST
        pedido.setTotal(BigDecimal.ZERO);
        pedido.setStatus(StatusPedido.AGUARDANDO);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertNotNull(clienteVerificacao);
    }
}
