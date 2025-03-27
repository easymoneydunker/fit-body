package ru.easymoneydunker.model.calories;

import org.springframework.stereotype.Component;

@Component
public class CaloriesCalculator {

    public int calculate(double weightKg, double heightCm, int age, Goal goal) {
        if (weightKg <= 0 || heightCm <= 0 || age <= 0) {
            throw new IllegalArgumentException("Weight, height, and age must be positive values.");
        }

        double bmr = (10 * weightKg) + (6.25 * heightCm) - (5 * age) - 161;

        double maintenanceCalories = bmr * 1.55;

        return switch (goal) {
            case CUTTING -> (int) (maintenanceCalories * 0.85);
            case MAINTAINING -> (int) maintenanceCalories;
            case BULKING -> (int) (maintenanceCalories * 1.15);
            default -> throw new IllegalArgumentException("Invalid goal: " + goal);
        };
    }
}