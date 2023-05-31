package com.tamim.twowayencrytion.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Encoded {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;

    private String encodedString;
}
