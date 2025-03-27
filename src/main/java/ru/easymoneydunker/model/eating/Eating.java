package ru.easymoneydunker.model.eating;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.easymoneydunker.model.meal.Meal;
import ru.easymoneydunker.model.report.DailyReport;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "eating")
public class Eating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "\"date\"", nullable = false)
    private LocalDate date;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "daily_report_id", insertable = false, updatable = false)
    private DailyReport dailyReport;


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "eating_meals",
            joinColumns = @JoinColumn(name = "eating_id"),
            inverseJoinColumns = @JoinColumn(name = "meal_id")
    )
    private List<Meal> meals = new ArrayList<>();

    public void addMeal(Meal meal) {
        meals.add(meal);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Eating eating = (Eating) o;
        return Objects.equals(getDate(), eating.getDate()) && Objects.equals(getUserId(), eating.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDate(), getUserId());
    }
}
