package net.shubhankarpotnis.diaryApp.repository;

import net.shubhankarpotnis.diaryApp.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


@DataMongoTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        userRepository.deleteAll();

        User testUser = new User("testUser", "testPass");
        userRepository.save(testUser);
    }

    @Test
    public void findByUserName_WhenUserExists_ReturnsUser() {
        User user = userRepository.findByUserName("testUser");
        assertNotNull(user);
        assertEquals("testUser", user.getUserName());
    }

    @Test
    void deleteByUserName_WhenCalled_RemovesUser() {
        userRepository.deleteByUserName("testUser");
        User user = userRepository.findByUserName("testUser");
        assertNull(user);
    }
}

