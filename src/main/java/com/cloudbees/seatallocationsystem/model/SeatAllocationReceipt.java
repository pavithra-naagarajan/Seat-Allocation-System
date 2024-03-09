package com.cloudbees.seatallocationsystem.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class SeatAllocationReceipt implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String fromStation;
    private String toStation;
    private BigDecimal price;
    private String section;
    private Long seatNumber;
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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


    public void setUser(User user) {
        this.user = user;
    }

    public Long getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Long seatNumber) {
        this.seatNumber = seatNumber;
    }

    @Override
    public String toString() {
        return "SeatAllocationReceipt{" +
                "id=" + id +
                ", fromStation='" + fromStation + '\'' +
                ", toStation='" + toStation + '\'' +
                ", price=" + price +
                ", section='" + section + '\'' +
                ", seatNumber=" + seatNumber +
                ", user=" + user +
                '}';
    }

}
