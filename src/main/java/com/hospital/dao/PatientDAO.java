package com.hospital.dao;

import com.hospital.model.Patient;
import com.hospital.model.enums.BloodGroup;
import com.hospital.model.enums.Gender;
import com.hospital.util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Patient operations
 */
public class PatientDAO {

    /**
     * Add a new patient to the database
     */
    public boolean addPatient(Patient patient) {
        String sql = "INSERT INTO patients (name, age, gender, phone, email, address, disease, " +
                "blood_group, emergency_contact, admission_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, patient.getName());
            pstmt.setInt(2, patient.getAge());

            if (patient.getGender() != null) {
                pstmt.setString(3, patient.getGender().name());
            } else {
                pstmt.setString(3, Gender.OTHER.name());
            }

            pstmt.setString(4, patient.getPhone());
            pstmt.setString(5, patient.getEmail());
            pstmt.setString(6, patient.getAddress());
            pstmt.setString(7, patient.getDisease());

            if (patient.getBloodGroup() != null) {
                pstmt.setString(8, patient.getBloodGroup().getLabel());
            } else {
                pstmt.setNull(8, Types.VARCHAR);
            }

            pstmt.setString(9, patient.getEmergencyContact());
            pstmt.setDate(10, patient.getAdmissionDate() != null ? Date.valueOf(patient.getAdmissionDate())
                    : Date.valueOf(LocalDate.now()));

            int result = pstmt.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Get all patients from the database
     */
    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patients ORDER BY patient_id DESC";

        try (Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                patients.add(mapRowToPatient(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patients;
    }

    /**
     * Get patient by ID
     */
    public Patient getPatientById(int patientId) {
        String sql = "SELECT * FROM patients WHERE patient_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, patientId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return mapRowToPatient(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Update patient information
     */
    public boolean updatePatient(Patient patient) {
        String sql = "UPDATE patients SET name = ?, age = ?, gender = ?, phone = ?, email = ?, " +
                "address = ?, disease = ?, blood_group = ?, emergency_contact = ?, " +
                "admission_date = ? WHERE patient_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, patient.getName());
            pstmt.setInt(2, patient.getAge());

            pstmt.setString(3, patient.getGender().name());

            pstmt.setString(4, patient.getPhone());
            pstmt.setString(5, patient.getEmail());
            pstmt.setString(6, patient.getAddress());
            pstmt.setString(7, patient.getDisease());

            if (patient.getBloodGroup() != null) {
                pstmt.setString(8, patient.getBloodGroup().getLabel());
            } else {
                pstmt.setNull(8, Types.VARCHAR);
            }

            pstmt.setString(9, patient.getEmergencyContact());
            pstmt.setDate(10, patient.getAdmissionDate() != null ? Date.valueOf(patient.getAdmissionDate()) : null);
            pstmt.setInt(11, patient.getPatientId());

            int result = pstmt.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Delete patient by ID
     */
    public boolean deletePatient(int patientId) {
        String sql = "DELETE FROM patients WHERE patient_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, patientId);
            int result = pstmt.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Search patients by name or phone
     */
    public List<Patient> searchPatients(String searchTerm) {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patients WHERE name LIKE ? OR phone LIKE ? ORDER BY name";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            String searchPattern = "%" + searchTerm + "%";
            pstmt.setString(1, searchPattern);
            pstmt.setString(2, searchPattern);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                patients.add(mapRowToPatient(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patients;
    }

    /**
     * Helper method to map ResultSet to Patient object
     */
    private Patient mapRowToPatient(ResultSet rs) throws SQLException {
        Patient patient = new Patient();
        patient.setPatientId(rs.getInt("patient_id"));
        patient.setName(rs.getString("name"));
        patient.setAge(rs.getInt("age"));

        try {
            String genderStr = rs.getString("gender");
            if (genderStr != null) {
                patient.setGender(Gender.valueOf(genderStr.toUpperCase()));
            } else {
                patient.setGender(Gender.OTHER);
            }
        } catch (IllegalArgumentException e) {
            patient.setGender(Gender.OTHER);
        }

        patient.setPhone(rs.getString("phone"));
        patient.setEmail(rs.getString("email"));
        patient.setAddress(rs.getString("address"));
        patient.setDisease(rs.getString("disease"));

        String bgStr = rs.getString("blood_group");
        if (bgStr != null) {
            patient.setBloodGroup(BloodGroup.fromString(bgStr));
        }

        patient.setEmergencyContact(rs.getString("emergency_contact"));

        Date admissionDate = rs.getDate("admission_date");
        if (admissionDate != null) {
            patient.setAdmissionDate(admissionDate.toLocalDate());
        }

        return patient;
    }
}