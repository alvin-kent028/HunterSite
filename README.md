# 🕵️ HUNTERSITE - Mobile Job Portal

**HUNTERSITE** is a professional mobile application built in Android Studio designed to connect Job Seekers and Employers. This project focuses on a clean user experience, robust input handling, and seamless navigation.

---

## 👥 Team Members
* **Alvin Kent Bigbig**
* **Franze Villarasa**
* **Shaira Nicole Mirasol**

---

## 🚀 Milestone 1: User Input & Navigation

### 🔹 Sprint 1: User Input Handling
* **Clean UI:** Professional, branded Login and Registration interface.
* **Role Selection:** Integrated logic for Job Seeker and Employer profiles.
* **Form Validation:** Real-time validation for email formats and password matching.
* **Tech Stack:** XML Layouts, Material Design Components, Kotlin.

### 🔹 Sprint 2: Dashboard & Navigation Implementation
* **Interactive Dashboard:** High-fidelity landing page with job statistics, search bar, and popular categories.
* **Job List Feed:** A dedicated, scrollable list of available career opportunities.
* **Explicit Intents:** Smooth and reliable transitions between the Dashboard and Job List screens.
* **Activity Management:** Efficient use of the Activity Lifecycle and `finish()` methods to prevent app crashes and manage memory.
* **User Feedback:** Integrated `Toast` notifications for the "Apply Now" functionality.

---

## 🧪 Testing Logs

| Test Category | Description | Status |
| :--- | :--- | :--- |
| **Email Validation** | Rejects inputs without "@" or proper domain extensions. | ✅ PASS |
| **Password Match** | Prevents registration if "Confirm Password" does not match. | ✅ PASS |
| **Dashboard Navigation** | Successfully moves from Dashboard to Job List via "View All". | ✅ PASS |
| **Back Navigation** | Header back-arrow successfully returns user to Dashboard. | ✅ PASS |
| **Action Feedback** | "Apply Now" button triggers a success confirmation message. | ✅ PASS |

---

## 🛠️ Installation & Setup
1. **Clone** this repository to your local machine.
2. **Open** the project in Android Studio (Hedgehog or newer).
3. **Build** the project and run it on an Emulator (API 24+) or a physical Android device.

---

## 📝 Technical Reflection
During this milestone, we successfully bridged the gap between static UI and functional navigation. A major technical highlight was implementing a modular "Apply" logic using helper functions in Kotlin, which ensures the codebase remains clean and scalable for future milestones.
