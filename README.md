# 🕵️ HUNTERSITE - Mobile Job Portal

**HUNTERSITE** is a professional mobile application built in Android Studio designed to connect Job Seekers and Employers. This project focuses on a clean user experience, robust input handling, and seamless navigation.

---

## 👥 Team Members
* **Alvin Kent Bigbig**
* **Franze Villarasa**
* **Shaira Nicole Mirasol**

---

## 🚀 Project Development

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

### 🔹 Sprint 3: Local Database Management Implementation
* **Room Persistence Library:** Implementation of local storage using Room for offline data persistence.
* **Data Entities:** Created `User` and `JobPost` entities to manage application state locally.
* **DAO Pattern:** Structured Data Access Objects (`UserDao`, `JobDao`) for clean database interactions.

### 🔹 Sprint 4: Deciding and Integrating Remote Data Management (Firebase)
* **Firebase Authentication:** Integrated secure cloud-based authentication for user login and registration.
* **Firebase Firestore:** Real-time database integration to synchronize user profiles and role-specific data.
* **Cloud Synchronization:** Seamless transition from local Room data to remote Firebase storage.

### 🔹 Sprint 5: External Testing, User Documentation Creation, and Final App Refinement
* **Advanced Error Handling:** Refined authentication logic to provide specific user feedback ("No Account Found" vs "The Password is incorrect").
* **Switch Account Feature:** Implemented a dedicated "Switch Account" system with automatic role-based dashboard redirection.
* **Final Refinement:** Cleaned up activity transitions and optimized Firestore queries for better performance.

---

## 🧪 Testing Logs

| Test Category | Description | Status |
| :--- | :--- | :--- |
| **Email Validation** | Rejects inputs without "@" or proper domain extensions. | ✅ PASS |
| **Password Match** | Prevents registration if "Confirm Password" does not match. | ✅ PASS |
| **Authentication** | Correctly identifies "No Account Found" vs "The Password is incorrect". | ✅ PASS |
| **Switch Account** | Signs out current user and logs into a new one with correct role redirection. | ✅ PASS |
| **Role Redirection** | Redirects Employers to Employer Dashboard and Job Seekers to Job Seeker Dashboard. | ✅ PASS |
| **Dashboard Navigation** | Successfully moves from Dashboard to Job List via "View All". | ✅ PASS |
| **Back Navigation** | Header back-arrow successfully returns user to Dashboard. | ✅ PASS |

---

## 🛠️ Installation & Setup
1. **Clone** this repository to your local machine.
2. **Open** the project in Android Studio (Hedgehog or newer).
3. **Firebase Setup:** Connect the project to Firebase and add your `google-services.json`.
4. **Build** the project and run it on an Emulator (API 24+) or a physical Android device.

---

## 📝 Technical Reflection
Throughout these sprints, we successfully transitioned from a static UI to a fully functional, cloud-integrated mobile application. The combination of Room for local persistence and Firebase for remote synchronization ensures a robust and scalable architecture. A key achievement was refining the user feedback system to be intuitive, especially during complex processes like account switching and multi-role authentication.
