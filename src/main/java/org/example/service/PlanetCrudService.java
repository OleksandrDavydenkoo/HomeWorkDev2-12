package org.example.service;

import org.example.model.Planet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class PlanetCrudService {
    private final SessionFactory sessionFactory;

    public PlanetCrudService() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void create(Planet planet) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(planet);
        transaction.commit();
        session.close();
    }

    public Planet findById(String id) {
        Session session = sessionFactory.openSession();
        Planet planet = session.get(Planet.class, id);
        session.close();
        return planet;
    }

    public void update(Planet planet) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(planet);
        transaction.commit();
        session.close();
    }

    public void delete(String id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Planet planet = session.get(Planet.class, id);
        if (planet != null) {
            session.delete(planet);
        }
        transaction.commit();
        session.close();
    }

    public boolean existsById(String id) {
        Session session = sessionFactory.openSession();
        Planet planet = session.get(Planet.class, id);
        session.close();
        return planet != null;
    }
}