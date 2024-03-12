package com.cloudbees.seatallocationsystem.testutil;

import com.cloudbees.seatallocationsystem.dto.request.SeatAllocationDTO;
import com.cloudbees.seatallocationsystem.model.SeatAllocationReceipt;
import com.cloudbees.seatallocationsystem.model.User;

public class SeatAllocationTestUtil {

    private SeatAllocationTestUtil(){}


    public static User getUser(){
        User user = new User("Pavithra","Nagarajan","pavithra123@gmail.com");
        return user;
    }
    public static SeatAllocationReceipt getSeatAllocationReceipt() {
        SeatAllocationReceipt seatAllocationReceipt = new SeatAllocationReceipt();
        seatAllocationReceipt.setId(1L);
        seatAllocationReceipt.setUser(getUser());
        seatAllocationReceipt.setFromStation("London");
        seatAllocationReceipt.setToStation("France");
        seatAllocationReceipt.setSeatNumber(2133L);
        seatAllocationReceipt.setSection("A");
                return seatAllocationReceipt;
    }
    public static SeatAllocationDTO getSeatAllocationDTO(){
        SeatAllocationDTO seatAllocationDTO = new SeatAllocationDTO();
        seatAllocationDTO.setSeatNumber(332L);
        seatAllocationDTO.setSection("A");
        seatAllocationDTO.setUserEmailId("pavithra123@gmail.com");
        return  seatAllocationDTO;
    }
}
