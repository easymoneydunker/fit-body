package ru.easymoneydunker.service.report;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import ru.easymoneydunker.model.calories.CaloriesCalculator;
import ru.easymoneydunker.model.calories.Goal;
import ru.easymoneydunker.model.eating.Eating;
import ru.easymoneydunker.model.meal.Meal;
import ru.easymoneydunker.model.report.DailyReport;
import ru.easymoneydunker.model.user.User;
import ru.easymoneydunker.repository.eating.EatingRepository;
import ru.easymoneydunker.repository.meal.MealRepository;
import ru.easymoneydunker.repository.user.UserRepository;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class ReportServiceTest {

    @Autowired
    private ReportService reportService;

    @Autowired
    private EatingRepository eatingRepository;

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private UserRepository userRepository;

    private Long testUserId;
    private int caloricTarget;

    @BeforeEach
    void setUp() {
        eatingRepository.deleteAll();
        mealRepository.deleteAll();
        userRepository.deleteAll();

        User user = new User();
        user.setName("Test User");
        user.setEmail("test@example.com");
        user.setAge(30);
        user.setWeight(70);
        user.setHeight(175);
        user.setGoal(Goal.CUTTING);
        caloricTarget = new CaloriesCalculator().calculate(user.getWeight(), user.getHeight(), user.getAge(), user.getGoal());
        user.setCaloriesPerDay(caloricTarget);
        user = userRepository.save(user);
        testUserId = user.getId();

    }

    @Test
    void generateDailyReportIntegrationTest() {
        Meal meal1 = new Meal();
        meal1.setName("Meal 1");
        meal1.setProteins(10);
        meal1.setFats(5);
        meal1.setCarbohydrates(20);
        meal1.setCalories(200);
        mealRepository.save(meal1);

        Meal meal2 = new Meal();
        meal2.setName("Meal 2");
        meal2.setProteins(15);
        meal2.setFats(7);
        meal2.setCarbohydrates(25);
        meal2.setCalories(250);
        mealRepository.save(meal2);

        LocalDate today = LocalDate.now();

        Eating eating1 = new Eating();
        eating1.setUserId(testUserId);
        eating1.setDate(today);
        eating1.setMeals(Arrays.asList(meal1));
        eatingRepository.save(eating1);

        Eating eating2 = new Eating();
        eating2.setUserId(testUserId);
        eating2.setDate(today);
        eating2.setMeals(Arrays.asList(meal2));
        eatingRepository.save(eating2);

        DailyReport report = reportService.generateDailyReport(testUserId, today);

        assertNotNull(report);
        assertEquals(testUserId, report.getUserId());
        assertEquals(today, report.getDate());
        assertEquals(25, report.getTotalProteins());
        assertEquals(12, report.getTotalFats());
        assertEquals(45, report.getTotalCarbohydrates());
        assertEquals(caloricTarget, report.getCaloricTarget());
        assertEquals(450, report.getTotalCalories());
    }
}
