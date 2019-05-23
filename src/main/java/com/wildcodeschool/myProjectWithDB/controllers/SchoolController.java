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

    @GetMapping("/api/school")
    public List<School> getSchool(@RequestParam(defaultValue = "%") String country) {
        return SchoolRepository.selectByCountry(country);
    }
    @PostMapping("/api/school")
    @ResponseStatus(HttpStatus.CREATED)
    public School store(
            @RequestParam String name,
            @RequestParam Integer capacity,
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
    @PutMapping("/api/school/{id}")
    public School update(
            @PathVariable int id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer capacity,
            @RequestParam(required = false) String country
    ) {
        School school = SchoolRepository.selectById(id);
        SchoolRepository.update(
                id,
                name != null ? name : school.getName(),
                capacity != null ? capacity : school.getCapacity(),
                country != null ? country : school.getCountry()
        );
        return SchoolRepository.selectById(id);
    }
}
