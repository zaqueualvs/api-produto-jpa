package com.alves;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.*;

public class EntityManagerTest {
    protected static EntityManagerFactory entityManagerFactory;
    protected EntityManager entityManager;

    @BeforeClass
    public static void setUpBeforeClass() {
        entityManagerFactory = Persistence.createEntityManagerFactory("test");
    }
    @AfterClass
    public static void tearDownAfterClass() {
        entityManagerFactory.close();
    }

    @Before
    public void setUp(){
        entityManager = entityManagerFactory.createEntityManager();
    }
    @After
    public void tearDown(){
        entityManager.close();
    }
}
