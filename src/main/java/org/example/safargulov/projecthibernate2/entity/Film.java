package org.example.safargulov.projecthibernate2.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.safargulov.projecthibernate2.util.SpecialFeatureSetConverter;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "film")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Short Id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "release_year")
    private Date releaseYear;

    @Column(name = "rental_duration", nullable = false, insertable = false)
    private Byte rentalDuration;

    @Column(name = "rental_rate", nullable = false, insertable = false)
    private BigDecimal rentalRate;

    @Column(name = "length")
    private Short length;

    @Column(name = "replacement_cost", nullable = false, insertable = false)
    private BigDecimal replacementCost;

    @Enumerated(EnumType.STRING)
    @Column(name = "rating", insertable = false)
    private Rating rating;

    @Convert(converter = SpecialFeatureSetConverter.class)
    @Column(name = "special_features", columnDefinition = "SET('Trailers','Commentaries','Deleted Scenes','Behind the Scenes')")
    private Set<SpecialFeature> specialFeatures;

    @Column(name = "last_update", insertable = false, updatable = false, nullable = false)
    private Timestamp lastUpdate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "language_id", nullable = false)
    private Language language;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "original_language_id")
    private Language originalLanguage;

    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL)
    private List<FilmActor> filmActor;

    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL)
    private List<FilmCategory> filmCategory;
}
