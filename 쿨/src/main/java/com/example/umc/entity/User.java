package com.example.umc.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;

    String name;

    String nickname;

    String phonenumber;

    String birthday;

}
