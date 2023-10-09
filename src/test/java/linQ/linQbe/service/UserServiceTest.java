package linQ.linQbe.service;

import linQ.linQbe.domain.User;
import linQ.linQbe.repository.MemoryUserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserServiceTest {

    UserService userService;

    MemoryUserRepository userRepository;

    @BeforeEach
    public void setup() {
        userRepository = new MemoryUserRepository();
        userService = new UserService(userRepository);
    }

    @AfterEach
    public void clean() {
        userRepository.clearList();
    }

    @Test
    void join() {
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
        String saveId = userService.join(user);

        // then
        User findUser = userService.findOne(saveId).get();
        assertThat(saveId).isEqualTo(findUser.getUserId());
    }

    @Test
    void 아이디_중복_회원_예외() {
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
        user2.setUserId("1");
        user2.setUserEmail("jane.smith@example.com");
        user2.setUserPassword("secret");
        user2.setConfirmPassword("secret");
        user2.setUserName("Jane Smith");
        user2.setUserNickname("janesmith");
        user2.setUserBirthDate(LocalDate.of(1985, 5, 15));
        user2.setUserRegistrationDate(LocalDateTime.now());
        user2.setUserRole("USER");

        // when
        String saveId1 = userService.join(user1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> userService.join(user2)); // 예외가 발생해야 함

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 아이디입니다.");
    }

    @Test
    void 이메일_중복_회원_예외() {
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
        user2.setUserEmail("john.doe@example.com");
        user2.setUserPassword("secret");
        user2.setConfirmPassword("secret");
        user2.setUserName("Jane Smith");
        user2.setUserNickname("janesmith");
        user2.setUserBirthDate(LocalDate.of(1985, 5, 15));
        user2.setUserRegistrationDate(LocalDateTime.now());
        user2.setUserRole("USER");

        // when
        String saveId1 = userService.join(user1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> userService.join(user2)); // 예외가 발생해야 함

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 이메일입니다.");
    }

    @Test
    void 닉네임_중복_회원_예외() {
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
        user2.setUserNickname("johndoe");
        user2.setUserBirthDate(LocalDate.of(1985, 5, 15));
        user2.setUserRegistrationDate(LocalDateTime.now());
        user2.setUserRole("USER");

        // when
        String saveId1 = userService.join(user1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> userService.join(user2)); // 예외가 발생해야 함

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 닉네임입니다.");
    }

    @Test
    void 이메일_형식_예외() {
        // give
        User user = new User();
        user.setUserId("1");
        user.setUserEmail("john.doeexample.com");
        user.setUserPassword("password");
        user.setConfirmPassword("password");
        user.setUserName("John Doe");
        user.setUserNickname("johndoe");
        user.setUserBirthDate(LocalDate.of(1990, 1, 1));
        user.setUserRegistrationDate(LocalDateTime.now());
        user.setUserRole("USER");

        // when
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> userService.join(user)); // 예외가 발생해야 함

        assertThat(e.getMessage()).isEqualTo("올바른 이메일 형식이 아닙니다.");
    }

    @Test
    void 비밀번호_확인_예외() {
        // given
        User user = new User();
        user.setUserId("1");
        user.setUserEmail("john.doee@xample.com");
        user.setUserPassword("password");
        user.setConfirmPassword("notpassword");
        user.setUserName("John Doe");
        user.setUserNickname("johndoe");
        user.setUserBirthDate(LocalDate.of(1990, 1, 1));
        user.setUserRegistrationDate(LocalDateTime.now());
        user.setUserRole("USER");

        // when
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> userService.join(user)); // 예외가 발생해야 함

        assertThat(e.getMessage()).isEqualTo("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
    }

    @Test
    void login() {
        // given
        User user = new User();
        user.setUserId("1");
        user.setUserEmail("john.doee@xample.com");
        user.setUserPassword("password");
        user.setConfirmPassword("password");
        user.setUserName("John Doe");
        user.setUserNickname("johndoe");
        user.setUserBirthDate(LocalDate.of(1990, 1, 1));
        user.setUserRegistrationDate(LocalDateTime.now());
        user.setUserRole("USER");
        userService.join(user);

        String loginId = "1";
        String loginPassword = "password";

        // when
        userService.login(loginId, loginPassword);

        // then
        User loginUser = userService.findOne(loginId).get();
        assertThat(user).isEqualTo(loginUser);
    }

    @Test
    void 로그인_아이디_예외 () {
        // given
        User user = new User();
        user.setUserId("1");
        user.setUserEmail("john.doee@xample.com");
        user.setUserPassword("password");
        user.setConfirmPassword("password");
        user.setUserName("John Doe");
        user.setUserNickname("johndoe");
        user.setUserBirthDate(LocalDate.of(1990, 1, 1));
        user.setUserRegistrationDate(LocalDateTime.now());
        user.setUserRole("USER");
        userService.join(user);

        String loginId = "2";
        String loginPassword = "password";

        // when
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> userService.login(loginId, loginPassword)); // 예외가 발생해야 함

        assertThat(e.getMessage()).isEqualTo("존재하지 않는 아이디입니다.");
    }

    @Test
    void 로그인_비밀번호_예외() {
        // given
        User user = new User();
        user.setUserId("1");
        user.setUserEmail("john.doee@xample.com");
        user.setUserPassword("password");
        user.setConfirmPassword("password");
        user.setUserName("John Doe");
        user.setUserNickname("johndoe");
        user.setUserBirthDate(LocalDate.of(1990, 1, 1));
        user.setUserRegistrationDate(LocalDateTime.now());
        user.setUserRole("USER");
        userService.join(user);

        String loginId = "1";
        String loginPassword = "secret";

        // when
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> userService.login(loginId, loginPassword)); // 예외가 발생해야 함

        assertThat(e.getMessage()).isEqualTo("비밀번호가 일치하지 않습니다.");
    }

    @Test
    void findAllUser() {

    }

    @Test
    void findOne() {
    }
}