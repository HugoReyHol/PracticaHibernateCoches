package org.hugo.practicahibernatecoches.dao;

import org.hibernate.Session;
import org.hugo.practicahibernatecoches.model.Coche;
import java.util.List;


public interface CocheDAO {

    boolean guardarCoche(Coche coche, Session session);

    boolean actualizarCoche(Coche coche, Session session);

    void eliminarCoche(Coche coche, Session session);

    List<Coche> listarCoches(Session session);

}
