package linQ.linQbe.controller;

import linQ.linQbe.domain.User;
import linQ.linQbe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/login")
    public String showLoginPage() {
        return "users/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String userid, @RequestParam String userpassword) {
        User user = userService.findOne(userid).get();
        if (user != null && user.getUserPassword().contentEquals(userpassword)) {
            // 로그인 성공
            return "redirect/";
        } else {
            // 로그인 실패
            // 실패 문구 띄우는 코드 추가 필요
            return "users/login";
        }
    }

    @GetMapping(value = "/register")
    public String showRegisterPage() {
        return "users/register";
    }

    @PostMapping("/register")
    public String register() {

    }
}
