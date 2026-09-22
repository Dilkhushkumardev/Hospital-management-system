# Architecture Decision Records (DECISIONS.md)

## ADR-001: Centralized JDBC Connection Architecture
- **Status:** Accepted
- **Context:** Individual UI frames previously managed ad-hoc database connections, leading to connection leaks and redundant driver registrations.
- **Decision:** Implement a centralized `conn.java` class with singleton statement management and clear initialization error logging.
- **Consequences:** Eliminates boilerplate connection code across forms, centralizes credential management, and provides uniform failure handling.

---

## ADR-002: Modular Undecorated Windows with Draggable Custom Title Bars
- **Status:** Accepted
- **Context:** Sub-screens opened from the central reception hub require a cohesive, clean card experience without jarring operating system borders, while maintaining the ability for administrators to reposition windows freely across multi-monitor setups.
- **Decision:** Implement `Theme.ModernTitleBar` supporting mouse drag motion listeners and hover-reactive close triggers across all modal screens (`NEW_PATIENT`, `Room`, `Patient_Discharge`, etc.) combined with `setUndecorated(true)`.
- **Consequences:** Provides a desktop software feel (similar to modern productivity applications) with intuitive window positioning and clean lifecycle disposal.

---

## ADR-003: Modern UI Component & Design System Engine (`Theme.java`)
- **Status:** Accepted
- **Context:** Native Java Swing defaults look dated with pixelated fonts, harsh borders, and inconsistent component alignment across different resolutions.
- **Decision:** Create a centralized `Theme.java` component engine that activates 2D rendering hints (`KEY_TEXT_ANTIALIASING`, `KEY_ANTIALIASING`), defines cohesive medical color tokens, provides rounded buttons with smooth hover animations, padded text fields with focus highlight rings, and styled `JTable` rendering with zebra striping and custom headers.
- **Consequences:** Guarantees a cohesive, visually appealing aesthetic across all 10 application views with minimal code duplication.
