package app;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class Container {

    @Bean
    public AI hardAI() {
        return new HardAI();
    }

    @Bean
    public AI randomAI() {
        return new RandomAI();
    }
}
