package org.example.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Holiday {
    @Id
    @GeneratedValue
    private Integer holidayId;

    private LocalDate date;

    @ManyToOne
    @JoinColumn
    private Vet vet;

//  Getters & Setters
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getHolidayId() {
        return holidayId;
    }

    public void setHolidayId(Integer holidayId) {
        this.holidayId = holidayId;
    }

    public Vet getVet() {
        return vet;
    }

    public void setVet(Vet vet) {
        this.vet = vet;
    }
}
