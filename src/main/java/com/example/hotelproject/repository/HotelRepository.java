package com.example.hotelproject.repository;

import java.util.List;

import com.example.hotelproject.entity.Hotel;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
	List<Hotel> findByPrefectureId(Long prefecture_id);
	List<Hotel> findByNameContaining(String name);
	List<Hotel> findByNameContaining(String name, Sort sort);
	List<Hotel> findByPrefectureNameContaining(String prefectureName);
}