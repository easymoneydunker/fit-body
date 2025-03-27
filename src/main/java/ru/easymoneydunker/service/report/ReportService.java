package ru.easymoneydunker.service.report;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.easymoneydunker.model.eating.Eating;
import ru.easymoneydunker.model.meal.Meal;
import ru.easymoneydunker.model.report.DailyReport;
import ru.easymoneydunker.repository.eating.EatingRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final EatingRepository eatingRepository;

    public DailyReport generateDailyReport(Long userId, LocalDate date) {
        List<Eating> eatings = eatingRepository.findByUserIdAndDate(userId, date);

        int totalProteins = 0;
        int totalFats = 0;
        int totalCarbohydrates = 0;
        int totalCalories = 0;

        for (Eating eating : eatings) {
            for (Meal meal : eating.getMeals()) {
                totalProteins += meal.getProteins();
                totalFats += meal.getFats();
                totalCarbohydrates += meal.getCarbohydrates();
                totalCalories += meal.getCalories();
            }
        }

        DailyReport report = new DailyReport();
        report.setUserId(userId);
        report.setDate(date);
        report.setTotalProteins(totalProteins);
        report.setTotalFats(totalFats);
        report.setTotalCarbohydrates(totalCarbohydrates);
        report.setTotalCalories(totalCalories);
        report.setEatings(eatings);

        return report;
    }
}
