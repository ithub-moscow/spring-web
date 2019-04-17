package hello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FIleRepositoryConfig {

    @Bean
    public FileRepository fileRepository() {
        return new MemoryFileRepository();
    }
}
