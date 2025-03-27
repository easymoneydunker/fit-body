package ru.easymoneydunker.service.eating;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.easymoneydunker.exception.model.notfound.EatingNotFoundException;
import ru.easymoneydunker.exception.model.notfound.MealNotFoundException;
import ru.easymoneydunker.model.eating.Eating;
import ru.easymoneydunker.model.meal.Meal;
import ru.easymoneydunker.repository.eating.EatingRepository;
import ru.easymoneydunker.repository.meal.MealRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EatingService {
    private final MealRepository mealRepository;
    private final EatingRepository eatingRepository;

    public Eating save(Eating eating) {
        if (eating.getDate() == null) {
            eating.setDate(java.time.LocalDate.now());
        }
        log.info("Saving eating: {}", eating.getId());
        Eating savedEating = eatingRepository.save(eating);
        log.info("Eating saved: {}", savedEating.getId());
        return savedEating;
    }

    public Eating findById(Long id) {
        log.info("Finding eating by id: {}", id);
        return eatingRepository.findById(id).orElseThrow(() -> {
            log.error("Eating with id {} not found.", id);
            return new EatingNotFoundException("Eating with id " + id + " not found");
        });
    }

    @Transactional
    public Eating addMealByName(String name, Long eatingId) {
        log.info("Adding meal by name: {} to eating with id: {}", name, eatingId);
        Meal meal = mealRepository.findByName(name).orElseThrow(() -> {
            log.error("Meal with name {} not found.", name);
            return new MealNotFoundException("Meal with name " + name + " not found");
        });
        Eating eating = eatingRepository.findById(eatingId).orElseThrow(() -> {
            log.error("Eating with id {} not found.", eatingId);
            return new EatingNotFoundException("Eating with id " + eatingId + " not found");
        });

        List<Meal> meals = mealRepository.findByEatingId(eatingId);
        meal.setEating(eating);
        meals.add(meal);
        eating.setMeals(meals);

        mealRepository.save(meal);
        Eating savedEating = eatingRepository.save(eating);
        log.info("Meal added to eating with id: {}", eatingId);
        return savedEating;
    }

    public void delete(Long id) {
        log.info("Deleting eating by id: {}", id);
        eatingRepository.deleteById(id);
        log.info("Eating with id {} deleted.", id);
    }
}
