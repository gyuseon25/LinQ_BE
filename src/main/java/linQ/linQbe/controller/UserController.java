package linQ.linQbe.controller;

import linQ.linQbe.domain.User;
import linQ.linQbe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users/login")
    public String loginForm(HttpSession session){
        return "/users/login";
    }
    @PostMapping("/users/login")
    public String login(@RequestParam("userId") String userid, @RequestParam("userPassword") String userpassword) {
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

    @GetMapping(value = "/users/register")
    public String registerForm() {
        return "users/register";
    }

    @PostMapping("/users/register")
    public String register(@RequestParam("userId") String userId,
                           @RequestParam("userEmail") String userEmail,
                           @RequestParam("userPassword") String userPassword,
                           @RequestParam("userName") String userName) {
        User user = new User();
        user.setUserId(userId);
        user.setUserEmail(userEmail);
        user.setUserPassword(userPassword);
        user.setUserName(userName);
        userService.join(user);
        return "/users/login";
    }
}

