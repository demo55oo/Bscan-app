package com.example.bscan

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.demo.mainviewmodel
import com.demo.mainviewmodelfactory
import com.example.api.repositary
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_loginactivity.*
import kotlinx.android.synthetic.main.activity_signupscreen.*

private lateinit var auth: FirebaseAuth
private  lateinit var viewModel: mainviewmodel

class loginactivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth

/*
        val repository= repositary()
        val viewModelFactory = mainviewmodelfactory(repository)
        viewModel =  ViewModelProvider(this, viewModelFactory).get(mainviewmodel::class.java)

        viewModel.gettokens("0xcf759d3ffca78eab24f22151b6456ca8ce7b836d0a4e4091b5e1344e83045696","0xf7E8d9F6462b37bA2e4cd29d526AEc52c7B9175D",1,100,"asc","RSV8C5U2MT5KX6XYGBMBVUEIP1T9DFRCXJ")
        viewModel.myresponce.observe(this,{responce ->

            if (responce.isSuccessful) {

                Log.d("look", "${responce.body()}  ${responce.code()}")

            } else {
                Log.d("err", "ERROR cause${responce.errorBody()} $responce ${responce.code()} ")
            }})*/
        val into = Intent(this,home::class.java)
        setContentView(R.layout.activity_loginactivity)

        textView2.setOnClickListener {
            val intenty = Intent(this,signupscreen::class.java)
            startActivity(intenty)
        }
        button.setOnClickListener {
            var email = editTextTextPersonName.text.toString()
            var password = editTextTextPassword.text.toString()
            progressBar.visibility = View.VISIBLE
            try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        val user = auth.currentUser
                        progressBar.visibility = View.GONE
                        startActivity(into)
                        finish()

                    } else {
                        // If sign in fails, display a message to the user.
                        progressBar.visibility = View.GONE
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }
                }catch (e:Exception){
                    Toast.makeText(this,e.message,Toast.LENGTH_SHORT).show()
                }
        }
    }
/*    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = Firebase.auth.currentUser
        val into = Intent(this,home::class.java)

        if(currentUser != null){
            startActivity(into)
        }
    }*/
}