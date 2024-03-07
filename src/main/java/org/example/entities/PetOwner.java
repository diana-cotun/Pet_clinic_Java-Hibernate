package org.example.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class PetOwner {
    @Id
    @GeneratedValue
    private Integer petOwnerId;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String phoneNumber;
    @OneToMany(mappedBy = "petOwner", orphanRemoval = true)
    private List<Pet> petList = new ArrayList<>();


    public List<Pet> getPetList() {
        return petList;
    }

    public void setPetList(List<Pet> petList) {
        this.petList = petList;
    }

    public Integer getPetOwnerId() {
        return petOwnerId;
    }

    public void setPetOwnerId(Integer petOwnerId) {
        this.petOwnerId = petOwnerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
