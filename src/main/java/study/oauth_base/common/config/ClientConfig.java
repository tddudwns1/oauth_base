package study.oauth_base.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class ClientConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
