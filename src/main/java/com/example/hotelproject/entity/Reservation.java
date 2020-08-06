package com.example.hotelproject.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Reservation {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	private String name;
	private String email;
	private Date startDate;
	private Date endDate;
    private Long numOfPeople;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Long getNumOfPeople() {
		return numOfPeople;
	}

	public void setNumOfPeople(Long numOfPeople) {
		this.numOfPeople = numOfPeople;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
}