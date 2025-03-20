package com.reservation.controllers;

import com.reservation.entity.Coworking;
import com.reservation.services.CoworkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coworking")
public class CoworkingController {
    private final CoworkingService coworkingService;

    @Autowired
    public CoworkingController(CoworkingService coworkingService) {
        this.coworkingService = coworkingService;
    }

    @GetMapping("/getCoworking")
    public Coworking getCoworkingById(@RequestParam("id") Integer id) {
        return coworkingService.getCoworkingById(id);
    }

    @GetMapping("/getAllCoworking")
    public List<Coworking> getAllCoworking() {
        return coworkingService.getAllCoworking();
    }

    @PostMapping("/createCoworking")
    public String createCoworking(@RequestBody Coworking coworking) {
        Integer id = coworkingService.createCoworking(coworking);
        return "Coworking with " + id + " has been created";
    }

    @PutMapping("/updateCoworking")
    public String updateCoworking(@RequestParam("id") Integer id, Coworking coworking) {
        Coworking coworkingDB = coworkingService.getCoworkingById(id);
        coworkingDB.setName(coworkingDB.getName());
        return "Coworking with " + id + " has been update";
    }

    @DeleteMapping("/deleteCoworking")
    public String deleteCoworking(@RequestParam("id") Integer id) {
        coworkingService.deleteCoworking(id);
        return "Coworking with " + id + " has been deleted";
    }
}
