package com.Appointment.Booking.dto;

public class DoctorResponse {

    private Long id;
    private String doctorName;
    private String doctorEmail;
    private String doctorMobileNo;
    private String experience;
    private String location;
    private String rating;
    private String schedule;
    private String cost;
    private String Password;
    private DepartmentResponse department;
    private int status;
    private String imageUrl;


    // Getters
    public Long getId() {
        return id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getDoctorEmail() {
        return doctorEmail;
    }

    public String getDoctorMobileNo() {
        return doctorMobileNo;
    }

    public String getExperience() {
        return experience;
    }

    public String getLocation() {
        return location;
    }

    public String getRating() {
        return rating;
    }

    public String getSchedule() {
        return schedule;
    }

    public String getCost() {
        return cost;
    }

    public String getPassword() {
        return Password;
    }
    public String getImageUrl() {return imageUrl; }

    public DepartmentResponse getDepartment() {
        return department;
    }

    public int getStatus() {
        return status;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public void setDoctorEmail(String doctorEmail) {
        this.doctorEmail = doctorEmail;
    }

    public void setDoctorMobileNo(String doctorMobileNo) {
        this.doctorMobileNo = doctorMobileNo;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setDepartment(DepartmentResponse department) {
        this.department = department;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setImageUrl(String imageUrl) {
          this.imageUrl = imageUrl;
}
}