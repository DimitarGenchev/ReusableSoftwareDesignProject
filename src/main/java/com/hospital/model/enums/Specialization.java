package com.hospital.model.enums;

public enum Specialization {
    CARDIOLOGY("Cardiology"),
    PEDIATRICS("Pediatrics"),
    ORTHOPEDICS("Orthopedics"),
    DERMATOLOGY("Dermatology"),
    NEUROLOGY("Neurology"),
    GYNECOLOGY("Gynecology"),
    PSYCHIATRY("Psychiatry"),
    RADIOLOGY("Radiology"),
    ANESTHESIOLOGY("Anesthesiology"),
    EMERGENCY_MEDICINE("Emergency Medicine"),
    INTERNAL_MEDICINE("Internal Medicine"),
    SURGERY("Surgery"),
    ONCOLOGY("Oncology"),
    OPHTHALMOLOGY("Ophthalmology"),
    ENT("ENT"),
    DENTISTRY("Dentistry"),
    GENERAL_PRACTICE("General Practice");

    private final String label;

    Specialization(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }

    public static Specialization fromString(String text) {
        if (text == null)
            return GENERAL_PRACTICE;

        for (Specialization s : Specialization.values()) {
            if (s.label.equalsIgnoreCase(text)) {
                return s;
            }
        }

        try {
            return Specialization.valueOf(text.toUpperCase());
        } catch (IllegalArgumentException e) {
            return GENERAL_PRACTICE;
        }
    }
}