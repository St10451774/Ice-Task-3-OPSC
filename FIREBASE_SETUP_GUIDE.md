# Firebase Integration Setup Guide - ICE Activity 4

## Complete Step-by-Step Implementation Guide

---

## **PART 1: Firebase Project Setup**

### Step 1.1: Create Firebase Project
1. Go to [Firebase Console](https://console.firebase.google.com/)
2. Click **"Create a project"**
3. Enter project name: `ST10451774-Centralink` (or similar)
4. Accept the terms and click **"Continue"**
5. Disable Google Analytics (optional) and click **"Create project"**
6. Wait for project creation to complete

### Step 1.2: Register Your Android App
1. In Firebase Console, click **"Add app"**
2. Select **Android** option
3. Fill in the app registration form:
   - **Android package name**: `com.st10451774.centralink`
   - **App nickname**: Centralink App
   - **Debug signing certificate SHA-1**: (Get from Android Studio)
4. Click **"Register app"**

### Step 1.3: Get SHA-1 Certificate
**In Android Studio:**
1. Open your project
2. Go to **Gradle** (Right panel)
3. Navigate to: `YOUR_PROJECT → Tasks → android → signingReport`
4. Double-click `signingReport`
5. Find the **SHA1** value in the Build output
6. Copy this value and paste it in Firebase Console

### Step 1.4: Download google-services.json
1. In Firebase Console, click **"Download google-services.json"**
2. Save the file

### Step 1.5: Add google-services.json to Project
1. In Android Studio, open the **Project** view
2. Navigate to `app/` folder
3. Paste the `google-services.json` file here
4. The file path should be: `app/google-services.json`

---

## **PART 2: Enable Firebase Authentication**

### Step 2.1: Enable Email/Password Authentication
1. In Firebase Console, go to **Authentication**
2. Click **"Get started"** (or go to Sign-in method tab)
3. Click **Email/Password** provider
4. Toggle **Enable** to turn it on
5. Click **"Save"**

### Step 2.2: Verify Authentication Setup
- Status should show **Enabled** ✓
- Users tab is now available to monitor registrations

---

## **PART 3: Configure Firebase Realtime Database**

### Step 3.1: Create Realtime Database
1. In Firebase Console, go to **Realtime Database**
2. Click **"Create Database"**
3. Select region closest to you
4. Start in **Test Mode** (for development)
5. Click **"Enable"**

### Step 3.2: Set Database Rules
Once database is created:
1. Click **"Rules"** tab
2. Replace existing rules with this:

```json
{
  "rules": {
    "users": {
      "$uid": {
        ".read": "$uid === auth.uid",
        ".write": "$uid === auth.uid"
      }
    }
  }
}
```

3. Click **"Publish"**

This ensures users can only read/write their own data.

---

## **PART 4: Update Navigation Graph**

### Step 4.1: Create nav_graph.xml
Create file: `app/src/main/res/navigation/nav_graph.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/nav_graph"
    app:startDestination="@id/loginFragment">

    <fragment
        android:id="@+id/loginFragment"
        android:name="com.st10451774.centralink.LoginFragment"
        android:label="Login">
        <action
            android:id="@+id/action_loginFragment_to_registerFragment"
            app:destination="@id/registerFragment" />
        <action
            android:id="@+id/action_loginFragment_to_homeFragment"
            app:destination="@id/homeFragment" />
    </fragment>

    <fragment
        android:id="@+id/registerFragment"
        android:name="com.st10451774.centralink.RegisterFragment"
        android:label="Register">
        <action
            android:id="@+id/action_registerFragment_to_loginFragment"
            app:destination="@id/loginFragment" />
    </fragment>

    <fragment
        android:id="@+id/homeFragment"
        android:name="com.st10451774.centralink.HomeFragment"
        android:label="Home">
        <action
            android:id="@+id/action_homeFragment_to_mapFragment"
            app:destination="@id/mapFragment" />
        <action
            android:id="@+id/action_homeFragment_to_announcementsFragment"
            app:destination="@id/announcementsFragment" />
        <action
            android:id="@+id/action_homeFragment_to_loginFragment"
            app:destination="@id/loginFragment" />
    </fragment>

    <fragment
        android:id="@+id/mapFragment"
        android:name="com.st10451774.centralink.MapFragment"
        android:label="Map">
        <action
            android:id="@+id/action_mapFragment_to_announcementsFragment"
            app:destination="@id/announcementsFragment" />
    </fragment>

    <fragment
        android:id="@+id/announcementsFragment"
        android:name="com.st10451774.centralink.AnnouncementsFragment"
        android:label="Announcements">
        <action
            android:id="@+id/action_announcementsFragment_to_homeFragment"
            app:destination="@id/homeFragment" />
    </fragment>

</navigation>
```

### Step 4.2: Update activity_main.xml
Replace: `app/src/main/res/layout/activity_main.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <androidx.fragment.app.FragmentContainerView
        android:id="@+id/nav_host_fragment_activity_main"
        android:name="androidx.navigation.fragment.NavHostFragment"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1"
        app:defaultNavHost="true"
        app:navGraph="@navigation/nav_graph" />

    <com.google.android.material.bottomnavigation.BottomNavigationView
        android:id="@+id/bottom_nav_menu"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        app:menu="@menu/bottom_nav_menu" />

</LinearLayout>
```

---

## **PART 5: Update AndroidManifest.xml**

Update: `app/src/main/AndroidManifest.xml`

Make sure it includes:
```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
```

Update package name to:
```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.st10451774.centralink">
```

---

## **PART 6: Create Missing Fragments**

### Step 6.1: Create AnnouncementsFragment.kt

Create file: `app/src/main/java/com/st10451774/centralink/AnnouncementsFragment.kt`

```kotlin
package com.st10451774.centralink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class AnnouncementsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_announcements, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val buttonBack = view.findViewById<android.widget.Button>(R.id.buttonBack)
        buttonBack?.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}
```

### Step 6.2: Create fragment_announcements.xml

Create file: `app/src/main/res/layout/fragment_announcements.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="20dp"
    android:gravity="center">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Announcements"
        android:textSize="28sp"
        android:textStyle="bold"
        android:layout_marginBottom="30dp" />

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Welcome to the Announcements page!"
        android:textSize="16sp"
        android:layout_marginBottom="20dp" />

    <Button
        android:id="@+id/buttonBack"
        android:layout_width="match_parent"
        android:layout_height="50dp"
        android:text="Go Back" />

</LinearLayout>
```

---

## **PART 7: Sync and Build Project**

### Step 7.1: Sync Gradle Files
1. In Android Studio, go to **File → Sync Now**
2. Wait for Gradle sync to complete
3. Resolve any dependency issues if prompted

### Step 7.2: Build the Project
1. Click **Build → Make Project**
2. Wait for build to complete
3. Resolve any errors

### Step 7.3: Run the Application
1. Connect an Android device or start an emulator
2. Click **Run → Run 'app'**
3. Wait for app to install and launch

---

## **PART 8: Testing the Application**

### Test 1: User Registration ✓
1. App opens with Login screen
2. Click **"Register"** link
3. Fill in all fields:
   - First Name: `John`
   - Last Name: `Doe`
   - Email: `john@example.com`
   - Phone: `1234567890`
   - Password: `password123`
   - Confirm Password: `password123`
4. Click **Register**
5. Should see success message
6. Auto-navigates to Login screen

### Test 2: Verify Data in Firebase ✓
1. Go to Firebase Console
2. Click **Realtime Database**
3. Expand **users** node
4. Should see new user with UID as key
5. Under UID, verify all user details are saved:
   ```
   - uid: (auto-generated)
   - email: john@example.com
   - firstName: John
   - lastName: Doe
   - phoneNumber: 1234567890
   ```

### Test 3: User Login ✓
1. Enter registered email: `john@example.com`
2. Enter password: `password123`
3. Click **Login**
4. Should see welcome message: `"Welcome, John Doe!"`
5. Should navigate to Home fragment

### Test 4: Open Map ✓
1. From Home screen, click **"Open Map"**
2. Mapbox map should display
3. Click **"Navigate to Next Fragment"** on map
4. Should go to Announcements page

### Test 5: Navigate Between Fragments ✓
1. From Home, click **"Navigate to Announcements"**
2. Should go to Announcements page
3. Click **"Go Back"**
4. Should return to Home

### Test 6: Logout ✓
1. From Home, click **"Logout"**
2. Should navigate back to Login screen
3. Previous data should not be visible

### Test 7: Invalid Login ✓
1. Try logging in with wrong email/password
2. Should show error message
3. Should not navigate to Home

---

## **PART 9: Creating Demo Video (2-5 minutes)**

### Video Content Checklist:
- [ ] Show app opening with Login screen
- [ ] Demonstrate user registration with all fields
- [ ] Go to Firebase Console and show saved user data in database
- [ ] Show successful login
- [ ] Display welcome message with user's name
- [ ] Click "Open Map" and show Mapbox map
- [ ] Click navigation button on map to go to Announcements
- [ ] Show navigation from Home to Announcements
- [ ] Click logout and return to Login
- [ ] Show app working smoothly with no errors

### Recording Tips:
1. Use screen recorder (built-in or OBS)
2. Speak clearly, explain each step
3. Show Firebase Console alongside app
4. Keep video under 5 minutes
5. Save as MP4 format

---

## **PART 10: Final Checklist Before Submission**

### Code Requirements ✓
- [ ] RegisterFragment.kt - Validates input and saves to Firebase
- [ ] LoginFragment.kt - Authenticates users
- [ ] HomeFragment.kt - Shows welcome message and user data
- [ ] MapFragment.kt - Opens Mapbox map
- [ ] User data structure defined
- [ ] Navigation graph configured
- [ ] Package name changed to `com.st10451774.centralink`

### Firebase Setup ✓
- [ ] Firebase project created
- [ ] google-services.json added to `app/` folder
- [ ] Email/Password Authentication enabled
- [ ] Realtime Database created
- [ ] Database rules configured for user privacy

### Testing ✓
- [ ] Registration works and saves data to Firebase
- [ ] Login works with valid credentials
- [ ] Login fails with invalid credentials
- [ ] User data visible in Firebase Database
- [ ] Map opens successfully
- [ ] Navigation between fragments works
- [ ] Logout works correctly
- [ ] No errors in Logcat

### Submission Files ✓
- [ ] Complete Android Studio project
- [ ] Demo video (2-5 minutes) showing:
  1. User Registration
  2. Firebase Data Saving
  3. User Login
  4. Map Opening
  5. Fragment Navigation
  6. Overall Functionality

---

## **Troubleshooting Guide**

### Issue: "google-services.json not found"
**Solution:** Make sure file is in `app/` folder (not root)

### Issue: Firebase initialization fails
**Solution:** Check internet connection and verify Firebase project is active

### Issue: Authentication not working
**Solution:** 
1. Verify Email/Password is enabled in Firebase Console
2. Check package name matches in Firebase project settings
3. Re-download google-services.json

### Issue: Map not showing
**Solution:**
1. Verify Mapbox token in gradle.properties
2. Check location permissions are granted
3. Ensure internet connectivity

### Issue: Data not saving to database
**Solution:**
1. Check database rules allow authenticated access
2. Verify user is authenticated before writing
3. Check database is in Test Mode (for development)

---

## **Additional Resources**

- Firebase Documentation: https://firebase.google.com/docs
- Android Navigation: https://developer.android.com/guide/navigation
- Mapbox Integration: https://docs.mapbox.com/android/maps/guides/install/

---

**Last Updated:** September 2026
**Project:** ST10451774 - ICE Activity 4
**Status:** Ready for Submission
