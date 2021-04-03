package com.example.hotelproject.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.cloudinary.Transformation;
import com.example.hotelproject.config.Singleton;

@Entity
public class Hotel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String description;
	private Double ratingAve;
	// private Long prefecture_id;

	@ManyToOne
	@JoinColumn(name = "prefecture_id")
	private Prefecture prefecture;

	@OneToOne(mappedBy = "hotel", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Equipment equipment;

	@OneToMany(mappedBy="hotel")
    private List<Review> reviews;

	@OneToOne(mappedBy = "hotel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Photo photo;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Hotel() {};
	// public Hotel(String name, String description) {
	// 	this.name = name;
	// 	this.description = description;
	// };

	public String getPhotoUrl() {
		return Singleton.getCloudinary().url().generate(photo.getUpload().getPublicId());
	}

	public String getThumbnailUrl() {
		return Singleton.getCloudinary().url()
			.transformation(
				new Transformation<>().width(150).height(150).crop("thumb")
			).generate(photo.getUpload().getPublicId());
	}

	public Prefecture getPrefecture() {
		return prefecture;
	}

	public void setPrefecture(Prefecture prefecture) {
		this.prefecture = prefecture;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public Double getRatingAve() {
		return ratingAve;
	}
	
	public void setRatingAve(List<Review> reviews) {
		Double sum = 0.0;
		for (Review review : reviews) {
			sum += review.getRating();
		}
		Double ave = sum/reviews.size();
		this.ratingAve = ave;
	}

}