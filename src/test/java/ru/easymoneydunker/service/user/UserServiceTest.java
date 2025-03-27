package ru.easymoneydunker.service.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.easymoneydunker.exception.model.notfound.UserNotFoundException;
import ru.easymoneydunker.model.calories.Goal;
import ru.easymoneydunker.model.user.User;
import ru.easymoneydunker.repository.user.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testSaveUser() {
        User user = new User();
        user.setName("John");
        user.setWeight(70);
        user.setHeight(180);
        user.setAge(25);
        user.setGoal(Goal.CUTTING);
        user.setEmail("john@example.com");

        User savedUser = userService.save(user);

        assertNotNull(savedUser.getId());
        assertEquals("John", savedUser.getName());
        assertTrue(savedUser.getCaloriesPerDay() > 0);
    }


    @Test
    void testFindUserById() {
        User user = new User();
        user.setName("Jane");
        user.setEmail("jane@example.com");
        user.setAge(25);
        user.setHeight(170);
        user.setWeight(60);
        user.setGoal(Goal.CUTTING);

        User savedUser = userRepository.save(user);

        User foundUser = userService.findById(savedUser.getId());

        assertNotNull(foundUser);
        assertEquals("Jane", foundUser.getName());
    }


    @Test
    void testUserNotFound() {
        assertThrows(UserNotFoundException.class, () -> userService.findById(324523423L));
    }
}
