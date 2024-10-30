package org.hugo.practicahibernatecoches.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hugo.practicahibernatecoches.model.Coche;
import org.hugo.practicahibernatecoches.util.AlertUtil;

import java.util.ArrayList;
import java.util.List;


public class DAOImpl implements DAO<Coche> {

    @Override
    public boolean guardar(Coche elemento, Session session) {
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(elemento);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();

            AlertUtil.mostrarError(e.getCause().getMessage());

            return false;

        }

        return true;
    }

    @Override
    public void actualizar(Coche elemento, Session session) {
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(elemento);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();

            AlertUtil.mostrarError(e.getCause().getMessage());

        }
    }

    @Override
    public void eliminar(Coche elemento, Session session) {
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Coche c = session.get(Coche.class, elemento.getId());
            session.delete(c);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();

        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Coche> listar(Session session) {
        Transaction transaction = null;
        List<Coche> coches = new ArrayList<>();

        try {
            transaction = session.beginTransaction();
            coches = session.createQuery("from Coche").list();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        }

        return coches;
    }
}