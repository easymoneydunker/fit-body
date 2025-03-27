package ru.easymoneydunker.controller.meal;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.easymoneydunker.model.meal.Meal;
import ru.easymoneydunker.service.meal.MealService;

@RestController
@RequestMapping("/meals")
@RequiredArgsConstructor
@Validated
public class MealController {
    private final MealService mealService;

    @PostMapping
    public ResponseEntity<Meal> createMeal(@RequestBody @Valid Meal meal) {
        Meal savedMeal = mealService.save(meal);
        return new ResponseEntity<>(savedMeal, HttpStatus.CREATED);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Meal> getMealByName(@PathVariable("name") @NotBlank String name) {
        Meal meal = mealService.findByName(name);
        return ResponseEntity.ok(meal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeal(@PathVariable("id") @Positive Long id) {
        mealService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
