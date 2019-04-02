package com.procourier.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import static java.util.Objects.requireNonNull;

@Entity
@Table(name="sellers")
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    public Seller() {
    }

    public Seller(String name, String description, Address address) {
        this.name = requireNonNull(name);
        this.description = requireNonNull(description);
        this.address = requireNonNull(address);
    }

    public Seller(String name, Address address) {
        this(name, "", address);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", address=" + address +
                '}';
    }
}
