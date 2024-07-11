package org.example.service;

import org.example.config.HibernateUtil;
import org.example.model.Ticket;
import org.example.model.Client;
import org.example.model.Planet;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class TicketCrudService {

    public List<Ticket> findAll() {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            return session.createQuery("from Ticket", Ticket.class).list();
        }
    }

    public Optional<Ticket> findById(Long id) {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(Ticket.class, id));
        }
    }

    public void save(Ticket ticket) {
        validateTicket(ticket);

        Transaction transaction = null;
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(ticket);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public void deleteById(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Ticket ticket = session.get(Ticket.class, id);
            if (ticket != null) {
                session.delete(ticket);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    private void validateTicket(Ticket ticket) {
        if (ticket.getClient() == null || !existsClientById(ticket.getClient().getId())) {
            throw new IllegalArgumentException("Invalid client");
        }
        if (ticket.getFromPlanet() == null || !existsPlanetById(ticket.getFromPlanet().getId())) {
            throw new IllegalArgumentException("Invalid departure planet");
        }
        if (ticket.getToPlanet() == null || !existsPlanetById(ticket.getToPlanet().getId())) {
            throw new IllegalArgumentException("Invalid arrival planet");
        }
    }

    private boolean existsClientById(Long clientId) {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            return session.get(Client.class, clientId) != null;
        }
    }

    private boolean existsPlanetById(String planetId) {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            return session.get(Planet.class, planetId) != null;
        }
    }
}