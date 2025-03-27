package ru.easymoneydunker.service.meal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.easymoneydunker.exception.model.notfound.MealNotFoundException;
import ru.easymoneydunker.model.meal.Meal;
import ru.easymoneydunker.repository.meal.MealRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class MealServiceTest {

    @Autowired
    private MealService mealService;

    @Autowired
    private MealRepository mealRepository;

    @Test
    void testSaveMeal() {
        Meal meal = new Meal();
        meal.setName("Pizza");
        meal.setProteins(10);
        meal.setFats(5);
        meal.setCarbohydrates(20);
        meal.setCalories(300);

        Meal savedMeal = mealService.save(meal);

        assertNotNull(savedMeal.getId());
        assertEquals("Pizza", savedMeal.getName());
    }

    @Test
    void testFindMealByName() {
        Meal meal = new Meal();
        meal.setName("Burger");
        meal.setProteins(10);
        meal.setFats(5);
        meal.setCarbohydrates(20);
        meal.setCalories(300);
        mealRepository.save(meal);

        Meal foundMeal = mealService.findByName("Burger");

        assertNotNull(foundMeal);
        assertEquals("Burger", foundMeal.getName());
    }

    @Test
    void testMealNotFound() {
        assertThrows(MealNotFoundException.class, () -> mealService.findByName("NonExistentMeal"));
    }
}
