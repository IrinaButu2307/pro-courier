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
@Table(name="couriers")
public class Courier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    @OneToOne(cascade = CascadeType.ALL)
    private Address headquarters;
    private Long pricePerDistance;
    private Long pricePerWeight;

    public Courier() {
    }

    public Courier(String companyName, Address headquarters, Long pricePerDistance, Long pricePerWeight) {
        this.companyName = requireNonNull(companyName);
        this.headquarters = requireNonNull(headquarters);
        this.pricePerDistance = requireNonNull(pricePerDistance);
        this.pricePerWeight = requireNonNull(pricePerWeight);
    }

    public String getCompanyName() {
        return companyName;
    }

    public Address getHeadquarters() {
        return headquarters;
    }

    public Long getPricePerDistance() {
        return pricePerDistance;
    }

    public Long getPricePerWeight() {
        return pricePerWeight;
    }

    @Override
    public String toString() {
        return "Courier{" +
                "companyName='" + companyName + '\'' +
                ", headquarters=" + headquarters +
                ", pricePerDistance=" + pricePerDistance +
                ", pricePerWeight=" + pricePerWeight +
                '}';
    }
}
