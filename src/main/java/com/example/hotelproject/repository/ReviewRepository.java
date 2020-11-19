package com.example.hotelproject.repository;

import java.util.List;

import com.example.hotelproject.entity.Review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
	List<Review> findByHotelId(Long hotel_id);
	List<Review> findByHotelIdOrderByIdDesc(Long hotel_id);
	List<Review> findFirst6ByHotelIdOrderByIdDesc(Long hotel_id);

	// public static double RatingSum(double[]Review) {
	// 	double sum = 0;
	// 	for (double rating : Review) {
	// 		sum += rating;
	// 	}
	// 	return sum;
	// }

	// public static double RatingAve(double[]Review) {
	// 	double sum = RatingSum(Review);
	// 	return (double) sum/Review.length;
	// }
}