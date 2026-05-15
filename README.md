# Raitha Varta (ರೈತ ವಾರ್ತೆ) 🌱

**Raitha Varta** is a hyper-local Android "Flash-Card Advisor" app designed to bridge the gap between agricultural research and field practice. It delivers 1-minute, high-contrast digital advisories to farmers, ensuring critical information is accessible, readable, and actionable.

---

## 🌾 The Problem It Solves

Agricultural extension services often provide dense, text-heavy manuals that are difficult to consume in the field. **Raitha Varta** addresses this by providing:

- **Bite-Sized Advisories:** Complex research distilled into 1-minute digital flashcards.
- **Digital Inclusion:** Instant English/Kannada toggle for local language accessibility.
- **Field-Optimized UI:** High-contrast design and Forest Green branding for better visibility under direct sunlight.
- **Localized Success Stories:** Peer-to-peer motivation through regional success data (e.g., yield increase metrics).
- **Expert Accessibility:** Direct flow to connect with agricultural advisors.

---

## 🛠️ Technical Overview

- **Platform:** Android
- **Language:** Kotlin
- **UI Architecture:** Jetpack Compose + Material 3 (Declarative UI)
- **State Management:** MVVM with State Hoisting and StateFlow
- **Persistence:** - **Room Database:** For offline-first advisory caching.
- **SharedPreferences:** For persistent language preferences and user session data.
- **Backend:** Firestore (Real-time advisory updates)
- **Image Loading:** Coil (Asynchronous loading of crop images)

---

## 📦 Core Functional Modules

### 1. Discovery & Advisory Feed
- **Crop-Specific Filtering:** Categories for Paddy, Coconut, Arecanut, and Tomato.
- **Horizontal Pager:** Swipe-through flashcards for "Daily Tips" and "Best Practices."
- **Search:** Instant keyword search for specific pest or fertilizer advice.

### 2. Localization Module
- **Dual Language Support:** Full Kannada and English UI strings.
- **Capsule Toggle:** A custom-animated UI component in the header for instant runtime switching.
- **Persistent Choice:** The app remembers the user's language choice from the login session via SharedPreferences.

### 3. Success Story Module
- **Impact Metrics:** Highlights yield and profit growth percentages (e.g., +35% Yield Increase).
- **Regional Stories:** Stories filtered by district (e.g., Mandya, Tumkur).

### 4. Expert Advisor Integration
- **Direct Contact:** Intent-based communication (WhatsApp/Call) to agricultural experts.
- **Feedback Loop:** Ability for farmers to submit ratings on the quality of advice.

---

## 📊 Data Model Snapshot

- `AdvisoryTip`: category, crop type, text, image, priority.
- `SuccessStory`: farmer name, location, yield increase, crop, testimonial.
- `CropCategory`: id, localized name, icon resource.
- `UserPreference`: language code, recently viewed crops.

---

## 📂 Repository Structure

- `app/src/main/java/com/developer/raitha_varta/presentation/`: Compose Screens and Components.
- `app/src/main/java/com/developer/raitha_varta/viewmodel/`: State logic and Locale management.
- `app/src/main/java/com/developer/raitha_varta/data/`: Room DAOs, Repositories, and Entities.
- `app/src/main/res/drawable/`: Custom vector assets like `ic_sprout.xml`.

---

## 🚀 Getting Started

### 1. Clone the Repository
```bash
git clone [https://github.com/vinayakachar/raitha-varta.git](https://github.com/vinayakachar/raitha-varta.git)
```

### 2. Prerequisites
- Android Studio Ladybug (or latest stable)
- JDK 17+
- Android SDK 34+

### 3. Build & Run
```bash
./gradlew assembleDebug
```

To run tests:

```bash
./gradlew test
```

To run lint checks:

```bash
./gradlew lint
```
---

## 🔮 Future Roadmap

To evolve **Raitha-Varta** from a Minimum Viable Product (MVP) to a full-scale agricultural ecosystem, the following phases are planned:

- **Phase 2: AI-Powered Pest Diagnosis** – Integration of **TensorFlow Lite** or **MediaPipe** to allow farmers to identify crop diseases by simply taking a photo.
- **Phase 3: Voice-Command Navigation** – Implementing **Speech-to-Text** in Kannada and English to assist farmers who may have difficulty typing while working in the field.
- **Phase 4: Hyper-Local Weather Integration** – Adding a module that provides real-time weather alerts and "Next Best Action" advice based on upcoming rainfall.
- **Phase 5: Marketplace Connectivity** – A direct link to verified local seed and fertilizer vendors based on the recommendations provided in the flashcards.
