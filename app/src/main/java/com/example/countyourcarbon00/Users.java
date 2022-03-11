package com.example.countyourcarbon00;
public class Users {
//https://www.youtube.com/watch?v=XactTKR0Wfc
    private String name;
    String email;
    String password;
    private String address;
    private String school;
    private String image;
    private Double result;

    public Users() {}

    public Users(String name, String email, String password, String address, String school, String image, Double result) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.school = school;
        this.image = image;
        this.result = result;
    }

    public Users(String name, String email, String password, String school, String address, double resultValue) {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }*/

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
