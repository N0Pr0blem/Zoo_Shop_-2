package org.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="companies")
public class Company {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private String image;

    public Company(Long id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }
}
