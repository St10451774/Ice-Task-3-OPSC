package com.st10451774.centralink

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class RegisterFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextConfirmPassword: EditText
    private lateinit var editTextFirstName: EditText
    private lateinit var editTextLastName: EditText
    private lateinit var editTextPhone: EditText
    private lateinit var buttonRegister: Button
    private lateinit var textViewLogin: TextView
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize Firebase
        auth = Firebase.auth
        database = FirebaseDatabase.getInstance()

        // Initialize views
        editTextEmail = view.findViewById(R.id.editTextEmail)
        editTextPassword = view.findViewById(R.id.editTextPassword)
        editTextConfirmPassword = view.findViewById(R.id.editTextConfirmPassword)
        editTextFirstName = view.findViewById(R.id.editTextFirstName)
        editTextLastName = view.findViewById(R.id.editTextLastName)
        editTextPhone = view.findViewById(R.id.editTextPhone)
        buttonRegister = view.findViewById(R.id.buttonRegister)
        textViewLogin = view.findViewById(R.id.textViewLogin)
        progressBar = view.findViewById(R.id.progressBar)

        buttonRegister.setOnClickListener {
            registerUser()
        }

        textViewLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }

    private fun registerUser() {
        val email = editTextEmail.text.toString().trim()
        val password = editTextPassword.text.toString().trim()
        val confirmPassword = editTextConfirmPassword.text.toString().trim()
        val firstName = editTextFirstName.text.toString().trim()
        val lastName = editTextLastName.text.toString().trim()
        val phone = editTextPhone.text.toString().trim()

        // Validation
        if (TextUtils.isEmpty(email)) {
            editTextEmail.error = "Email is required"
            return
        }
        if (TextUtils.isEmpty(password)) {
            editTextPassword.error = "Password is required"
            return
        }
        if (password != confirmPassword) {
            editTextConfirmPassword.error = "Passwords do not match"
            return
        }
        if (password.length < 6) {
            editTextPassword.error = "Password must be at least 6 characters"
            return
        }
        if (TextUtils.isEmpty(firstName)) {
            editTextFirstName.error = "First name is required"
            return
        }
        if (TextUtils.isEmpty(lastName)) {
            editTextLastName.error = "Last name is required"
            return
        }

        progressBar.visibility = View.VISIBLE

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    if (user != null) {
                        // Save user data to Firebase Database
                        val userObj = User(
                            uid = user.uid,
                            email = email,
                            firstName = firstName,
                            lastName = lastName,
                            phoneNumber = phone
                        )

                        database.reference.child("users").child(user.uid)
                            .setValue(userObj)
                            .addOnSuccessListener {
                                progressBar.visibility = View.GONE
                                Toast.makeText(
                                    requireContext(),
                                    "User registered and data saved successfully!",
                                    Toast.LENGTH_SHORT
                                ).show()
                                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                            }
                            .addOnFailureListener { e ->
                                progressBar.visibility = View.GONE
                                Toast.makeText(
                                    requireContext(),
                                    "Failed to save user data: ${e.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                    }
                } else {
                    progressBar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "Registration failed: ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}
