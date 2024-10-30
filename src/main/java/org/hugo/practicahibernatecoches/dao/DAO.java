package org.hugo.practicahibernatecoches.dao;

import org.hibernate.Session;

import java.util.List;


public interface DAO<T> {
    boolean guardar(T elemento, Session session);

    void actualizar(T elemento, Session session);

    void eliminar(T elemento, Session session);

    List<T> listar(Session session);
}
