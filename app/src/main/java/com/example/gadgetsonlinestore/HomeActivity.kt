package com.example.gadgetsonlinestore

import android.app.Person
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.gadgetsonlinestore.data.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val personCollectionRef = Firebase.firestore.collection("Users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        auth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()
        val user = auth.currentUser
        val userID = user?.uid



        loginStatus()

        val docRef = db.collection("Users").document("2ugjB5X03I8ZuXrixIUn")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {

                    Log.d("exist", "DocumentSnapshot data: ${document.data}")

                    tvUserFirstname.text = document.getString("firstName")
                    tvUserLastname.text = document.getString("lastName")
                    tvUserEmail.text = document.getString("email")
                    tvUserPassword.text = document.getString("password")

                } else {
                    Log.d("notExist", "No Such Document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("Error db", "Get failed with ", exception)
            }
    }



    private fun loginStatus() {
        val user = auth.currentUser
        val userID = user?.uid

        if (user == null) {
            tvLoginStatus.text = "Logged Out"
        } else {
            tvLoginStatus.text = userID
        }
    }

    override fun onStart() {
        super.onStart()
        loginStatus()
    }
}