package de.marhan.sample.solid.domain.poor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "poor_apartment")
@Data
public class PoorApartment {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private PoorApartmentStatus status;

    public void reserve() {
        setStatus(PoorApartmentStatus.reserved);
    }

    public void rent() {
        setStatus(PoorApartmentStatus.rented);
    }

    public void cancel() {
        setStatus(PoorApartmentStatus.free);
    }
}
