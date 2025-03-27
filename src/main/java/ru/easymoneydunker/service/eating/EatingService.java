package ru.easymoneydunker.service.eating;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.easymoneydunker.exception.model.notfound.EatingNotFoundException;
import ru.easymoneydunker.exception.model.notfound.MealNotFoundException;
import ru.easymoneydunker.exception.model.notfound.UserNotFoundException;
import ru.easymoneydunker.model.eating.Eating;
import ru.easymoneydunker.model.meal.Meal;
import ru.easymoneydunker.repository.eating.EatingRepository;
import ru.easymoneydunker.repository.meal.MealRepository;
import ru.easymoneydunker.repository.user.UserRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class EatingService {
    private final MealRepository mealRepository;
    private final UserRepository userRepository;
    private final EatingRepository eatingRepository;

    @Transactional
    public Eating save(Eating eating, Long userId) {
        userRepository.findById(userId).orElseThrow(() -> {
            log.error("User with id {} not found.", userId);
            return new UserNotFoundException("User with id " + userId + " not found");
        });

        eating.setUserId(userId);
        if (eating.getDate() == null) {
            eating.setDate(LocalDate.now());
        }

        Eating savedEating = eatingRepository.save(eating);
        log.info("Eating saved: {}", savedEating.getId());
        return savedEating;
    }

    @Transactional
    public Eating addMealByName(String name, Long eatingId) {
        Meal meal = mealRepository.findByName(name).orElseThrow(() -> {
            log.error("Meal with name {} not found.", name);
            return new MealNotFoundException("Meal with name " + name + " not found");
        });

        Eating eating = eatingRepository.findById(eatingId).orElseThrow(() -> {
            log.error("Eating with id {} not found.", eatingId);
            return new EatingNotFoundException("Eating with id " + eatingId + " not found");
        });

        eating.getMeals().add(meal);
        return eatingRepository.save(eating);
    }

    public Eating findById(Long id) {
        log.info("Finding eating by id: {}", id);
        return eatingRepository.findById(id).orElseThrow(() -> {
            log.error("Eating with id {} not found.", id);
            return new EatingNotFoundException("Eating with id " + id + " not found");
        });
    }

    public void delete(Long id) {
        log.info("Deleting eating by id: {}", id);
        eatingRepository.deleteById(id);
        log.info("Eating with id {} deleted.", id);
    }
}
