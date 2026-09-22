# Architecture & Technical Decision Records (ADRs)

## ADR-001: Direct JDBC vs ORM Framework (Hibernate)
- **Status:** Accepted
- **Context:** The system needs high-speed desktop performance with minimal runtime memory footprint and clear direct SQL interaction.
- **Decision:** Use raw JDBC (`java.sql.Connection`, `Statement`, `ResultSet`) with `mysql-connector-java`.
- **Consequences:** 
  - Faster startup time without heavy bytecode enhancement or XML/Annotation overhead.
  - Transparent SQL execution.

---

## ADR-002: Dynamic Tabular Rendering with `ResultSet2xml / DbUtils`
- **Status:** Accepted
- **Context:** Presenting SQL result sets inside `JTable` requires verbose `AbstractTableModel` boilerplate in standard Swing.
- **Decision:** Adopt `DbUtils.resultSetToTableModel(resultSet)` from `ResultSet2xml.jar`.
- **Consequences:** 
  - Reduced boilerplate code to a single line across `Room`, `Employee_info`, `Department`, `Ambulance`, and `All_Patient_Info`.
  - Instant auto-generation of table headers and column data directly from SQL schema.

---

## ADR-003: Desktop GUI Framework (Java Swing vs JavaFX vs Web)
- **Status:** Accepted
- **Context:** Hospital receptionists require an offline-capable, standalone desktop client that runs natively on hospital workstations.
- **Decision:** Standardize on Java Swing (`javax.swing.*`) with `java.awt.*`.
- **Consequences:** 
  - Native JVM integration requiring zero external browser dependencies.
  - Predictable window coordinates and layout management.

---

## ADR-004: Synchronized Room Status State Machine
- **Status:** Accepted
- **Context:** Room availability must always remain strictly consistent with patient admissions and discharges.
- **Decision:** Execute room status update queries directly in the same workflow as patient creation (`INSERT patient` ➔ `UPDATE room SET Availability='Occupied'`) and discharge (`DELETE patient` ➔ `UPDATE room SET Availability='Available'`).
- **Consequences:**
  - Prevents double-booking of rooms.
  - Ensures accurate live status in `Search_Room.java`.
