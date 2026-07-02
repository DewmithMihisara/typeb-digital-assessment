package digital.typeb.take_home_assessment.service;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HelloWorldService {

    public Optional<String> greet(String name) {
        if (name == null || name.isBlank()) {
            return Optional.empty();
        }

        char first = Character.toUpperCase(name.charAt(0));
        if (first < 'A' || first > 'M') {
            return Optional.empty();
        }

        String capitalized = first + name.substring(1);
        return Optional.of("Hello " + capitalized);
    }
}
