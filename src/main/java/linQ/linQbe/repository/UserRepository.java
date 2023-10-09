package linQ.linQbe.repository;

import linQ.linQbe.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);

    Optional<User> findById(String userid);

    Optional<User> findByEmail(String useremail);

    Optional<User> findByNickname(String usernickname);

    List<User> findAll();
}
