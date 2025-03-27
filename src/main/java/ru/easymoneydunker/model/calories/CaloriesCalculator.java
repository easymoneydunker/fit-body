package ru.easymoneydunker.model.calories;

import org.springframework.stereotype.Component;

@Component
public class CaloriesCalculator {
    public int calculate(double weight, double height, int age, Goal goal) {
        if (weight <= 0 || height <= 0 || age <= 0) {
            throw new IllegalArgumentException("Weight, height, and age must be positive values.");
        }

        int baseCalculation = (int) (weight * 10 * height / 6.25 - age * 5 - 161);

        if (goal == Goal.CUTTING) {
            return baseCalculation;
        } else if (goal == Goal.MAINTAINING) {
            return baseCalculation * 15 / 100 + baseCalculation;
        } else if (goal == Goal.BULKING) {
            return baseCalculation * 15 / 100 + baseCalculation * 2;
        } else {
            throw new IllegalArgumentException("Invalid goal: " + goal);
        }
    }
}
