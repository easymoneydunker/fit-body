package ru.easymoneydunker.repository.meal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.easymoneydunker.model.meal.Meal;

import java.util.List;
import java.util.Optional;

public interface MealRepository extends JpaRepository<Meal, Long> {
    Optional<Meal> findByName(String name);

    @Query("select m from Meal m where m.eating.id = ?1")
    List<Meal> findByEatingId(Long eatingId);
}