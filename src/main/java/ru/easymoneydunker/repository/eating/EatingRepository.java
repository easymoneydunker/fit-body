package ru.easymoneydunker.repository.eating;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.easymoneydunker.model.eating.Eating;

public interface EatingRepository extends JpaRepository<Eating, Long> {
}