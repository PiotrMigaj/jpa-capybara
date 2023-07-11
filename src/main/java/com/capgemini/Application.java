package com.capgemini;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.capgemini.capybara.Capybara;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Application {
    private static Logger logger = LogManager.getLogger(Application.class);
    private static EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Capybara capybara1 = new Capybara();
        Capybara capybara2 = new Capybara();
        Capybara capybara3 = new Capybara();

        logger.info("Before commit:");
        logger.info("{}",capybara1);
        logger.info("{}",capybara2);
        logger.info("{}",capybara3);

//        capybara2.setId(0L);
        Capybara capybara1afterMerge = em.merge(capybara1);
        Capybara capybara2afterMerge = em.merge(capybara2);
        Capybara capybara3afterMerge = em.merge(capybara3);

        em.getTransaction().commit();

        logger.info("After commit:");
        logger.info("{}",capybara1afterMerge);
        logger.info("{}",capybara2afterMerge);
        logger.info("{}",capybara3afterMerge);

        em.close();
    }
}
