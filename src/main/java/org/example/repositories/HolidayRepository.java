package org.example.repositories;

import org.example.entities.Appointment;
import org.example.entities.Holiday;
import org.example.entities.Vet;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class HolidayRepository extends Repository<Holiday>{

    public List<Holiday> findHolidaysByVet(Vet vet){
        Session session = sessionFactory.openSession();
        Query<Holiday> query = session.createQuery("select h from Holiday h where h.vet = :vet");
        query.setParameter("vet", vet);
        List<Holiday> holidays = query.list();
        session.close();
        return holidays;
    }

}
