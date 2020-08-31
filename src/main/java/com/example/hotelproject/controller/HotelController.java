package com.example.hotelproject.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.cloudinary.utils.ObjectUtils;
import com.example.hotelproject.config.Singleton;
import com.example.hotelproject.entity.Hotel;
import com.example.hotelproject.entity.Photo;
import com.example.hotelproject.entity.PhotoUpload;
import com.example.hotelproject.entity.Prefecture;
import com.example.hotelproject.form.HotelForm;
import com.example.hotelproject.repository.HotelRepository;
import com.example.hotelproject.repository.PhotoRepository;
import com.example.hotelproject.repository.PrefectureRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hotels")
public class HotelController {
	@Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private PrefectureRepository prefectureRepository;

    @GetMapping
    public String index(Model model) {
        List<Hotel> hotels = hotelRepository.findAll();

        model.addAttribute("hotels", hotels);
        model.addAttribute("title", "Hotel Index");
        return "hotel/index";
	}
	
	@GetMapping("new")
    public String newHotel(Model model) {
        model.addAttribute("title", "New Hotel");
        return "hotel/new";
    }

    @PostMapping
    public String create(@ModelAttribute HotelForm hotelForm) throws IOException {
        Hotel hotel = new Hotel();
        hotel.setName(hotelForm.getName());
        hotel.setDescription(hotelForm.getDescription());
        hotel.setPrefecture(prefectureRepository.findById(hotelForm.getPrefectureId()).orElse(null));
        PhotoUpload photoUpload = new PhotoUpload();
        Map<?, ?> uploadResult = null;
        if (hotelForm.getFile() != null && !hotelForm.getFile().isEmpty()) {
            uploadResult = Singleton.getCloudinary().uploader().upload(hotelForm.getFile().getBytes(),
                    ObjectUtils.asMap("resource_type", "auto"));
            photoUpload.setPublicId((String) uploadResult.get("public_id"));
            Object version = uploadResult.get("version");
            if (version instanceof Integer) {
                photoUpload.setVersion(Long.valueOf((Integer) version));
            } else {
                photoUpload.setVersion((Long) version);
            }

            photoUpload.setSignature((String) uploadResult.get("signature"));
            photoUpload.setFormat((String) uploadResult.get("format"));
            photoUpload.setResourceType((String) uploadResult.get("resource_type"));
        }

        Photo photo = new Photo();
        photo.setHotel(hotel);
        photo.setUpload(photoUpload);

        hotelRepository.save(hotel);
        photoRepository.save(photo);
        return "redirect:/hotels";
    }

    @GetMapping("{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Hotel hotel = hotelRepository.findById(id).orElse(null);
        model.addAttribute("hotel", hotel);
        model.addAttribute("title", "Edit Hotel");
        return "hotel/edit";
    }

    @PutMapping("{id}")
    public String update(@PathVariable Long id, @ModelAttribute Hotel hotel) {
        hotelRepository.save(hotel);
        Prefecture prefecture = prefectureRepository.findById(hotel.getPrefectureId()).orElse(null);
        hotel.setPrefecture(prefecture);
        hotelRepository.save(hotel);
        return "redirect:/hotels";
    }

    @GetMapping("{id}")
    public String show(@PathVariable Long id, Model model) {
        Hotel hotel = hotelRepository.findById(id).orElse(null);
        model.addAttribute("hotel", hotel);
        model.addAttribute("title", "Show Hotel");
        return "hotel/show";
    }

    @DeleteMapping("{id}")
    public String destroy(@PathVariable Long id) {
        hotelRepository.deleteById(id);
        return "redirect:/hotels";
    }
}