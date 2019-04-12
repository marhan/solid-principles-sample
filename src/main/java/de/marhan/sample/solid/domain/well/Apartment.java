package de.marhan.sample.solid.domain.well;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "apartment")
@Data
public class Apartment {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "apartmentId")
    private UUID apartmentId;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ApartmentStatus status;

    public void reserve() {
        setStatus(ApartmentStatus.reserved);
    }

    public void rent() {
        setStatus(ApartmentStatus.rented);
    }

    public void cancel() {
        setStatus(ApartmentStatus.free);
    }
}
