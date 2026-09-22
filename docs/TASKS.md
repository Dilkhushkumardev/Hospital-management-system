# Project Tasks & Roadmap (TASKS.md)

## Phase 1: Environment & Database Setup
- [x] Configure MySQL Database `hospital_management_system`
- [x] Create core SQL tables (`login`, `patient_info`, `room`, `EMP_INFO`, `department`, `Ambulance`)
- [x] Configure JDBC Connector `mysql-connector-java-8.0.28.jar`
- [x] Configure Data Adapter `ResultSet2xml.jar`
- [x] Set up centralized connection class [conn.java](file:///d:/MY%20PROJECT/H.M.S/Hospital%20management%20system/src/hospital/management/system/conn.java)

## Phase 2: Core Authentication & Reception Navigation
- [x] Build [Login.java](file:///d:/MY%20PROJECT/H.M.S/Hospital%20management%20system/src/hospital/management/system/Login.java) authentication interface
- [x] Build [Reception.java](file:///d:/MY%20PROJECT/H.M.S/Hospital%20management%20system/src/hospital/management/system/Reception.java) dashboard hub with 10 action routes
- [x] Implement secure logout and session return

## Phase 3: Patient Admission & Room Allocation
- [x] Build [NEW_PATIENT.java](file:///d:/MY%20PROJECT/H.M.S/Hospital%20management%20system/src/hospital/management/system/NEW_PATIENT.java) form with ID type selection
- [x] Implement dynamic room dropdown loading only active rooms
- [x] Synchronize room status update to `Occupied` upon patient admission
- [x] Real-time admission timestamp capture

## Phase 4: Room Management & Search
- [x] Build [Room.java](file:///d:/MY%20PROJECT/H.M.S/Hospital%20management%20system/src/hospital/management/system/Room.java) table view with DbUtils
- [x] Build [Search_Room.java](file:///d:/MY%20PROJECT/H.M.S/Hospital%20management%20system/src/hospital/management/system/Search_Room.java) with live availability filter (`Available` / `Occupied`)

## Phase 5: Billing & Patient Record Updates
- [x] Build [update_patient_details.java](file:///d:/MY%20PROJECT/H.M.S/Hospital%20management%20system/src/hospital/management/system/update_patient_details.java)
- [x] Implement real-time pending bill calculator (`Room Tariff - Deposit`)
- [x] Implement record update query into MySQL database

## Phase 6: Patient Discharge & Room Release
- [x] Build [Patient_Discharge.java](file:///d:/MY%20PROJECT/H.M.S/Hospital%20management%20system/src/hospital/management/system/Patient_Discharge.java)
- [x] Compare In-time and real-time Out-time
- [x] Delete active patient record and reset room status to `Available`

## Phase 7: Directories & Emergency Services
- [x] Build [Employee_info.java](file:///d:/MY%20PROJECT/H.M.S/Hospital%20management%20system/src/hospital/management/system/Employee_info.java) staff directory
- [x] Build [Department.java](file:///d:/MY%20PROJECT/H.M.S/Hospital%20management%20system/src/hospital/management/system/Department.java) department contact list
- [x] Build [Ambulance.java](file:///d:/MY%20PROJECT/H.M.S/Hospital%20management%20system/src/hospital/management/system/Ambulance.java) ambulance tracker
- [x] Build [All_Patient_Info.java](file:///d:/MY%20PROJECT/H.M.S/Hospital%20management%20system/src/hospital/management/system/All_Patient_Info.java) master records table

## Phase 8: Modern UI/UX Redesign & Theme Engine
- [x] Create [Theme.java](file:///d:/MY%20PROJECT/H.M.S/Hospital%20management%20system/src/hospital/management/system/Theme.java) central design token and modern component engine
- [x] Redesign [Login.java](file:///d:/MY%20PROJECT/H.M.S/Hospital%20management%20system/src/hospital/management/system/Login.java) with split medical hero card and keyboard shortcuts
- [x] Redesign [Reception.java](file:///d:/MY%20PROJECT/H.M.S/Hospital%20management%20system/src/hospital/management/system/Reception.java) as a modern Command Center with live clock and categorized cards
- [x] Upgrade all dialogs with draggable title bars (`ModernTitleBar`), auto-centering, and styled inputs
- [x] Modernize data tables with high-contrast headers, zebra striping, and styled scroll panes

## Phase 9: Documentation & Repository Polish
- [x] Write rich, professional `README.md` with system architecture diagrams
- [x] Set up complete `docs/` documentation suite following the Vibe Coding Guide (PRD, ARCHITECTURE, DESIGN, RULES, TASKS, DECISIONS, MEMORY, TEST_PLAN, SECURITY)
- [x] Create `.env.example` configuration template

## Phase 10: Future Roadmap & Enhancements
- [ ] Implement PreparedStatement across all query executions for advanced SQL sanitization
- [ ] Add PDF Invoice generation for checkout receipts
- [ ] Add doctor appointment scheduling calendar
