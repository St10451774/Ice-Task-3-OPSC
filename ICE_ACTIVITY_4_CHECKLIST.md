# ICE Activity 4 - Implementation Checklist

## Pre-Implementation Checklist
- [ ] Read the FIREBASE_SETUP_GUIDE.md thoroughly
- [ ] Have Firebase Console open
- [ ] Android Studio installed and project loaded
- [ ] Device/Emulator ready for testing

---

## Step 1: Firebase Project Setup
- [ ] 1.1 - Created Firebase project
- [ ] 1.2 - Registered Android app in Firebase
- [ ] 1.3 - Generated and noted SHA-1 certificate
- [ ] 1.4 - Downloaded google-services.json
- [ ] 1.5 - Placed google-services.json in app/ folder

## Step 2: Firebase Authentication
- [ ] 2.1 - Enabled Email/Password authentication
- [ ] 2.2 - Verified authentication is active in Firebase Console

## Step 3: Firebase Realtime Database
- [ ] 3.1 - Created Realtime Database
- [ ] 3.2 - Updated database rules with user privacy settings
- [ ] 3.3 - Database is in Test Mode (for development)

## Step 4: Update Navigation Graph
- [ ] 4.1 - Created nav_graph.xml with all fragments
- [ ] 4.2 - Updated activity_main.xml with NavHostFragment
- [ ] 4.3 - All navigation routes defined

## Step 5: Update AndroidManifest.xml
- [ ] 5.1 - Added INTERNET permission
- [ ] 5.2 - Added LOCATION permissions
- [ ] 5.3 - Updated package name to com.st10451774.centralink

## Step 6: Create Missing Fragments
- [ ] 6.1 - Created AnnouncementsFragment.kt
- [ ] 6.2 - Created fragment_announcements.xml layout
- [ ] 6.3 - All fragments have proper imports

## Step 7: Gradle Sync and Build
- [ ] 7.1 - Synced Gradle files (File → Sync Now)
- [ ] 7.2 - Built project (Build → Make Project)
- [ ] 7.3 - No build errors
- [ ] 7.4 - No dependency warnings

## Step 8: Testing Phase

### Test 1: User Registration
- [ ] App launches without crashes
- [ ] Login screen displays correctly
- [ ] Can navigate to Register fragment
- [ ] All input fields are visible
- [ ] Can enter all required information
- [ ] Validation messages appear for invalid input
- [ ] Registration button works
- [ ] Success message displays after registration
- [ ] Auto-navigates to Login screen

### Test 2: Firebase Data Verification
- [ ] Go to Firebase Console
- [ ] Open Realtime Database
- [ ] User data is saved under /users/{uid}/
- [ ] All fields are present: email, firstName, lastName, phoneNumber
- [ ] Data is correct and matches registration input

### Test 3: User Login
- [ ] Can enter email and password
- [ ] Login validation works (empty fields)
- [ ] Correct credentials allow login
- [ ] Incorrect credentials show error message
- [ ] Success message displays after successful login
- [ ] Auto-navigates to Home fragment

### Test 4: Home Screen
- [ ] Welcome message displays with user's name
- [ ] User data loaded from Firebase correctly
- [ ] "Open Map" button is clickable
- [ ] "Navigate to Announcements" button is clickable
- [ ] "Logout" button is clickable

### Test 5: Map Fragment
- [ ] Map opens without errors
- [ ] Mapbox map displays (may need internet)
- [ ] Map is interactive
- [ ] "Navigate to Next Fragment" button works
- [ ] Navigates to Announcements fragment

### Test 6: Fragment Navigation
- [ ] Home → Map works
- [ ] Home → Announcements works
- [ ] Map → Announcements works
- [ ] Announcements → Home works (back button)
- [ ] No crashes during navigation

### Test 7: Logout Functionality
- [ ] Logout button works
- [ ] Returns to Login screen
- [ ] Cannot access Home screen after logout
- [ ] Can login again with same credentials

### Test 8: Edge Cases
- [ ] Try registering with existing email (should fail)
- [ ] Try login with non-existent email
- [ ] Try password shorter than 6 characters
- [ ] Try passwords that don't match in confirmation
- [ ] Close and reopen app (should maintain login state)
- [ ] Test on different devices/screen sizes

---

## Step 9: Demo Video Production

### Video Recording Setup
- [ ] Device is fully charged
- [ ] Internet connection is stable
- [ ] Screen recording software ready
- [ ] Microphone is working
- [ ] Quiet environment for audio

### Video Content (2-5 minutes)
- [ ] 0:00-0:30 - App opening, show Login screen
- [ ] 0:30-1:30 - User Registration
  - [ ] Click Register link
  - [ ] Fill all fields
  - [ ] Show validation (try invalid input)
  - [ ] Successfully register
  - [ ] Show success message
- [ ] 1:30-2:15 - Firebase Verification
  - [ ] Open Firebase Console (browser split screen)
  - [ ] Show database with saved user data
  - [ ] Point out all fields: uid, email, firstName, lastName, phone
