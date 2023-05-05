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

class LoginActivity : AppCompatActivity() {

    lateinit var etemailaddress: EditText
    lateinit var etpassword: EditText
    lateinit var btnlogin: Button
    lateinit var tvtosignup: TextView
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etemailaddress = findViewById(R.id.etEmailAddress)
        etpassword = findViewById(R.id.etPassword)
        btnlogin = findViewById(R.id.btnLogin)
        tvtosignup = findViewById(R.id.tvRedirectSignUp)

        auth = Firebase.auth

        tvtosignup.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        btnlogin.setOnClickListener {
            login()
        }
    }
    private fun login(){
        val email = etemailaddress.text.toString()
        val pass = etpassword.text.toString()

        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this){
            if (it.isSuccessful){
                Toast.makeText(this, "Successfully logged in", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}