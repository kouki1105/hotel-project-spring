package com.example.hotelproject.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.cloudinary.Transformation;
import com.example.hotelproject.config.Singleton;

@Entity
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
	private String description;
	// private Long prefecture_id;

	@ManyToOne
    @JoinColumn(name = "prefecture_id")
    private Prefecture prefecture;

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
	public Hotel(String name, String description) {
		this.name = name;
		this.description = description;
	};

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

	// public Long getPrefectureId() {
	// 	return prefecture_id;
	// }

	// public void setPrefectureId(Long prefecture_id) {
	// 	this.prefecture_id = prefecture_id;
	// }
}