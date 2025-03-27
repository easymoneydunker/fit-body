package ru.easymoneydunker.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.easymoneydunker.exception.model.notfound.UserNotFoundException;
import ru.easymoneydunker.model.calories.CaloriesCalculator;
import ru.easymoneydunker.model.user.User;
import ru.easymoneydunker.repository.user.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final CaloriesCalculator caloriesCalculator;

    @Transactional
    public User save(User user) {
        log.info("Saving user: {}", user.getName());
        int caloriesPerDay = caloriesCalculator
                .calculate(user.getWeight(), user.getHeight(), user.getAge(), user.getGoal());
        user.setCaloriesPerDay(caloriesPerDay);
        User savedUser = userRepository.save(user);
        log.info("User saved: {}", savedUser.getId());
        return savedUser;
    }

    public User findById(Long id) {
        log.info("Finding user by id: {}", id);
        return userRepository.findById(id).orElseThrow(() -> {
            log.error("User with id {} not found.", id);
            return new UserNotFoundException("User with id " + id + " was not found.");
        });
    }

    public User findByName(String name) {
        log.info("Finding user by name: {}", name);
        return userRepository.findByName(name).orElseThrow(() -> {
            log.error("User with name {} not found.", name);
            return new UserNotFoundException("User with name " + name + " was not found.");
        });
    }

    public void deleteById(Long id) {
        log.info("Deleting user by id: {}", id);
        userRepository.deleteById(id);
        log.info("User with id {} deleted.", id);
    }
}
