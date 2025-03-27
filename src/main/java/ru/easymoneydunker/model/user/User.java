package ru.easymoneydunker.model.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
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
    @Max(value = 120, message = "Age must be less than or equal to 120")
    private int age;
    @Min(value = 30,  message = "Weight must be greater than or equal to 30kg")
    @Max(value = 200, message = "Weight must be less than or equal to 200kg")
    private int weight;
    @Min(value = 100,  message = "Height must be greater than or equal to 100cm")
    @Max(value = 250,  message = "Height must be less than or equal to 250cm")
    private int height;
    private int caloriesPerDay;
    @Enumerated(EnumType.STRING)
    private Goal goal;
}