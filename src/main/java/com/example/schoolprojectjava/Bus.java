package com.example.schoolprojectjava;

import java.sql.Date;

public class Bus {

    private String busId, busSource, busDestination;
    private int ticketPrice;
    private String depart, arrival;
    private int totalSeats, availableSeats;
    private Date date;

    public Bus(String id, String source, String destination, int ticketPrice, String depart, String arrival, int totalSeats, int availableSeats, Date date) {
        this.busId = id;
        this.busSource = source;
        this.busDestination = destination;
        this.ticketPrice = ticketPrice;
        this.depart = depart;
        this.arrival = arrival;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
        this.date = date;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public String getBusSource() {
        return busSource;
    }

    public void setBusSource(String busSource) {
        this.busSource = busSource;
    }

    public String getBusDestination() {
        return busDestination;
    }

    public void setBusDestination(String busDestination) {
        this.busDestination = busDestination;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
