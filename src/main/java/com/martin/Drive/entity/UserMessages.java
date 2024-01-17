package com.martin.Drive.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserMessages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private String message;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "id", cascade={CascadeType.ALL})
    private User userOg;

    @OneToOne(fetch = FetchType.LAZY,  mappedBy = "id")
    private User UserDestination;

    private Date date;

}
