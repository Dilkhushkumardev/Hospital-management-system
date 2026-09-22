# Product Requirements Document (PRD)

## 1. Product Name
**Hospital Management System (HMS)**

## 2. Problem Statement
Hospitals and healthcare clinics often struggle with manual, fragmented paperwork for patient intake, manual room tracking, billing discrepancies, disconnected employee directories, and unstructured ambulance dispatch logs. 

Staff require a centralized, fast desktop application to seamlessly register patients, manage real-time room occupancy, accurately calculate billing balance against tariffs, access hospital department contacts, track ambulance availability, and handle one-click patient checkout.

## 3. Target Users
- **Hospital Receptionists & Desk Clerks:** For admitting patients, updating records, collecting deposits, and discharging patients.
- **Hospital Administrators & Medical Directors:** For overseeing room availability, department directory extensions, staff rosters, and emergency fleets.
- **Cashier / Accounts Personnel:** For verifying deposits, room tariffs, and pending dues.

## 4. Primary Goal & Outcome
Deliver an intuitive, robust, and responsive desktop GUI application in Java Swing backed by a reliable MySQL relational database that eliminates manual paperwork and streamlines daily hospital operations.

---

## 5. Core Features & Scope

### In Scope (Core Modules):
1. **Authentication & Access Control (`Login.java`):**
   - Secure login screen validating administrative credentials against the MySQL database.
2. **Central Reception Dashboard (`Reception.java`):**
   - Main control center providing quick-action access to all 9 hospital management modules.
3. **Patient Intake & Registration (`NEW_PATIENT.java`):**
   - ID Proof verification (`Aadhar Card`, `Voter ID`, `Driving License`).
   - Patient metadata (Name, Gender, Diagnosis/Disease, Deposit Amount, Timestamp).
   - Dynamic room dropdown allocating available rooms and atomically setting room status to `Occupied`.
4. **Room Inventory & Real-Time Availability (`Room.java`, `Search_Room.java`):**
   - Tabular view of room numbers, availability, room tariff, and bed type.
   - Filter rooms dynamically by status (`Available` vs `Occupied`).
5. **Patient Record & Bill Calculator (`update_patient_details.java`):**
   - Dynamic patient picker.
   - Auto-population of room number, in-time, and previous deposit.
   - Real-time computation of **Pending Balance (Rs)** = `Room Price - Paid Deposit`.
   - Update patient records directly into the database.
6. **Patient Discharge & Auto-Vacate (`Patient_Discharge.java`):**
   - Checkout workflow with automated room release (`Occupied` ➔ `Available`).
   - Discharge timestamp logging and record archival/cleanup.
7. **Staff & Doctor Directory (`Employee_info.java`):**
   - Overview of doctors, nurses, and staff with contact details, age, salary, and Aadhar verification.
8. **Department Registry (`Department.java`):**
   - Contact extensions and department names (Cardiology, ICU, Pediatrics, Emergency, etc.).
9. **Ambulance Emergency Dispatch (`Ambulance.java`):**
   - Vehicle model, assigned driver, live availability status, and deployment location.

---

### Out of Scope (For Current Version):
- Online telemedicine / remote video consultation.
- Third-party payment gateway integration (e.g., Stripe/Razorpay) — cash/desk payments are handled via deposit tracking.
- Mobile client app (Android / iOS).
- AI-based diagnosis prediction or clinical decision support.

---

## 6. MVP Success Criteria
A user should be able to:
1. Log into the application using verified administrative credentials.
2. Register a new patient and assign an available room.
3. Verify that the assigned room is automatically marked as `Occupied`.
4. Search rooms by status (`Available` / `Occupied`).
5. Look up a patient, view their deposit, and compute remaining pending payment.
6. Discharge a patient and verify that their room status returns to `Available`.
7. View employee lists, department contacts, and ambulance fleet availability in live data tables.
