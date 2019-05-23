package com.wildcodeschool.myProjectWithDB.controllers;

import com.wildcodeschool.myProjectWithDB.entities.School;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.wildcodeschool.myProjectWithDB.repositories.SchoolRepository;

import java.util.List;

@Controller
@ResponseBody
public class SchoolController {

    @GetMapping("/api/schools")
    public List<School> getSchool(@RequestParam(defaultValue = "%") String country) {
        return SchoolRepository.selectByCountry(country);
    }
    @PostMapping("/api/schools")
    @ResponseStatus(HttpStatus.CREATED)
    public School store(
            @RequestParam String name,
            @RequestParam int capacity,
            @RequestParam String country
    ) {
        int idGeneratedByInsertion = SchoolRepository.insert(
                name,
                capacity,
                country

        );
        return SchoolRepository.selectById(
                idGeneratedByInsertion
        );
    }
}
