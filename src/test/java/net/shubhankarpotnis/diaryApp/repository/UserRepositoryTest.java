package net.shubhankarpotnis.diaryApp.repository;

import net.shubhankarpotnis.diaryApp.entity.User;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureDataMongo
class UserRepositoryTest {

 //this class is testing directly on the real DB, Plz configure that !!!!!

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

