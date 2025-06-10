package com.davi.restaurant_burguer.repositories;

import com.davi.restaurant_burguer.models.Users;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("should return User successfully from DB")
    void findOneByPhoneCase1() {
        String phone = "+5511999999999";
        Users user = new Users("test1",1,phone);
        this.createUser(user);
        Users result = this.userRepository.findOneByPhone(phone);

        assertThat(result != null).isTrue();
    }

    @Test
    @DisplayName("should return null case user not exist in DB")
    void findOneByPhoneCase2() {
        String phone = "+5511999999999";
        Users result = this.userRepository.findOneByPhone(phone);

        assertThat(result == null).isTrue();
    }

    private void createUser(Users user) {
        this.entityManager.persist(user);
    }

    private void createManyUsers(List<Users> users) {
        for(Users u : users) {
            this.entityManager.persist(u);
        }
    }

    @Test
    @DisplayName("Should return a User")
    void findOneCase1() {
        Users user = new Users("Test user",1,"+5511999999999");
        this.createUser(user);
        Users userReturned = this.userRepository.findOne(user.getId());

        assertThat(userReturned != null).isTrue();
    }

    @Test
    @DisplayName("Should return null case id is not valid in DB")
    public void findOneCase2() {
        Users userReturned = this.userRepository.findOne(1);

        assertThat(userReturned == null).isTrue();
    }

    @Test
    @DisplayName("Should return null if deletedAt is not empty")
    public void findOneCase3() {
        Users user = new Users("User test",1,"+5511999999999");
        user.setDeletedAt(OffsetDateTime.now());
        this.createUser(user);
        Users userReturned = this.userRepository.findOne(user.getId());
        assertThat(userReturned == null).isTrue();
    }

    @Test
    @DisplayName("should return all users if deletedAt is null")
    public void findAllCase1() {
        Users deletedUser = new Users("Deleted user test 1", 1, "+5511999999995");
        deletedUser.setDeletedAt(OffsetDateTime.now());
        List<Users> users = new ArrayList<>(List.of(
                new Users("User test 1", 1, "+5511999999999"),
                new Users("User test 2", 1, "+5511999999299"),
                new Users("User test 3", 1, "+5511999999939"),
                new Users("User test 4", 1, "+5511999999994"),
                deletedUser
        ));
        this.createManyUsers(users);
        List<Users> usersReturned = this.userRepository.findAll();

        assertThat(usersReturned.size()).isEqualTo(4);
    }

    @Test
    @DisplayName("should return null case the table is empty")
    public void findAllCase2() {
        List<Users> usersReturned = this.userRepository.findAll();

        assertThat(usersReturned).hasSize(0);
    }
}