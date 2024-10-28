package org.hugo.practicahibernatecoches.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hugo.practicahibernatecoches.model.Coche;
import java.util.ArrayList;
import java.util.List;


public class CocheDAOImpl implements CocheDAO{

    public boolean guardarCoche(Coche coche, Session session) {
        Transaction transaction = null;

        try{
            transaction = session.beginTransaction();
            session.save(coche);
            transaction.commit();

        } catch (Exception e) {
            if(transaction != null)
                transaction.rollback();
            return false;

        }

        return true;
    }

    public boolean actualizarCoche(Coche coche, Session session) {
        Transaction transaction = null;

        try{
            transaction = session.beginTransaction();
            session.saveOrUpdate(coche);
            transaction.commit();

        } catch (Exception e) {
            if(transaction != null)
                transaction.rollback();
            return false;

        }

        return true;
    }

    public void eliminarCoche(Coche coche, Session session) {
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Coche c = session.get(Coche.class, coche.getId());
            session.delete(c);
            transaction.commit();

        } catch (Exception e) {
            if(transaction != null)
                transaction.rollback();

        }
    }

    @SuppressWarnings("unchecked")
    public List<Coche> listarCoches(Session session) {
        Transaction transaction = null;
        List<Coche> coches = new ArrayList<>();

        try {
            transaction = session.beginTransaction();
            coches = session.createQuery("from coches").list();
            transaction.commit();

        } catch (Exception e) {
            if(transaction != null)
                transaction.rollback();
        }


        return coches;
    }
}
