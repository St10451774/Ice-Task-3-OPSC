package com.st10451774.centralink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var textViewWelcome: TextView
    private lateinit var buttonOpenMap: Button
    private lateinit var buttonNavigateNext: Button
    private lateinit var buttonLogout: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize Firebase
        auth = Firebase.auth
        database = FirebaseDatabase.getInstance()

        // Initialize views
        textViewWelcome = view.findViewById(R.id.textViewWelcome)
        buttonOpenMap = view.findViewById(R.id.buttonOpenMap)
        buttonNavigateNext = view.findViewById(R.id.buttonNavigateNext)
        buttonLogout = view.findViewById(R.id.buttonLogout)

        // Load user data
        loadUserData()

        // Set button listeners
        buttonOpenMap.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_mapFragment)
        }

        buttonNavigateNext.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_announcementsFragment)
        }

        buttonLogout.setOnClickListener {
            auth.signOut()
            findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
            Toast.makeText(requireContext(), "Logged out successfully", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadUserData() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            database.reference.child("users").child(currentUser.uid)
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()) {
                            val user = snapshot.getValue(User::class.java)
                            if (user != null) {
                                textViewWelcome.text = "Welcome, ${user.firstName} ${user.lastName}!"
                            }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(
                            requireContext(),
                            "Error loading user data",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
        }
    }
}
