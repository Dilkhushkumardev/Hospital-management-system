# System Architecture Document

## 1. Architectural Overview

The **Hospital Management System (HMS)** follows a 2-Tier Desktop Client-Server architecture, leveraging an event-driven presentation layer with direct JDBC relational database connectivity.

```
┌────────────────────────────────────────────────────────┐
│                   Desktop Client (UI)                  │
│             Java Swing (AWT / EventQueue)              │
│  [Login] ──> [Reception] ──> [Sub-Window Modules]       │
└──────────────────────────┬─────────────────────────────┘
                           │
                 SQL Queries / Updates
                 via JDBC Connection
                           │
                           ▼
┌────────────────────────────────────────────────────────┐
│            Data Access & Connection Layer              │
│       conn.java (DriverManager, Connection, Statement) │
│       Libraries: mysql-connector-java, ResultSet2xml   │
└──────────────────────────┬─────────────────────────────┘
                           │
                  TCP/IP Port 3306
                           │
                           ▼
┌────────────────────────────────────────────────────────┐
│               Relational Database Engine               │
│                   MySQL Server 8.0+                    │
│     Database: `hospital_management_system`             │
│     Tables: login, patient_info, room, EMP_INFO, ...   │
└────────────────────────────────────────────────────────┘
```

---

## 2. Technology Stack & Component Responsibilities

| Layer | Component | Implementation | Responsibility |
| :--- | :--- | :--- | :--- |
| **Presentation Layer** | UI Frames & Forms | Java Swing (`JFrame`, `JPanel`, `JButton`, `JTextField`, `JTable`, `Choice`, `JComboBox`) | Renders graphical forms, captures user interaction, triggers action events. |
| **Data Binding Layer** | Result Set Adapter | `ResultSet2xml.jar` (`net.proteanit.sql.DbUtils`) | Binds JDBC SQL `ResultSet` directly to Swing `JTable` models with minimal boilerplate. |
| **Connectivity Layer** | JDBC Driver | `mysql-connector-java-8.0.28.jar` | Manages low-level database communication over TCP/IP socket to MySQL port 3306. |
| **Connection Manager** | Central DB Access | `conn.java` | Instantiates `java.sql.Connection` and `java.sql.Statement` instances for all UI modules. |
| **Persistence Layer** | Relational Database | MySQL 8.0+ (`hospital_management_system`) | Stores structured records with referential and data integrity. |

---

## 3. Directory & Package Structure

```plaintext
Hospital management system/
├── docs/                                  # Comprehensive System Documentation
│   ├── PRD.md                             # Product Requirements Document
│   ├── ARCHITECTURE.md                    # Technical Architecture
│   ├── DESIGN.md                          # UI/UX & Style Guide
│   ├── RULES.md                           # AI & Developer Rules
│   ├── TASKS.md                           # Phased Implementation Checklist
│   ├── DECISIONS.md                       # Architectural Decision Records (ADRs)
│   ├── MEMORY.md                          # Living Project State & History
│   ├── TEST_PLAN.md                       # Test Strategy & Quality Assurance
│   └── SECURITY.md                        # Security & Access Guidelines
├── src/
│   ├── icon/                              # Graphical Assets (PNG Icons)
│   │   ├── am.png                         # Ambulance Graphic
│   │   ├── dr.png                         # Doctor Graphic
│   │   ├── login.png                      # Login Illustration
│   │   ├── patient.png                    # Patient Emblem
│   │   ├── romm.png                       # Room Management Asset
│   │   └── updated.png                    # Update Graphic
│   └── hospital/management/system/
│       ├── conn.java                      # DB Connection Handler
│       ├── Login.java                     # Auth & Entry Window
│       ├── Reception.java                 # Central Dashboard
│       ├── NEW_PATIENT.java               # Patient Intake Module
│       ├── Room.java                      # Room Roster
│       ├── Search_Room.java               # Room Status Search
│       ├── Department.java                # Department Directory
│       ├── Employee_info.java             # Staff Roster
│       ├── All_Patient_Info.java          # Patient Master Records
│       ├── update_patient_details.java    # Billing & Patient Updater
│       ├── Patient_Discharge.java         # Patient Checkout
│       └── Ambulance.java                 # Emergency Fleet Tracker
├── mysql-connector-java-8.0.28.jar        # MySQL JDBC Driver
├── ResultSet2xml.jar                      # DB Table Model Adapter
└── README.md                              # Public Repository Documentation
```

---

## 4. Architectural Rules & Best Practices

1. **Atomic Room State Transitions:**
   - When a patient is added (`NEW_PATIENT.java`), the room status must be updated to `'Occupied'` in the same transaction flow.
   - When a patient is discharged (`Patient_Discharge.java`), the room status must be updated to `'Available'`.
2. **Centralized Connection Access:**
   - All modules instantiate `conn c = new conn()` to reuse the centralized JDBC connection pool setup.
3. **Graceful Resource & Window Management:**
   - Sub-windows are opened non-destructively or cleanly disposed upon `Back` button interaction (`setVisible(false)`).
4. **Data Isolation:**
   - Passwords and sensitive Aadhar identifiers are stored securely and rendered with proper UI controls (`JPasswordField`).
