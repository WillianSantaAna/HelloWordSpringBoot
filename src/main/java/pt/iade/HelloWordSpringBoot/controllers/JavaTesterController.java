package pt.iade.HelloWordSpringBoot.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/java/tester")
public class JavaTesterController {
    private Logger logger = LoggerFactory.getLogger(GreeterController.class);

    @GetMapping(path = "/access/{student}/{covid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean getGreeting(@PathVariable("student") boolean isStudent, @PathVariable("covid") boolean hasCovid) {
        return isStudent && hasCovid;
    }

    @GetMapping(path = "/required/{student}/{temperature}/{classType}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean getRequired(@PathVariable("student") boolean isStudent, @PathVariable("temperature") double hasCovid,
            @PathVariable("classType") String type) {
        return isStudent && (hasCovid > 34.5 && hasCovid < 37.5) && (type.equals("presential"));
    }

    @GetMapping(path = "/evacuation/{fire}/{numberOfCovids}/{powerShutdown}/{comeBackTime}/", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean getEvacuation(@PathVariable("fire") boolean fire, @PathVariable("numberOfCovids") int numberOfCovids,
            @PathVariable("powerShutdown") boolean powerShutdown, @PathVariable("comeBackTime") int comeBackTime) {
        return fire && (numberOfCovids > 5 || powerShutdown) && comeBackTime > 15;
    }
}
