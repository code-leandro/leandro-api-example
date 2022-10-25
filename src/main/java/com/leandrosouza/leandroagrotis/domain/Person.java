package com.leandrosouza.leandroagrotis.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private LocalDateTime start;
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "id_property", referencedColumnName = "id", insertable = false, updatable = false)
    private Property property;

    @Column(name = "id_property")
    private Integer idProperty;

    @ManyToOne
    @JoinColumn(name = "id_laboratory", referencedColumnName = "id", insertable = false, updatable = false)
    private Laboratory laboratory;

    @Column(name = "id_laboratory")
    private Integer idLaboratory;

    private String idEmployer;
    private String note;
}
