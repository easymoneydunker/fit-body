package ru.easymoneydunker.model.report;

import lombok.Data;
import ru.easymoneydunker.model.eating.Eating;

import java.time.LocalDate;
import java.util.List;

@Data
public class DailyReport {
    private Long userId;
    private LocalDate date;
    private int totalProteins;
    private int totalFats;
    private int totalCarbohydrates;
    private int totalCalories;
    private List<Eating> eatings;
}
