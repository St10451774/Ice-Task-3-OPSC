# Ice Task 3 OPSC

**Student ID**: ST10451774

## Project Overview

This is an Android application project built with Kotlin and Android Studio.

### Technologies Used
- **Language**: Kotlin
- **Build System**: Gradle
- **Target SDK**: Android 37 (14)
- **Min SDK**: Android 24
- **Key Libraries**:
  - AndroidX
  - Retrofit 2 (HTTP Client)
  - Mapbox Maps SDK v11
  - Navigation Components
  - Material Design 3

### Project Structure

```
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/centralink/
│   │   │   │   ├── MainActivity.kt
│   │   │   │   ├── Fragments
│   │   │   │   └── API Classes
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   ├── drawable/
│   │   │   │   └── values/
│   │   │   └── AndroidManifest.xml
│   │   ├── test/
│   │   └── androidTest/
│   └── build.gradle.kts
├── gradle/
├── build.gradle.kts
└── settings.gradle.kts
```

## Building the Project

### Prerequisites
- Android Studio (latest)
- Java 11 or higher
- Gradle 8.x

### Steps
1. Clone the repository
2. Open in Android Studio
3. Sync Gradle files
4. Build and run on emulator or device

## Dependencies

All dependencies are managed through `gradle/libs.versions.toml`:

- Core Android dependencies (AndroidX)
- Retrofit for API calls
- Gson for JSON serialization
- Navigation components
- Mapbox for mapping features

## Configuration

### Mapbox API Token
The project uses Mapbox for map functionality. The API token is stored in `gradle.properties` as `MAP_ACCESS_TOKEN`.

## Permissions

The app requires:
- Internet access
- Network state access
- Coarse location access
- Fine location access

## License

This project is for educational purposes.
