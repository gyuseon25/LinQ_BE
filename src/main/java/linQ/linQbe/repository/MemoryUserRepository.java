package linQ.linQbe.repository;

import linQ.linQbe.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemoryUserRepository implements UserRepository {

    private List<User> userList = new ArrayList<>();

    @Override
    public User save(User user) {
        userList.add(user);
        return user;
    }

    @Override
    public Optional<User> findById(String userid) {
        return userList.stream()
                .filter(u -> u.getUserId().equals(userid))
                .findAny();
    }

    @Override
    public Optional<User> findByEmail(String useremail) {
        return userList.stream()
                .filter(u -> u.getUserEmail().equals(useremail))
                .findAny();
    }

    @Override
    public Optional<User> findByNickname(String usernickname) {
        return userList.stream()
                .filter(u -> u.getUserNickname().equals(usernickname))
                .findAny();
    }

    @Override
    public List<User> findAll() {
        return userList;
    }

    public void clearList() {
        userList.clear();
    }
}
