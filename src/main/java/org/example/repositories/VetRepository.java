package org.example.repositories;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.example.entities.Vet;
import org.example.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;
import java.util.Optional;

public class VetRepository extends Repository<Vet> {

    public List<Vet> getAllVets() {
        Session session = sessionFactory.openSession();
        Query<Vet> query = session.createQuery("select v from Vet v");
        List<Vet> vetList = query.list();
        session.close();
        return vetList;
    }

    public Optional<Vet> findVetByBadgeId(Integer badgeId) {
        Session session = sessionFactory.openSession();
        Query<Vet> query = session.createQuery("select v from Vet v where v.badgeId = :badgeId");
        Optional<Vet> optionalVet = query.setParameter("badgeId", badgeId).uniqueResultOptional();
        session.close();
        return optionalVet;
    }
}
