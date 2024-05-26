package ru.netkachev.hello_app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StopController {
    @GetMapping("/stop")
    public void stop() {
        System.exit(0);
    }
}
