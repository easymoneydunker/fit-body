package ru.easymoneydunker.repository.eating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.easymoneydunker.model.eating.Eating;

import java.time.LocalDate;
import java.util.List;

public interface EatingRepository extends JpaRepository<Eating, Long> {
    @Query("select e from Eating e where e.userId = ?1 and e.date = ?2")
    List<Eating> findByUserIdAndDate(Long userId, LocalDate date);
}