- [ ] 2:15-2:45 - User Login
  - [ ] Enter registered credentials
  - [ ] Show welcome message with user's name
  - [ ] Verify data loaded correctly
- [ ] 2:45-3:30 - Map Feature
  - [ ] Click "Open Map" button
  - [ ] Show Mapbox map loading
  - [ ] Show map interaction
  - [ ] Navigate to next fragment from map
- [ ] 3:30-4:00 - Navigation Testing
  - [ ] From Home, go to Announcements
  - [ ] Show back button working
  - [ ] Navigate between different fragments
- [ ] 4:00-4:30 - Logout and Conclusion
  - [ ] Click Logout
  - [ ] Return to Login screen
  - [ ] Brief summary of features demonstrated

### Video Quality Requirements
- [ ] Resolution: 1080p (1920x1080) or higher
- [ ] Format: MP4
- [ ] Duration: 2-5 minutes
- [ ] Audio: Clear narration explaining each step
- [ ] FPS: 30fps or higher
- [ ] File size: Under 500MB

---

## Step 10: Final Submission Preparation

### Code Files Ready
- [ ] All .kt files have correct package names
- [ ] All .xml files are in correct directories
- [ ] No commented-out code (unless necessary)
- [ ] Proper error handling implemented
- [ ] No sensitive data in code (tokens moved to gradle.properties)

### Project Structure
```
Ice-Task-3-OPSC/
├── app/
│   ├── src/main/java/com/st10451774/centralink/
│   │   ├── MainActivity.kt ✓
│   │   ├── User.kt ✓
│   │   ├── RegisterFragment.kt ✓
│   │   ├── LoginFragment.kt ✓
│   │   ├── HomeFragment.kt ✓
│   │   ├── MapFragment.kt ✓
│   │   ├── AnnouncementsFragment.kt ✓
│   │   └── (Other existing fragments)
│   ├── src/main/res/
│   │   ├── layout/
│   │   │   ├── activity_main.xml ✓
│   │   │   ├── fragment_register.xml ✓
│   │   │   ├── fragment_login.xml ✓
│   │   │   ├── fragment_home.xml ✓
│   │   │   ├── fragment_map.xml ✓
│   │   │   └── fragment_announcements.xml ✓
│   │   ├── navigation/
│   │   │   └── nav_graph.xml ✓
│   │   ├── drawable/
│   │   │   └── edit_text_background.xml ✓
│   │   └── menu/
│   │       └── bottom_nav_menu.xml ✓
│   ├── build.gradle.kts ✓
│   └── google-services.json ✓ (Required)
├── build.gradle.kts ✓
├── gradle/libs.versions.toml ✓
├── settings.gradle.kts
└── FIREBASE_SETUP_GUIDE.md ✓
```

### Documentation
- [ ] README.md updated with Firebase info
- [ ] FIREBASE_SETUP_GUIDE.md complete
- [ ] ICE_ACTIVITY_4_CHECKLIST.md (this file)
- [ ] All comments in code are clear

### Firebase Configuration
- [ ] google-services.json in app/ folder
- [ ] Project ID noted: ________________
- [ ] Email/Password authentication: ENABLED ✓
- [ ] Realtime Database: CREATED ✓
- [ ] Database Rules: PUBLISHED ✓

### Device/Emulator Testing
- [ ] Tested on Android 7.0+ device
- [ ] Tested on emulator (if possible)
- [ ] All permissions granted
- [ ] No crashes or ANR errors

---

## Submission Checklist

### Files to Submit on ARC
- [ ] Complete Android Studio project (as .zip)
- [ ] Demo video (MP4 format, 2-5 minutes)
- [ ] README with project description
- [ ] FIREBASE_SETUP_GUIDE.md

### Before Final Submission
- [ ] All tests passed
- [ ] Video uploaded and tested
- [ ] Project compressed properly
- [ ] Submitted before deadline
- [ ] Student ID (ST10451774) is in package name
- [ ] All requirements from assignment met

---

## Troubleshooting Quick Reference

**Q: App crashes on startup**
A: Check google-services.json location and Firebase dependencies in build.gradle

**Q: Login not working**
A: Verify Email/Password is enabled in Firebase Console Authentication

**Q: User data not saving**
A: Check Realtime Database rules and ensure user is authenticated

**Q: Map not displaying**
A: Verify Mapbox token and internet connection

**Q: Navigation not working**
A: Check nav_graph.xml syntax and fragment IDs match exactly

**Q: Build fails**
A: Run "Build → Clean Project" then "Build → Make Project"

---

## Support Resources
- Firebase Docs: https://firebase.google.com/docs
- Android Navigation: https://developer.android.com/guide/navigation
- Kotlin Documentation: https://kotlinlang.org/docs
- Mapbox Android: https://docs.mapbox.com/android/maps

---

**Project Status:** Ready for Development
**Last Updated:** September 14, 2026
**Student ID:** ST10451774
