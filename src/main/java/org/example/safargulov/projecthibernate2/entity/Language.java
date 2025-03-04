package org.example.safargulov.projecthibernate2.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "language")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private Byte id;

    @Column(name = "name", columnDefinition = "CHAR(20)", nullable = false)
    private String name;

    @Column(name = "last_update", insertable = false, updatable = false, nullable = false)
    private Timestamp lastUpdate;

}
