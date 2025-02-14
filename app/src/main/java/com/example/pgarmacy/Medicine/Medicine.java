package com.example.pgarmacy.Medicine;

public class Medicine {
    public int id, roof, cabin;
    public String name, company;
    public double price;


    public Medicine(int id, String name, String company, double price, int roof, int cabin) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.price = price;
        this.roof = roof;
        this.cabin = cabin;
    }

    public Medicine() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoof() {
        return roof;
    }

    public void setRoof(int roof) {
        this.roof = roof;
    }

    public int getCabin() {
        return cabin;
    }

    public void setCabin(int cabin) {
        this.cabin = cabin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPrice() {
        return String.valueOf(price);
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
