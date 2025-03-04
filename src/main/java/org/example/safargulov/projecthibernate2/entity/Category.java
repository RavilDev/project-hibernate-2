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
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Byte id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "last_update", insertable = false, updatable = false, nullable = false)
    private Timestamp lastUpdate;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<FilmCategory> filmCategory;
}
