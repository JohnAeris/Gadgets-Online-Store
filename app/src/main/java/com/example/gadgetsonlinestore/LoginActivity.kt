package com.example.gadgetsonlinestore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.UiThread
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_get_started_screen.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val personCollectionRef = Firebase.firestore.collection("Users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        tvRegister.setOnClickListener{
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        tvForgotPassword.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }

        btnLogIn.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val email = findViewById<EditText>(R.id.etUsername).text.toString()
        val password = findViewById<EditText>(R.id.etPassword).text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    auth.signInWithEmailAndPassword(email, password).await()
                    withContext(Dispatchers.IO) {
                        runOnUiThread {
                            Toast.makeText(this@LoginActivity, "Successfully Logged In", Toast.LENGTH_LONG).show()
                        }
                    }
                    startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                } catch (e: Exception) {
                    withContext(Dispatchers.IO) {
                        runOnUiThread {
                            Toast.makeText(this@LoginActivity, e.message, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }

    }



}