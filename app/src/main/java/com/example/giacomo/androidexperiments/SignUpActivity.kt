package com.example.giacomo.androidexperiments

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    private var mFirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        emailText.setText(intent.extras["email"].toString())
        passowordText.setText(intent.extras["password"].toString())


        signUpButton.setOnClickListener {
            if(emailText.text.isNullOrEmpty() || passowordText.text.isNullOrEmpty()) return@setOnClickListener
            signUp(emailText.text.toString(), passowordText.text.toString())
        }

    }


    private fun signUp(email: String, password: String) {
        mFirebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, { task ->
            if (task.isSuccessful) {
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                Toast.makeText(this, task.exception.toString(), Toast.LENGTH_LONG).show()
            }
        })

    }
}
