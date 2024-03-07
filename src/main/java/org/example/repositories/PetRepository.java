package org.example.repositories;

import org.example.entities.Pet;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class PetRepository extends Repository<Pet> {

    public List<Pet> findAllPets() {
        Session session = sessionFactory.openSession();
        Query<Pet> query = session.createQuery("select p from Pet p");
        return query.list();
    }

    public Optional<Pet> findByCollarId(String collarId) {
        Session session = sessionFactory.openSession();
        Query<Pet> query = session.createQuery("select p from Pet p where p.collarId = :collarId");
        query.setParameter("collarId", collarId);
        Optional<Pet> optionalPet = query.uniqueResultOptional();
        session.close();
        return optionalPet;
    }

    public List<Pet> findByOwnerPhoneNumber(String phoneNumber) {
        Session session = sessionFactory.openSession();
        Query<Pet> query = session.createQuery("select p from Pet p where p.petOwner.phoneNumber = :phoneNumber");
        query.setParameter("phoneNumber", phoneNumber);
        return query.list();
    }












}
