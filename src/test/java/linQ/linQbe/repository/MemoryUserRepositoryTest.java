package linQ.linQbe.repository;

import linQ.linQbe.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemoryUserRepositoryTest {
    private MemoryUserRepository memoryUserRepository;

    @BeforeEach
    public void setUp() {
        memoryUserRepository = new MemoryUserRepository();
    }

    @Test
    public void testSave() {
        // given
        User user = new User();
        user.setUserId("1");
        user.setUserEmail("john.doe@example.com");
        user.setUserPassword("password");
        user.setConfirmPassword("password");
        user.setUserName("John Doe");
        user.setUserNickname("johndoe");
        user.setUserBirthDate(LocalDate.of(1990, 1, 1));
        user.setUserRegistrationDate(LocalDateTime.now());
        user.setUserRole("USER");

        // when
        memoryUserRepository.save(user);

        // then
        // 저장된 사용자 아이디로 조회
        User foundUser = memoryUserRepository.findById(user.getUserId()).get();
        // 조회된 사용자가 존재하는지 확인
        assertThat(foundUser).isEqualTo(user);
    }

    @Test
    public void testSaveAndFindById() {
        // given
        User user1 = new User();
        user1.setUserId("1");
        user1.setUserEmail("john.doe@example.com");
        user1.setUserPassword("password");
        user1.setConfirmPassword("password");
        user1.setUserName("John Doe");
        user1.setUserNickname("johndoe");
        user1.setUserBirthDate(LocalDate.of(1990, 1, 1));
        user1.setUserRegistrationDate(LocalDateTime.now());
        user1.setUserRole("USER");

        User user2 = new User();
        user2.setUserId("2");
        user2.setUserEmail("jane.smith@example.com");
        user2.setUserPassword("secret");
        user2.setConfirmPassword("secret");
        user2.setUserName("Jane Smith");
        user2.setUserNickname("janesmith");
        user2.setUserBirthDate(LocalDate.of(1985, 5, 15));
        user2.setUserRegistrationDate(LocalDateTime.now());
        user2.setUserRole("USER");

        // when
        memoryUserRepository.save(user1);
        memoryUserRepository.save(user2);

        // then
        // 저장된 사용자 아이디로 조회
        User foundUser = memoryUserRepository.findById("1").get();
        // 조회된 사용자가 존재하는지 확인
        assertThat(foundUser).isEqualTo(user1);
    }

    @Test
    public void testFindByEmail() {
        // given
        User user1 = new User();
        user1.setUserId("1");
        user1.setUserEmail("john.doe@example.com");
        user1.setUserPassword("password");
        user1.setConfirmPassword("password");
        user1.setUserName("John Doe");
        user1.setUserNickname("johndoe");
        user1.setUserBirthDate(LocalDate.of(1990, 1, 1));
        user1.setUserRegistrationDate(LocalDateTime.now());
        user1.setUserRole("USER");

        User user2 = new User();
        user2.setUserId("2");
        user2.setUserEmail("jane.smith@example.com");
        user2.setUserPassword("secret");
        user2.setConfirmPassword("secret");
        user2.setUserName("Jane Smith");
        user2.setUserNickname("janesmith");
        user2.setUserBirthDate(LocalDate.of(1985, 5, 15));
        user2.setUserRegistrationDate(LocalDateTime.now());
        user2.setUserRole("USER");

        // when
        memoryUserRepository.save(user1);
        memoryUserRepository.save(user2);

        // then
        // 저장된 사용자 이메일로 조회
        User foundUser = memoryUserRepository.findByEmail("john.doe@example.com").get();
        // 조회된 사용자가 존재하는지 확인
        assertThat(foundUser).isEqualTo(user1);
    }

    @Test
    public void testFindByNickname() {
        // given
        User user1 = new User();
        user1.setUserId("1");
        user1.setUserEmail("john.doe@example.com");
        user1.setUserPassword("password");
        user1.setConfirmPassword("password");
        user1.setUserName("John Doe");
        user1.setUserNickname("johndoe");
        user1.setUserBirthDate(LocalDate.of(1990, 1, 1));
        user1.setUserRegistrationDate(LocalDateTime.now());
        user1.setUserRole("USER");

        User user2 = new User();
        user2.setUserId("2");
        user2.setUserEmail("jane.smith@example.com");
        user2.setUserPassword("secret");
        user2.setConfirmPassword("secret");
        user2.setUserName("Jane Smith");
        user2.setUserNickname("janesmith");
        user2.setUserBirthDate(LocalDate.of(1985, 5, 15));
        user2.setUserRegistrationDate(LocalDateTime.now());
        user2.setUserRole("USER");

        // when
        memoryUserRepository.save(user1);
        memoryUserRepository.save(user2);

        // then
        // 저장된 사용자 닉네임으로 조회
        User foundUser = memoryUserRepository.findByNickname("johndoe").get();
        // 조회된 사용자가 존재하는지 확인
        assertThat(foundUser).isEqualTo(user1);
    }

    @Test
    public void testFindAll() {
        // given
        User user1 = new User();
        user1.setUserId("1");
        user1.setUserEmail("john.doe@example.com");
        user1.setUserPassword("password");
        user1.setConfirmPassword("password");
        user1.setUserName("John Doe");
        user1.setUserNickname("johndoe");
        user1.setUserBirthDate(LocalDate.of(1990, 1, 1));
        user1.setUserRegistrationDate(LocalDateTime.now());
        user1.setUserRole("USER");

        User user2 = new User();
        user2.setUserId("2");
        user2.setUserEmail("jane.smith@example.com");
        user2.setUserPassword("secret");
        user2.setConfirmPassword("secret");
        user2.setUserName("Jane Smith");
        user2.setUserNickname("janesmith");
        user2.setUserBirthDate(LocalDate.of(1985, 5, 15));
        user2.setUserRegistrationDate(LocalDateTime.now());
        user2.setUserRole("USER");

        // when
        memoryUserRepository.save(user1);
        memoryUserRepository.save(user2);

        // then
        // 모든 사용자 조회
        List<User> userList = memoryUserRepository.findAll();
        // 조회된 사용자 수 확인
        assertThat(2).isEqualTo(userList.size());
        assertTrue(userList.contains(user1));
        assertTrue(userList.contains(user2));
    }
}