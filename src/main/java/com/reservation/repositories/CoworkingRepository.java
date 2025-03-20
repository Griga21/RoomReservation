package com.reservation.repositories;

import com.reservation.entity.Coworking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoworkingRepository extends JpaRepository<Coworking, Integer> {
}
