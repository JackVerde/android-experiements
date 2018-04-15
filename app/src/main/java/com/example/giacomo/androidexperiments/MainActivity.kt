package com.example.giacomo.androidexperiments

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.FirebaseAuth
import android.content.Intent
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private var mFirebaseAuth = FirebaseAuth.getInstance()
    private var mFirebaseUser: FirebaseUser? = null
    private var mUsername: String? = null

    private var mFirebaseDatabase = FirebaseDatabase.getInstance().reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        LogOutButton.setOnClickListener {
            mFirebaseAuth.signOut()
            mUsername = null
            finish()
            startActivity(intent)
        }

        mFirebaseUser = mFirebaseAuth?.currentUser
        if (mFirebaseUser == null) {
            // Not signed in, launch the Sign In activity
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
            return
        } else {
            mUsername = mFirebaseUser?.email
            usernameText.text = mUsername
            if (mFirebaseUser?.photoUrl != null) {
                //mPhotoUrl = mFirebaseUser?.getPhotoUrl().toString()
            }
        }

        //loadDatabase(mFirebaseDatabase)

    }


    override fun onBackPressed() {
        //super.onBackPressed()
    }

    fun loadDatabase(firebaseData: DatabaseReference) {
        val availableSalads: List<Salad> = mutableListOf(
                Salad("Gherkin", "Fresh and delicious"),
                Salad("Lettuce", "Easy to prepare"),
                Salad("Tomato", "Boring but healthy"),
                Salad("Zucchini", "Healthy and gross")
        )
        availableSalads.forEach {
            val key = firebaseData.child("salads").push().key
            it.uuid = key
            firebaseData.child("salads").child(key).setValue(it)
        }
    }


    data class Salad(
            val name: String = "",
            val description: String = "",
            var uuid: String = "")
}
