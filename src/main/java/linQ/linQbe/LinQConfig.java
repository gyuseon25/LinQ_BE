package linQ.linQbe;

import linQ.linQbe.repository.MemoryUserRepository;
import linQ.linQbe.repository.UserRepository;
import linQ.linQbe.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LinQConfig {

    @Bean
    public UserService userService() {
        return new UserService(userRepository());
    }

    @Bean
    public UserRepository userRepository() {
        return new MemoryUserRepository();
    }

}
