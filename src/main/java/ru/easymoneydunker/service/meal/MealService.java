package ru.easymoneydunker.service.meal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.easymoneydunker.exception.model.notfound.MealNotFoundException;
import ru.easymoneydunker.model.meal.Meal;
import ru.easymoneydunker.repository.meal.MealRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class MealService {
    private final MealRepository mealRepository;

    public Meal findByName(String name) {
        log.info("Finding meal by name: {}", name);
        return mealRepository.findByName(name).orElseThrow(() -> {
            log.error("Meal with name {} not found.", name);
            return new MealNotFoundException("Meal with name " + name + " was not found.");
        });
    }

    public Meal save(Meal meal) {
        log.info("Saving meal: {}", meal.getName());
        Meal savedMeal = mealRepository.save(meal);
        log.info("Meal saved: {}", savedMeal.getId());
        return savedMeal;
    }

    public void deleteById(Long id) {
        log.info("Deleting meal by id: {}", id);
        mealRepository.deleteById(id);
        log.info("Meal with id {} deleted.", id);
    }
}
