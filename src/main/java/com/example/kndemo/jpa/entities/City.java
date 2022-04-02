package com.example.kndemo.jpa.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "cities")
@AllArgsConstructor
@NoArgsConstructor
public class City {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    @NotNull
    private UUID id;

    @Column(nullable = false)
    @NotNull
    private String name;

    @Column(nullable = false)
    @NotNull
    private String url;


    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String photo) {
        this.url = photo;
    }
}
