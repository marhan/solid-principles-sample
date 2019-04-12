package de.marhan.sample.solid.domain.poorly;

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
public class PoorlyApartment {

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
    private PoorlyApartmentStatus status;

    public void reserve() {
        setStatus(PoorlyApartmentStatus.reserved);
    }

    public void rent() {
        setStatus(PoorlyApartmentStatus.rented);
    }

    public void cancel() {
        setStatus(PoorlyApartmentStatus.free);
    }
}
