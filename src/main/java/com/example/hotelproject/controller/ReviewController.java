package com.example.hotelproject.controller;

import java.util.List;

import com.example.hotelproject.entity.Hotel;
import com.example.hotelproject.entity.Review;
import com.example.hotelproject.repository.HotelRepository;
import com.example.hotelproject.repository.ReviewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hotels/{hotel_id}/reviews")
public class ReviewController {
	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private HotelRepository hotelRepository;

	@GetMapping
	public String index(@PathVariable Long hotel_id, Model model) {
		List<Review> reviews = reviewRepository.findByHotelIdOrderByIdDesc(hotel_id);
		Hotel hotel = hotelRepository.findById(hotel_id).orElse(null);
		model.addAttribute("reviews", reviews);
		model.addAttribute("hotel", hotel);
		model.addAttribute("title", "Review Index");
		return "review/index";
	}

	@GetMapping("new")
	public String newReview(@PathVariable Long hotel_id, Model model) {
		Hotel hotel = hotelRepository.findById(hotel_id).orElse(null);
		model.addAttribute("hotel", hotel);
		model.addAttribute("title", "New Review");
		return "review/new";
	}

	@PostMapping
	public String create(@PathVariable Long hotel_id, @ModelAttribute @Validated Review review, BindingResult bindingResult) {
		Hotel hotel = hotelRepository.findById(hotel_id).orElse(null);
		if (bindingResult.hasErrors()) {
			return "review/new";
		}
		review.setHotel(hotel);
		reviewRepository.save(review);
		List<Review> reviews = reviewRepository.findByHotelId(hotel_id);
		hotel.setRatingAve(reviews);
		hotelRepository.save(hotel);
		return "redirect:/hotels/{hotel_id}/reviews";
	}

	@GetMapping("{id}/edit")
	public String edit(@PathVariable Long hotel_id,@PathVariable Long id, Model model) {
		Hotel hotel = hotelRepository.findById(hotel_id).orElse(null);
		Review review = reviewRepository.findById(id).orElse(null);
		model.addAttribute("review", review);
		model.addAttribute("hotel", hotel);
		model.addAttribute("title", "Edit Review");
		return "review/edit";
	}

	@PutMapping("{id}")
	public String update(@PathVariable Long hotel_id,@PathVariable Long id, @ModelAttribute Review review) {
		Hotel hotel = hotelRepository.findById(hotel_id).orElse(null);
		review.setHotel(hotel);
		review.setId(id);
		hotelRepository.save(hotel);
		return "redirect:/hotels/{hotel_id}/reviews";
	}

	@GetMapping("{id}")
	public String show(@PathVariable Long hotel_id,@PathVariable Long id, Model model) {
		Hotel hotel = hotelRepository.findById(hotel_id).orElse(null);
		Review review = reviewRepository.findById(id).orElse(null);
		model.addAttribute("review", review);
		model.addAttribute("hotel", hotel);
		model.addAttribute("title", "Show Review");
		return "review/show";
	}

	@DeleteMapping("{id}")
	public String destroy(@PathVariable Long id) {
		reviewRepository.deleteById(id);
		return "redirect:/hotels/{hotel_id}/reviews";
	}
}