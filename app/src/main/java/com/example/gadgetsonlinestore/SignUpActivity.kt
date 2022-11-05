package com.example.gadgetsonlinestore



import android.content.ContentValues.TAG
import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.example.gadgetsonlinestore.data.User
import com.example.gadgetsonlinestore.databinding.ActivitySignUpBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivitySignUpBinding
    //private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.tvLoginHere.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.btnRegister.setOnClickListener {
            signUp()
        }

    }

    private fun signUp() {
        val firstName = binding.etFirstname.text.toString()
        val lastName = binding.etLastname.text.toString()
        val email = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty() && firstName.isNotEmpty() && lastName.isNotEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener (this@SignUpActivity) { task ->
                            if (task.isSuccessful) {


                                Log.d(TAG, "createUserAccount: Success")
                                Toast.makeText(this@SignUpActivity, "Successfully Registered", Toast.LENGTH_LONG).show()

                                binding.etFirstname.text?.clear()
                                binding.etLastname.text?.clear()
                                binding.etUsername.text?.clear()
                                binding.etPassword.text?.clear()

                                val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                                startActivity(intent)
                            } else {
                                Log.w(TAG, "createUserAccount: Unsuccessful")
                                Toast.makeText(this@SignUpActivity, "Failed Registration", Toast.LENGTH_LONG).show()
                            }
                        }
                        .addOnFailureListener {
                            Toast.makeText(this@SignUpActivity, "Error: ${it.localizedMessage}", Toast.LENGTH_SHORT).show()
                        }
                        .await()
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@SignUpActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                    }
                }
            }
        } else {
            Toast.makeText(this@SignUpActivity, "Fill all fields", Toast.LENGTH_SHORT).show()
        }
    }


}