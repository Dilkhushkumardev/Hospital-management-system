# Modern Design System & UI/UX Guidelines (DESIGN.md)

## 1. Visual Philosophy & Design Principles
The Hospital Management System (HMS) UI has been modernized to deliver a **sleek, high-contrast, medical-grade administrative desktop experience**. It combines clean hospital teal accents, deep slate headers, crisp anti-aliased typography, rounded card surfaces, and seamless drag-and-drop modal navigation.

---

## 2. Color Palette & Theme Tokens

| Token Name | Hex Code / RGB Value | Visual Role | Application |
| :--- | :--- | :--- | :--- |
| **Primary Teal** | `#0D9488` / `rgb(13, 148, 136)` | 🟩 Brand Teal | Primary action buttons, active indicators, focus rings |
| **Deep Teal** | `#0F766E` / `rgb(15, 118, 110)` | 🟩 Hover Teal | Button hover states, accent borders |
| **Teal Light Tint** | `#CCFBF1` / `rgb(204, 251, 241)` | 🟩 Soft Teal Tint | Table row selection highlight, badge backgrounds |
| **Dark Header Slate**| `#0F172A` / `rgb(15, 23, 42)` | ⬛ Deep Slate 900 | Top navigation bars, table header backgrounds, drag title bars |
| **Slate Navy** | `#1E293B` / `rgb(30, 41, 59)` | ⬛ Slate Navy 800 | Secondary action buttons, dialog cards |
| **Card Surface** | `#FFFFFF` / `rgb(255, 255, 255)` | ⬜ Pure White | Form panels, data cards, input backgrounds |
| **Light Slate Canvas**| `#F8FAFC` / `rgb(248, 250, 252)` | ⬜ Slate 50 | Window backgrounds, zebra table alternating rows |
| **Border Slate** | `#E2E8F0` / `rgb(226, 232, 240)` | ◽ Slate 200 | Card borders, dividers, group separators |
| **Input Border** | `#CBD5E1` / `rgb(203, 213, 225)` | ◽ Slate 300 | Inactive text field and combo box borders |
| **Success Emerald** | `#10B981` / `rgb(16, 185, 129)` | 🟩 Emerald Green | Available bed indicator, online status chip, update actions |
| **Alert Amber** | `#F59E0B` / `rgb(245, 158, 11)` | 🟨 Amber Accent | Ambulance fleet badge, capacity warnings |
| **Danger Rose** | `#EF4444` / `rgb(239, 68, 68)` | 🟥 Coral Red | Patient discharge actions, occupied bed badges, close buttons |

---

## 3. Typography Hierarchy

- **Primary Font Family:** `Segoe UI` (Fallbacks: `Tahoma`, `Arial`, `Sans-Serif`)
- **Scale Hierarchy:**
  - **Screen Headers / Form Titles:** `Font("Segoe UI", Font.BOLD, 22)`
  - **Section Headers / Card Titles:** `Font("Segoe UI", Font.BOLD, 18)`
  - **Subheaders & Metric Values:** `Font("Segoe UI", Font.BOLD, 15)`
  - **Form Labels & Table Headers:** `Font("Segoe UI", Font.BOLD, 13)`
  - **Body / Table Cell Contents:** `Font("Segoe UI", Font.PLAIN, 13)`
  - **Action Button Labels:** `Font("Segoe UI", Font.BOLD, 13)`
  - **Badges / Micro-Text:** `Font("Segoe UI", Font.BOLD, 11)`

---

## 4. UI Component Architecture (`Theme.java`)

### Modern Rounded Buttons (`Theme.ModernButton`)
- **Border Radius:** `10px` anti-aliased round rectangle.
- **Hover & Click Animations:** Dynamic darkening on mouse-enter and mouse-press.
- **Cursor:** Automatic `Cursor.HAND_CURSOR`.
- **Variants:** Primary Teal, Secondary Slate, Success Emerald, Danger Rose, Light Cancel.

### Modern Text & Password Inputs (`Theme.ModernTextField`, `Theme.ModernPasswordField`)
- **Border Radius:** `8px`.
- **Inner Padding:** `Insets(6, 12, 6, 12)` for comfortable breathing room.
- **Focus Glow:** Border transitions smoothly from `#CBD5E1` to Primary `#0D9488` with a 1.8px stroke upon focus.
- **Placeholder Text:** Subtle muted helper text when empty.

### Modern Card Surfaces (`Theme.ModernCard`)
- Anti-aliased rounded rectangle with `#E2E8F0` 1px border stroke and `#FFFFFF` fill.
- Used to group related form controls, financial balance widgets, and metrics.

### Draggable Modal Title Bar (`Theme.ModernTitleBar`)
- Integrated into all undecorated sub-windows (`NEW_PATIENT`, `Room`, `Patient_Discharge`, `Department`, etc.).
- **Drag-to-Move:** Users can click and drag the top title bar to move windows across any monitor.
- **Quick Close (`×`):** Hover-reactive red exit button that closes and disposes the sub-window cleanly.

### High-Density Data Tables (`Theme.ModernTable` & `createStyledScrollPane`)
- **Header:** `#0F172A` Deep Slate with bold white text and 38px height.
- **Row Height:** `34px` with centered text and 10px horizontal cell padding.
- **Zebra Striping:** Alternating `#FFFFFF` and `#F8FAFC` row backgrounds.
- **Selection Highlight:** Soft teal `#CCFBF1` with dark slate text.
- **Container:** Wrapped in a borderless modern `JScrollPane`.

---

## 5. Screen Layout Guidelines

1. **Authentication (`Login.java`):** Split card layout featuring a branded medical gradient hero on the left and a modern credential form on the right. Enter key automatically triggers login.
2. **Command Dashboard (`Reception.java`):** Full-screen command center with live clock, admin status badge, 3 structured module categories (Patient Care, Facility, Staff/Emergency), and hospital statistics banner.
3. **Modal Dialogs:** Dynamic screen centering via `setLocationRelativeTo(null)` to eliminate fixed off-screen coordinate issues on different monitor sizes.
