package com.cloudbees.seatallocationsystem.dto.request;


import com.cloudbees.seatallocationsystem.model.User;

import java.io.Serializable;
import java.math.BigDecimal;

public class SeatAllocationDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private UserDTO user;
    private String fromStation;
    private String toStation;
    private BigDecimal price;
    private String section;
    private Long seatNumber;

    public String getFromStation() {
        return fromStation;
    }

    public void setFromStation(String fromStation) {
        this.fromStation = fromStation;
    }

    public String getToStation() {
        return toStation;
    }

    public void setToStation(String toStation) {
        this.toStation = toStation;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Long getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Long seatNumber) {
        this.seatNumber = seatNumber;
    }
}
