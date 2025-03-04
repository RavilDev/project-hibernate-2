package org.example.safargulov.projecthibernate2.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "actor")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id")
    private Short id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "last_update", insertable = false, updatable = false, nullable = false)
    private Timestamp lastUpdate;

    @OneToMany(mappedBy = "actor", cascade = CascadeType.ALL)
    private List<FilmActor> filmActor;

}
