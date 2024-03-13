package com.wellsfargo.utilities.crms.controller;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.utilities.crms.model.Booking;
import com.wellsfargo.utilities.crms.repository.BookingRepository;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200",methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE})
@RestController
@RequestMapping("/api/v1")
public class BookingController {
    @Autowired
    BookingRepository bookingRepository;

    @GetMapping("/GetAllBooking")
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @PostMapping("/bookings")
    public Object createBooking(@Valid @RequestBody Booking booking) {
        if(findBooking(booking).size() > 0){
            String strReturn = "{\"message\":\"meeting already scheduled\"}";
            return strReturn;
        }else{
            //booking.setToTimeId( booking.getToTimeId()==1 ? booking.getToTimeId() : booking.getToTimeId() -1 );
            booking.setMessage("Booking Scheduled successfully.");
        }

        return bookingRepository.save(booking);
    }

    @PostMapping("/findBooking")
    public List<Booking> findBooking(@Valid @RequestBody Booking booking){
        List<Booking> bookingList = getAllBookings();
        Integer roomId = booking.getRoomId();
        String bookingDate = booking.getBookingDate();
        Integer fromTimeId = booking.getFromTimeId();
        Integer toTimeId = booking.getToTimeId();

        bookingList = getAllBookings().stream()
                .filter( b-> (b.getRoomId() == roomId)  )
                .filter(b -> (b.getBookingDate().equals(bookingDate)))
                .filter(b-> (b.getFromTimeId() == fromTimeId)  )
                .filter(b -> (b.getToTimeId() == toTimeId))
                .collect(Collectors.toList());


        return bookingList;
    }


}
