# Course Project: Reusable Software Design

This repository contains the coursework for the "Reusable Software Design" subject. The project focuses on refactoring a legacy **Hospital Management System** (Java Swing + JDBC) to improve its architectural quality.

## Goals
The main objective is to identify architectural violations and fix them by applying:
* **Clean Architecture** (Uncle Bob)
* **SOLID Principles**
* **OOP Principles**
* **High Cohesion & Low Coupling**

## Transformation
* **Before:** Logic mixed with UI, direct DB calls, hard-to-maintain code.
* **After:** Layered architecture, separated concerns, and testable business logic.

## Changes made by commits

### Commit 1: Upload the original legacy code

### Commit 2: Refactor domain models to use enums and fix UI issues

This commit addresses several critical architectural violations found in the legacy codebase, specifically targeting **Primitive Obsession** and **Data Redundancy**.

#### 🏗️ Architectural Wins (Violations Fixed)
* **Fixed Primitive Obsession:** Replaced raw `String` fields (prone to typos and invalid states like "male"/"Male") with strong-typed **Enums**. This enforces domain rules at the compiler level.
* **Fixed Data Redundancy (Normalization):** Removed `patientName`, `doctorName`, and `specialization` strings from the `Appointment` entity. The entity now holds only foreign keys (IDs), enforcing a **Single Source of Truth** and adhering to the **DRY (Don't Repeat Yourself)** principle.
* **Improved Separation of Concerns:** Moved the data transformation logic (String ↔ Enum) entirely into the DAO layer, keeping the Domain Model clean and the UI focused only on presentation.

#### 🛠️ Detailed Changes
* **Domain Layer:**
  * Introduced Enums: `Gender`, `AppointmentStatus`, `UserRole`, `BloodGroup`, `Specialization`.
  * Refactored `Patient`, `Doctor`, and `Appointment` classes to use these Enums.
  * Stripped display-only fields from the `Appointment` model to reflect the database schema accurately.
* **DAO Layer:**
  * Implemented robust **Enum Mapping** with `try-catch` blocks to handle legacy data safely (e.g., mapping unknown values to `Gender.OTHER`).
  * Added `toUpperCase()` normalization to handle case-sensitivity issues in the legacy database.
* **UI Layer:**
  * Updated `PatientDialog`, `DoctorDialog`, and `AppointmentDialog` to use type-safe `JComboBox<Enum>` instead of raw strings.
  * Fixed Swing UI glitches: Added `setOpaque(true)`/`setBorderPainted(false)` to fix invisible Action Buttons.
  * Refactored `AppointmentManagementFrame` to fetch Patient/Doctor details dynamically by ID, adapting to the normalized data model.
  * Implemented **Case-Insensitive Search** and underscore-to-space mapping for better UX in status filtering.