package com.reservation.services;

import com.reservation.entity.Coworking;
import com.reservation.repositories.CoworkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoworkingService {
    private CoworkingRepository coworkingRepository;

    @Autowired
    public CoworkingService(CoworkingRepository coworkingRepository) {
        this.coworkingRepository = coworkingRepository;
    }

    public Coworking getCoworkingById(Integer id) {
        return coworkingRepository.findById(id).get();
    }

    public List<Coworking> getAllCoworking() {
        return coworkingRepository.findAll();
    }

    public Integer createCoworking(Coworking coworking) {
        return coworkingRepository.save(coworking).getId();
    }

    public void deleteCoworking(Integer id) {
        coworkingRepository.deleteById(id);
    }
}
