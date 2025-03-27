package ru.easymoneydunker.service.eating;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.easymoneydunker.exception.model.notfound.EatingNotFoundException;
import ru.easymoneydunker.model.eating.Eating;
import ru.easymoneydunker.model.meal.Meal;
import ru.easymoneydunker.model.calories.Goal;
import ru.easymoneydunker.model.user.User;
import ru.easymoneydunker.repository.eating.EatingRepository;
import ru.easymoneydunker.repository.meal.MealRepository;
import ru.easymoneydunker.repository.user.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class EatingServiceTest {

    @Autowired
    private EatingService eatingService;

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private EatingRepository eatingRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testSaveEating() {
        User user = new User();
        user.setName("John");
        user.setEmail("john.doe@example.com");
        user.setAge(30);
        user.setWeight(70);
        user.setHeight(175);
        user.setCaloriesPerDay(2000);
        user.setGoal(Goal.MAINTAINING);
        userRepository.save(user);

        Eating eating = new Eating();
        eating.setDate(LocalDate.now());
        eating.setUserId(user.getId());

        Meal meal = new Meal();
        meal.setName("Pizza");
        meal.setProteins(10);
        meal.setFats(5);
        meal.setCarbohydrates(20);
        meal.setCalories(300);
        meal.setEating(eating);

        List<Meal> food = new ArrayList<>(List.of(meal));
        eating.setMeals(food);

        Eating savedEating = eatingService.save(eating);

        assertNotNull(savedEating.getId());
        assertEquals(eating.getMeals().get(0).getName(), savedEating.getMeals().get(0).getName());
    }

    @Test
    void testAddMealByName() {
        Eating eating = new Eating();
        eating.setDate(LocalDate.now());
        eating.setUserId(userRepository.findById(1L).orElseThrow().getId());

        Meal meal = new Meal();
        meal.setName("Pancakes");
        meal.setProteins(10);
        meal.setFats(5);
        meal.setCarbohydrates(20);
        meal.setCalories(300);
        meal.setEating(eating);

        eating.setMeals(new ArrayList<>(List.of(meal)));

        Eating savedEating = eatingRepository.save(eating);
        mealRepository.save(meal);

        Eating updatedEating = eatingService.addMealByName("Pancakes", savedEating.getId());

        assertTrue(updatedEating.getMeals().stream().anyMatch(m -> m.getName().equals("Pancakes")));
    }

    @Test
    void testEatingNotFound() {Meal meal = new Meal();
        assertThrows(EatingNotFoundException.class, () -> eatingService.findById(23543L));
    }
}
