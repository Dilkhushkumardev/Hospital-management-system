# Design System & UI/UX Guidelines

## 1. Visual Philosophy
The Hospital Management System UI is designed with a **medical teal & gold aesthetic** that evokes cleanliness, confidence, trust, and rapid readability for hospital administrators.

---

## 2. Color Palette & Theme Tokens

| Token Name | RGB / Hex Value | Visual Swatch / Role | Usage |
| :--- | :--- | :--- | :--- |
| **Teal Primary** | `new Color(109, 164, 170)` / `#6DA4AA` | 🟩 Primary Teal | Main background of Reception & Login windows |
| **Teal Secondary** | `new Color(90, 156, 163)` / `#5A9CA3` | 🟩 Soft Teal | Form panels (`NEW_PATIENT`, `Room`, `Discharge`) |
| **Dark Slate Accent**| `new Color(3, 45, 48)` / `#032D30` | ⬛ Deep Forest Slate | ComboBox dropdowns & Choice controls |
| **Warm Sand Gold** | `new Color(246, 215, 118)` / `#F6D776` | 🟨 Sand Gold | Primary action buttons on Reception Hub |
| **Amber Orange** | `new Color(210, 150, 50)` / `#D29632` | 🟧 Warm Amber | Input fields in Login dialog |
| **Pitch Black** | `Color.BLACK` / `#000000` | ⬛ Black | Action buttons (`ADD`, `Back`, `Update`, `Check`) |
| **Pure White** | `Color.WHITE` / `#FFFFFF` | ⬜ White | Labels, Headings, and Table text |

---

## 3. Typography System

- **Primary Font Family:** `Tahoma` (Fallbacks: `Arial`, `Sans-Serif`, `Serif` for branded buttons)
- **Scale Hierarchy:**
  - **Screen Headers / Form Titles:** `Font("Tahoma", Font.BOLD, 20)`
  - **Section Sub-headers:** `Font("Tahoma", Font.BOLD, 16)`
  - **Form Labels & Field Titles:** `Font("Tahoma", Font.BOLD, 14)`
  - **Table Cell Contents:** `Font("Tahoma", Font.PLAIN, 12)` / `Font("Tahoma", Font.BOLD, 12)`
  - **Action Button Labels:** `Font("Tahoma", Font.BOLD, 14)` or `Font("serif", Font.BOLD, 15)`

---

## 4. UI Component Patterns

### Buttons
- **Reception Grid Buttons:**
  - Dimensions: `width: 200px`, `height: 30px`
  - Background: `Color(246, 215, 118)` (Sand Gold)
  - Foreground: `Color.BLACK`
- **Form Action Buttons (`ADD`, `Back`, `Check`, `Update`, `Discharge`):**
  - Dimensions: `width: 89px` to `130px`, `height: 23px` to `30px`
  - Background: `Color.BLACK`
  - Foreground: `Color.WHITE`

### Data Tables (`JTable`)
- Background: Matches parent panel (`Color(90, 156, 163)` or `Color(109, 164, 170)`)
- Foreground: `Color.WHITE` or `Color.BLACK`
- Font: `Font("Tahoma", Font.BOLD, 12)`

### Windows & Modals
- **Undecorated Modals:** Sub-screens (`NEW_PATIENT`, `Room`, `Patient_Discharge`, `Department`, `Ambulance`) use `setUndecorated(true)` for a sleek borderless card feel with localized close/back triggers.
- **Centering:** Frames use explicit `setLocation(300, 200)` / `setLocation(400, 250)` coordinates for optimal desktop screen visibility.
