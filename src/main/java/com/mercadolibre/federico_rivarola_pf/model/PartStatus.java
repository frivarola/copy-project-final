package com.mercadolibre.federico_rivarola_pf.model;

import javax.persistence.*;

@Entity
@Table(name = "part_status")
public class PartStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_part_status", length = 2)
    private Integer idPartStatus;
    @Column(name = "code")
    private String code;
    @Column(name = "description")
    private String description;
}
