package digital.typeb.take_home_assessment.controller;

import digital.typeb.take_home_assessment.dto.ErrorDto;
import digital.typeb.take_home_assessment.dto.SuccessDto;
import digital.typeb.take_home_assessment.service.HelloWorldService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Hello World", description = "Greets names starting with A–M")
public class HelloWorldController {
    private final HelloWorldService helloWorldService;

    public HelloWorldController(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    @GetMapping("/hello-world")
    public ResponseEntity<?> helloWorld(@RequestParam(required = false) String name) {
        return helloWorldService.greet(name)
                .<ResponseEntity<?>>map(message -> ResponseEntity.ok(new SuccessDto(message)))
                .orElseGet(() -> ResponseEntity.badRequest().body(new ErrorDto("Invalid Input")));
    }
}
