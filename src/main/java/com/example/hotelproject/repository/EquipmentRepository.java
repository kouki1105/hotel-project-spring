package com.example.hotelproject.repository;

import java.util.List;

import com.example.hotelproject.entity.Equipment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    List<Equipment> findByHotelId(Long hotel_id);
}
