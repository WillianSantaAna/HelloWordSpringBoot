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

    private String ucs[] = { "OOP", "DB", "OS", "DM", "CK", "TE1" };
    private double grades[] = { 20, 20, 18, 16, 16, 14 };

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getGreeting() {
        logger.info("Saying Java Tester");
        return "Java Tester";
    }

    @GetMapping(path = "/author", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAuthor() {
        String name = "Willian SantaAna";
        int number = 20190919;
        double height = 1.72;
        boolean footballFan = false;
        char footballColor = ' ';
        String otherInformation = "I'm Programmer";

        String info = "Done by " + name + " with number " + number + ".\n";
        info += "I am " + height + " tall and ";
        
        if (footballFan) {
            info += "I am a fan of football.\nMy favorite club is ";

            switch (Character.toLowerCase(footballColor)){
                case 'r':
                    info += "Benfica\n";
                break;
                case 'g':
                    info += "Sporting\n";
                break;
                case 'b':
                    info += "Porto\n";
                break;
            }
        } else {
            info += "not a fan of football.\n";
        }

        if (!otherInformation.isEmpty())
            info += "And " + otherInformation;

        return info;
    }

    @GetMapping(path = "/access/{student}/{covid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean getGreeting(@PathVariable("student") boolean isStudent, @PathVariable("covid") boolean hasCovid) {
        logger.info("Is student: " + isStudent + ", has covid: " + hasCovid);
        return isStudent && !hasCovid;
    }

    @GetMapping(path = "/required/{student}/{temperature}/{classType}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean getRequired(@PathVariable("student") boolean isStudent,
            @PathVariable("temperature") double temperature, @PathVariable("classType") String type) {
        logger.info("Is student: " + isStudent + ", temperature: " + temperature + ", class type: " + type);
        return isStudent && (temperature > 34.5 && temperature < 37.5) && (type.equals("presential"));
    }

    @GetMapping(path = "/evacuation/{fire}/{numberOfCovids}/{powerShutdown}/{comeBackTime}/", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean getEvacuation(@PathVariable("fire") boolean fire, @PathVariable("numberOfCovids") int numberOfCovids,
            @PathVariable("powerShutdown") boolean powerShutdown, @PathVariable("comeBackTime") int comeBackTime) {
        logger.info("Fire " + fire + ", number of covids: " + numberOfCovids + ", power shutdown: " + powerShutdown
                + ", come back time: " + comeBackTime);
        return fire && (numberOfCovids > 5 || powerShutdown) && comeBackTime > 15;
    }
}
