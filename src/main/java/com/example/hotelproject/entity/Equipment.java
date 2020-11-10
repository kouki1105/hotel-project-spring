package com.example.hotelproject.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Boolean parking;
    private Boolean hotSpring;
    private Boolean outdoorBath;
    private Boolean wifi;

    @OneToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Boolean getParking() {
		return parking;
	}

	public void setParking(Boolean parking) {
		this.parking = parking;
	}

	public Boolean getHotSpring() {
		return hotSpring;
	}

	public void setHotSpring(Boolean hotSpring) {
		this.hotSpring = hotSpring;
	}

	public Boolean getOutdoorBath() {
		return outdoorBath;
	}

	public void setOutdoorBath(Boolean outdoorBath) {
		this.outdoorBath = outdoorBath;
	}

	public Boolean getWifi() {
		return wifi;
	}

	public void setWifi(Boolean wifi) {
		this.wifi = wifi;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

}
