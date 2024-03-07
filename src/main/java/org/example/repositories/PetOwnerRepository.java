package org.example.repositories;

import org.example.entities.PetOwner;
import org.example.entities.Vet;
import org.example.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class PetOwnerRepository extends Repository<PetOwner>{

    public Optional<PetOwner> findByPhoneNumber(String phoneNumber) {
        Session session = sessionFactory.openSession();
        Query<PetOwner> query = session.createQuery("select p from PetOwner p where p.phoneNumber = :phoneNumber");
        query.setParameter("phoneNumber", phoneNumber);
        Optional<PetOwner> petOwner = query.uniqueResultOptional();
        session.close(); // unde inchidem sisiunea; cum putem lucra cu mai multe tranzactii ???
        return petOwner;
    }


    public List<PetOwner> getAllPetOwners() {
        Session session = sessionFactory.openSession();
        Query<PetOwner> query = session.createQuery("select p from PetOwner p");
        return query.list();
    }


//   clasa abstracta cu generic (save, update si delete);


}
