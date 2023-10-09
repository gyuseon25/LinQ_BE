package linQ.linQbe.service;

import linQ.linQbe.domain.User;
import linQ.linQbe.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 회원 가입
    public String join(User user) {
        // 중복 검사: 아이디, 이메일, 닉네임
        isIdDuplicate(user);
        isEmailDuplicate(user);
        isNicknameDuplicate(user);
        
        // 이메일 형식 검사
        isVaildEmail(user);

        // 비밀번호 확인 검사
        isSamePassword(user);

        userRepository.save(user);
        return user.getUserId();
    }

    // 아이디 중복 검사
    private void isIdDuplicate(User user) {
        userRepository.findById(user.getUserId())
                .ifPresent(u -> {
                    throw new IllegalStateException("이미 존재하는 아이디입니다.");
                });
    }

    // 이메일 중복 검사
    private void isEmailDuplicate(User user) {
        userRepository.findByEmail(user.getUserEmail())
                .ifPresent(u -> {
                    throw new IllegalStateException("이미 존재하는 이메일입니다.");
                });
    }

    // 닉네임 중복 검사
    private void isNicknameDuplicate(User user) {
        userRepository.findByNickname(user.getUserNickname())
                .ifPresent(u -> {
                    throw new IllegalStateException("이미 존재하는 닉네임입니다.");
                });
    }

    // 이메일 형식 검사
    private void isVaildEmail(User user) {
        String emailFormat = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (!user.getUserEmail().matches(emailFormat)) {
            throw new IllegalStateException("올바른 이메일 형식이 아닙니다.");
        }
    }

    // 비밀번호 확인 검사
    private void isSamePassword(User user) {
        if (!(user.getUserPassword().contentEquals(user.getConfirmPassword()))) {
            throw new IllegalStateException("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        }
    }

    // 로그인
    public User login(String userid, String userpassword) {
        User user = userRepository.findById(userid)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 아이디입니다."));

        if (!(user.getUserPassword().contentEquals(userpassword))) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }

        return user;
    }

    // 전체 회원 조회
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    // 회원 조회
    public Optional<User> findOne(String userid) {
        return userRepository.findById(userid);
    }

}
