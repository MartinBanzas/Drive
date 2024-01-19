package com.martin.Drive.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserMessages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    @OneToOne
    private User ogUser;

    @OneToOne
    private User destUser;

}
