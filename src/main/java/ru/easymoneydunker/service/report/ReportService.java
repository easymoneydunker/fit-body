package ru.easymoneydunker.service.report;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.easymoneydunker.exception.model.notfound.UserNotFoundException;
import ru.easymoneydunker.model.eating.Eating;
import ru.easymoneydunker.model.meal.Meal;
import ru.easymoneydunker.model.report.DailyReport;
import ru.easymoneydunker.model.user.User;
import ru.easymoneydunker.repository.report.DailyReportRepository;
import ru.easymoneydunker.repository.eating.EatingRepository;
import ru.easymoneydunker.repository.user.UserRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final UserRepository userRepository;
    private final EatingRepository eatingRepository;
    private final DailyReportRepository dailyReportRepository;

    public DailyReport generateDailyReport(Long userId, LocalDate date) {
        List<Eating> eatings = eatingRepository.findByUserIdAndDate(userId, date);
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

        int totalProteins = 0;
        int totalFats = 0;
        int totalCarbohydrates = 0;
        int totalCalories = 0;
        int caloricTarget = user.getCaloriesPerDay();

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
        report.setCaloricTarget(caloricTarget);
        report.setEatings(eatings);

        return dailyReportRepository.save(report);
    }

    public List<DailyReport> getDailyReportsByUserId(Long userId) {
        return dailyReportRepository.findByUserId(userId);
    }
}
