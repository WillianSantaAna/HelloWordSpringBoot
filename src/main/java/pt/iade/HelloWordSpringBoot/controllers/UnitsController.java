package pt.iade.HelloWordSpringBoot.controllers;

import java.util.ArrayList;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.iade.HelloWordSpringBoot.models.CurricularUnit;

@RestController
@RequestMapping(path = "/api/units")
public class UnitsController {
    private Logger logger = LoggerFactory.getLogger(GreeterController.class);

    private ArrayList<CurricularUnit> units = new ArrayList<CurricularUnit>();

    @PostMapping(path = "")
    public CurricularUnit saveUnit(@RequestBody CurricularUnit unit) {
        logger.info("Added unit " + unit.getName());
        units.add(unit);
        return unit;
    }

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<CurricularUnit> getUnits() {
        logger.info("Get " + units.size() + " Units\n");
        logger.info("units: " + units);

        return units;
    }

    @GetMapping(path = "/average", produces = MediaType.APPLICATION_JSON_VALUE)
    public double getAverage() {
        double credits = 0;
        double sumGradesECTSs = 0;

        for (CurricularUnit unit : units) {
            sumGradesECTSs += unit.getGrade() * unit.getEcts();
            credits += unit.getEcts();
        }
        
        return credits == 0 ? 0 : sumGradesECTSs / credits;
    }

    @GetMapping(path = "/max", produces = MediaType.APPLICATION_JSON_VALUE)
    public double getMax() {
        double max = 0;

        for (CurricularUnit unit : units)
            if (unit.getGrade() > max)
                max = unit.getGrade();
        
        return max;
    }

    @GetMapping(path = "/grade/{uc}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getGrade(@PathVariable("uc") String uc) {

        for (CurricularUnit unit : units)
            if (unit.getName().equals(uc))
                return "Your grade for " + uc + " is " + Double.toString(unit.getGrade());
        
        return "UC not found";
    }

    @GetMapping(path = "/semester/{semester}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<CurricularUnit> getUCsBySemester(@PathVariable("semester") int semester) {
        ArrayList<CurricularUnit> ucs = new ArrayList<CurricularUnit>();
        
        for (CurricularUnit unit : units)
            if (unit.getSemester() == semester)
                ucs.add(unit);
        
        return ucs;
    }

    @GetMapping(path = "/betweenminmax", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<CurricularUnit> getBetweenMinMax() {
        ArrayList<CurricularUnit> ucs = new ArrayList<CurricularUnit>();
        ArrayList<Double> grades = new ArrayList<Double>();

        for (CurricularUnit unit : units)
            grades.add(unit.getGrade());
            
        Double max = Collections.max(grades);
        Double min = Collections.min(grades);

        for (CurricularUnit unit : units)   
            if (unit.getGrade() != max && unit.getGrade() != min)
                ucs.add(unit);
            
        return ucs;
    } 
}