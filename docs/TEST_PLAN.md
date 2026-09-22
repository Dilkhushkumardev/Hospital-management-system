# Quality Assurance & Test Plan (TEST_PLAN.md)

## 1. Scope & Objective
This test plan defines verification criteria for the **Hospital Management System (HMS)** desktop application to ensure reliable authentication, data persistence, room synchronization, and accurate billing computations.

---

## 2. Test Scenarios & Verification Matrix

### 🔐 Authentication (`Login.java`)
| ID | Test Scenario | Expected Outcome | Status |
| :--- | :--- | :--- | :--- |
| **AUTH-001** | Valid admin login credentials | Closes Login window, opens Reception dashboard. | Pass |
| **AUTH-002** | Invalid password or user ID | Displays `"Invalid"` error modal; remains on Login. | Pass |
| **AUTH-003** | Cancel button clicked | Terminates application process safely (`System.exit`). | Pass |

### 📝 Patient Admission & Room Sync (`NEW_PATIENT.java`)
| ID | Test Scenario | Expected Outcome | Status |
| :--- | :--- | :--- | :--- |
| **ADM-001** | Fill all valid fields & select room | Inserts record into `patient_info`; updates room to `'Occupied'`; shows `"Added Successfully"`. | Pass |
| **ADM-002** | Check room list dropdown | Dropdown only lists rooms available in the `room` table. | Pass |

### 🛏️ Room Management & Search (`Room.java`, `Search_Room.java`)
| ID | Test Scenario | Expected Outcome | Status |
| :--- | :--- | :--- | :--- |
| **ROOM-001**| Open Room directory | Displays all rooms in a populated `JTable` with price and bed type. | Pass |
| **ROOM-002**| Filter by `Available` | Shows only unoccupied rooms. | Pass |
| **ROOM-003**| Filter by `Occupied` | Shows only currently assigned rooms. | Pass |

### 💰 Billing & Update Details (`update_patient_details.java`)
| ID | Test Scenario | Expected Outcome | Status |
| :--- | :--- | :--- | :--- |
| **BILL-001**| Click `CHECK` for patient | Automatically computes `Pending Amount = Room Price - Deposit`. | Pass |
| **BILL-002**| Modify deposit & click `Update` | Updates `patient_info` with new amount; shows `"Update Successfully"`. | Pass |

### 🚪 Patient Discharge (`Patient_Discharge.java`)
| ID | Test Scenario | Expected Outcome | Status |
| :--- | :--- | :--- | :--- |
| **DISC-001**| Select patient ID and click `Check` | Retrieves patient's room number and admission time. | Pass |
| **DISC-002**| Click `Discharge` | Deletes patient record; sets room availability to `'Available'`; shows `"Done"`. | Pass |

### 📋 Staff, Departments & Ambulance
| ID | Test Scenario | Expected Outcome | Status |
| :--- | :--- | :--- | :--- |
| **DIR-001** | Open Employee Info | Populates all employee names, roles, salaries, and contact info. | Pass |
| **DIR-002** | Open Department List | Displays department phone directory. | Pass |
| **DIR-003** | Open Ambulance Fleet | Displays real-time driver names, availability, and location zones. | Pass |
