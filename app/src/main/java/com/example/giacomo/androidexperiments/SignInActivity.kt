package com.example.giacomo.androidexperiments

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_in.*


class SignInActivity : AppCompatActivity() {

    private var mFirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

            signInButton.setOnClickListener {
                if(emailText.text.isNullOrEmpty() || passowordText.text.isNullOrEmpty()) return@setOnClickListener
                signIn(emailText.text.toString(), passowordText.text.toString())
            }

            signUpText.setOnClickListener {
                var intent = Intent(this, SignUpActivity::class.java)
                intent.putExtra("email", emailText.text)
                intent.putExtra("password", passowordText.text)
                startActivity(intent)
            }

        }

        private fun signIn(email: String, password: String) {
            mFirebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, MainActivity::class.java))
                } else {
                    Toast.makeText(this, task.exception.toString(), Toast.LENGTH_LONG).show()
                }
            })

        }
    }

