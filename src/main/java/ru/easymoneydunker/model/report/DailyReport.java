package ru.easymoneydunker.model.report;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.easymoneydunker.model.eating.Eating;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "daily_report")
public class DailyReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "\"date\"", nullable = false)
    private LocalDate date;

    private int totalProteins;

    private int totalFats;

    private int totalCarbohydrates;

    private int totalCalories;

    private int caloricTarget;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "daily_report_id")
    private List<Eating> eatings;
}
