package org.example.repositories;

import org.example.entities.Appointment;
import org.example.entities.Vet;
import org.example.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class AppointmentRepository extends Repository<Appointment> {

    public List<Appointment> findAppointmentsByVet(Vet vet) {
        Session session = sessionFactory.openSession();
        Query<Appointment> query = session.createQuery("select a from Appointment a where vet = :vet");
        query.setParameter("vet", vet);
        List<Appointment> appointments = query.list();
        session.close();
        return appointments;
    }


}
