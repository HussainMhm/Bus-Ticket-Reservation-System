package com.example.schoolprojectjava;

public class Booking {
    private String busId;
    private String name;
    private String mail;
    private String phone;
    private String numberOfTickets;
    private String source;
    private String destination;
    private String depart;
    private String arrive;
    // I might change it to Date according to how i am going to fetch it out when filling info of ticket
    private String date;

    public Booking(String busId, String name, String mail, String phone, String numberOfTickets, String source, String destination, String depart, String arrive, String date) {
        this.busId = busId;
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.numberOfTickets = numberOfTickets;
        this.source = source;
        this.destination = destination;
        this.depart = depart;
        this.arrive = arrive;
        this.date = date;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(String numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getArrive() {
        return arrive;
    }

    public void setArrive(String arrive) {
        this.arrive = arrive;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
