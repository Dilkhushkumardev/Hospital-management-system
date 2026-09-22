# Development & AI Rules (RULES.md)

## 1. General Principles
- **Target Language:** Java 8+ / Java 11+.
- **GUI Framework:** Java Swing & AWT. Avoid unnecessary third-party heavy GUI frameworks to maintain lightweight native performance.
- **Database:** MySQL 8.0+ using JDBC.
- **Do Not Break Working Flows:** Always preserve existing working database schemas and method signatures.
- **Preserve Comments & Formatting:** Keep existing comments intact unless explicitly tasked to refactor them.

---

## 2. Before Coding
1. **Understand Context:** Read `docs/PRD.md`, `docs/ARCHITECTURE.md`, `docs/DESIGN.md`, and `docs/TASKS.md` before making architectural modifications.
2. **Inspect Existing Code:** Verify existing table column names in `conn.java` and SQL scripts prior to running or writing queries.
3. **Plan Small Changes:** Make atomic, focused changes rather than large rewrites.

---

## 3. Coding & UI Guidelines
- **Consistent Styling:** Adhere strictly to the color palette and typography defined in `docs/DESIGN.md`.
- **Event Handling:** All interactive Swing elements (`JButton`, `JComboBox`, `Choice`) must implement clean `ActionListener` callbacks.
- **Null Safety & Exceptions:** Wrap JDBC calls in `try-catch (Exception e)` blocks and log errors with `e.printStackTrace()` or user dialog warnings (`JOptionPane.showMessageDialog`).

---

## 4. Database & Persistence Standards
- **Use Parameterized / Sanitized Queries:** Guard against SQL injection when refactoring.
- **Synchronized Room Status:** Whenever patient records are created or deleted, synchronize the corresponding `room` table's `Availability` column (`'Occupied'` vs `'Available'`).
- **Resource Cleanup:** Always close or handle `ResultSet` and `Statement` instances gracefully.

---

## 5. Git & Version Control Rules
- **Commit Frequency:** Commit after completing each discrete task or fixing an individual issue.
- **Conventional Commits:**
  - `feat:` for new UI modules or database additions
  - `fix:` for bug fixes in SQL queries or layout coordinates
  - `docs:` for documentation updates
  - `style:` for visual styling/color tweaks
  - `refactor:` for code restructuring without feature changes
