package com.example.firebaseapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    lateinit var etemailaddress: EditText
    lateinit var etpassword: EditText
    lateinit var etconfirmpass: EditText
    lateinit var btnsignup: Button
    lateinit var tvtologin: TextView
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etemailaddress = findViewById(R.id.etSEmailAddress)
        etpassword = findViewById(R.id.etSPassword)
        etconfirmpass = findViewById(R.id.etSConfPassword)
        btnsignup = findViewById(R.id.btnSSigned)
        tvtologin = findViewById(R.id.tvRedirectLogin)
        auth = Firebase.auth

        tvtologin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        btnsignup.setOnClickListener {
            signedup()
        }

    }
    private fun signedup(){
        val email = etemailaddress.text.toString()
        val password = etpassword.text.toString()
        val confirmpass = etconfirmpass.text.toString()

        if (email.isBlank() || password.isBlank()|| confirmpass.isBlank()){
            Toast.makeText(this, "Please confirm email and password", Toast.LENGTH_LONG).show()
            return
        }else if (password != confirmpass){
            Toast.makeText(this, "password does not match", Toast.LENGTH_LONG).show()
            return
        }
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this){
            if (it.isSuccessful){
                Toast.makeText(this, "signed successful", Toast.LENGTH_LONG).show()

            }else{
                Toast.makeText(this, "Failed to create an account", Toast.LENGTH_LONG).show()
            }
        }

    }
}