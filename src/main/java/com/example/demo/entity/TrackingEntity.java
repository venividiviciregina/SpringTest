package com.example.demo.entity;

import com.example.demo.status.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@AllArgsConstructor
@Entity(name = "tracking")
public class TrackingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tracking_id")
    private Long tracking;

    @NonNull
    @Transient
    private Status statusEnum;

    @Basic
    private String status;

    @JoinColumn(name = "shipment_id")
    @Fetch(FetchMode.JOIN)
    @ManyToOne
    private ShipmentEntity shipment;

    @PrePersist
    private void prePersistent() {

        if( statusEnum != null ) {
            status = statusEnum.dbName();
        }
    }

    @PostLoad
    private void post()  {
        if( status != null ) {
            this.statusEnum = Status.of(status);
        }
    }
}
