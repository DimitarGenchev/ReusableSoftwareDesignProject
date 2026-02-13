package com.hospital.model;

import com.hospital.model.enums.BloodGroup;
import com.hospital.model.enums.Gender;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Patient model class representing patient entity
 */
public class Patient {
    private int patientId;
    private String name;
    private int age;
    private Gender gender;
    private String phone;
    private String email;
    private String address;
    private String disease;
    private BloodGroup bloodGroup;
    private String emergencyContact;
    private LocalDate admissionDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Default constructor
    public Patient() {
    }

    // Constructor with essential fields
    public Patient(String name, int age, Gender gender, String phone, String disease) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.disease = disease;
    }

    // Full constructor
    public Patient(int patientId, String name, int age, Gender gender, String phone,
            String email, String address, String disease, BloodGroup bloodGroup,
            String emergencyContact, LocalDate admissionDate) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.disease = disease;
        this.bloodGroup = bloodGroup;
        this.emergencyContact = emergencyContact;
        this.admissionDate = admissionDate;
    }

    // Getters and Setters
    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId=" + patientId +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", disease='" + disease + '\'' +
                '}';
    }
}
