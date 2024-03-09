package com.cloudbees.seatallocationsystem.util;

import com.cloudbees.seatallocationsystem.dto.request.SeatAllocationDTO;
import com.cloudbees.seatallocationsystem.model.SeatAllocationReceipt;
import com.cloudbees.seatallocationsystem.model.User;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SeatAllocationServiceUtil {
    private  SeatAllocationServiceUtil(){}

    private static List<User> userList= Arrays.asList(new User("Pavithra","Nagarajan","pavithra123@gmail.com"),
            new User("Ragul","Ravi","rag002@gmail.com"),new User("Sruthi","Sekar","sruthi@gmail.com"));

    public static SeatAllocationReceipt generateSeatAllocationReceipt(SeatAllocationDTO seatAllocation,Long id) {
        User userFound=userList.stream().filter(user->user.getEmailId().equals(seatAllocation.getUserEmailId())).findFirst().orElse(null);
        SeatAllocationReceipt seatAllocationReceipt=null;
        if(Objects.nonNull(userFound)){
            seatAllocationReceipt = new SeatAllocationReceipt();
            seatAllocationReceipt.setId(id);
            seatAllocationReceipt.setFromStation(seatAllocation.getFromStation());
            seatAllocationReceipt.setToStation(seatAllocation.getToStation());
            seatAllocationReceipt.setPrice(seatAllocation.getPrice());
            seatAllocationReceipt.setSection(seatAllocation.getSection());
            seatAllocationReceipt.setSeatNumber(seatAllocation.getSeatNumber());
            seatAllocationReceipt.setUser(userFound);
        }
        return seatAllocationReceipt;
    }
}
