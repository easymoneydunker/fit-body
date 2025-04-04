package ru.easymoneydunker.controller.eating;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.easymoneydunker.model.eating.Eating;
import ru.easymoneydunker.service.eating.EatingService;

@RestController
@RequestMapping("/eatings")
@RequiredArgsConstructor
@Validated
public class EatingController {
    private final EatingService eatingService;

    @PostMapping
    public ResponseEntity<Eating> createEating(@RequestBody @Valid Eating eating, @RequestParam("userId") @Positive Long userId) {
        Eating savedEating = eatingService.save(eating, userId);
        return new ResponseEntity<>(savedEating, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Eating> getEatingById(@PathVariable("id") @Positive Long id) {
        Eating eating = eatingService.findById(id);
        return ResponseEntity.ok(eating);
    }

    @PostMapping("/{eatingId}/add-meal")
    public ResponseEntity<Eating> addMealToEating(@RequestParam("name") @NotBlank String name, @PathVariable("eatingId") @Positive Long eatingId) {
        Eating updatedEating = eatingService.addMealByName(name, eatingId);
        return ResponseEntity.ok(updatedEating);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEating(@PathVariable("id") @Positive Long id) {
        eatingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
