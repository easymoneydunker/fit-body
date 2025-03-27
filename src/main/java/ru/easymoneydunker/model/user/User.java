package ru.easymoneydunker.model.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import ru.easymoneydunker.model.calories.Goal;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Email is required")
    private String email;
    @Positive
    private int age;
    @Min(1)
    private int weight;
    @Min(1)
    private int height;
    private int caloriesPerDay;
    @Enumerated(EnumType.STRING)
    private Goal goal;
}