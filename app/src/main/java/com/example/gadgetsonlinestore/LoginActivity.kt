package com.example.gadgetsonlinestore

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.example.gadgetsonlinestore.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val personCollectionRef = Firebase.firestore.collection("Users")
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.tvRegister.setOnClickListener{
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        binding.tvForgotPassword.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }

        binding.btnLogIn.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val email = findViewById<EditText>(R.id.etUsername).text.toString()
        val password = findViewById<EditText>(R.id.etPassword).text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener (this@LoginActivity) { task ->
                            if (task.isSuccessful) {
                                Log.d(ContentValues.TAG, "login: Success")
                                Toast.makeText(this@LoginActivity, "Logged In", Toast.LENGTH_SHORT).show()

                                binding.etUsername.text?.clear()
                                binding.etPassword.text?.clear()

                                val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                                startActivity(intent)

                            }
                        }
                        .addOnFailureListener {
                            Log.w(ContentValues.TAG, "login: Unsuccessful")
                        }
                        .await()
                } catch (e: Exception) {
                    withContext(Dispatchers.IO) {
                        runOnUiThread {
                            Toast.makeText(this@LoginActivity, "The username or password you entered is incorrect", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        } else {
            Toast.makeText(this@LoginActivity, "Fill all fields", Toast.LENGTH_SHORT).show()
        }

    }



}