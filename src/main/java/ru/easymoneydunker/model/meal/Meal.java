package ru.easymoneydunker.model.meal;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import ru.easymoneydunker.model.eating.Eating;

@Getter
@Setter
@Entity
@Table(name = "meal")
public class Meal {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name is required")
    private String name;
    @Positive
    private Integer calories;
    @Positive
    private Integer proteins;
    @Positive
    private Integer fats;
    @Positive
    private Integer carbohydrates;

    @ManyToOne
    @JoinColumn(name = "eating_id", nullable = true)
    private Eating eating;
}
