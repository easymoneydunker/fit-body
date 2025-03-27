package ru.easymoneydunker.model.eating;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.easymoneydunker.model.meal.Meal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "eating")
public class Eating {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "\"date\"", nullable = false)
    private LocalDate date;

    private Long userId;

    @OneToMany(mappedBy = "eating", fetch = FetchType.LAZY)
    private List<Meal> meals = new ArrayList<>();

}
