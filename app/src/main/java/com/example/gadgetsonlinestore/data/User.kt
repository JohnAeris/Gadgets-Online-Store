package com.example.gadgetsonlinestore.data

import com.google.firebase.database.Exclude

data class User(
    /*@get:Exclude
    var id: String? = null,*/
    var firstName: String? = null,
    var lastName: String? = null,
    var email: String? = null,
    var password: String? = null
)
