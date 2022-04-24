package com.example.bscan

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.VisibleForTesting
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_loginactivity.*
import kotlinx.android.synthetic.main.activity_signupscreen.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

private lateinit var auth: FirebaseAuth
private val userref = Firebase.firestore.collection("users")
class signupscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signupscreen)

        auth = Firebase.auth

        button2.setOnClickListener{
            progressBar2.visibility= View.VISIBLE

            var email = editTextTextEmailAddress.text.toString()
            var password = editTextTextPassword2.text.toString()
            var name = editTextTextPersonName2.text.toString()
            var name2 = editTextTextPersonName3.text.toString()
            var confpass= editTextTextPassword3.text.toString()

            if (name!=null &&name2!=null && password == confpass){
              auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        progressBar2.visibility = View.GONE
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = auth.currentUser
                        val user1 = Users(user.toString(),name,name2,email)
                        saveuser(user1)
                        Toast.makeText(baseContext, "Signed up suceesfully",
                        Toast.LENGTH_SHORT).show()

                        val intent = Intent(this,loginactivity::class.java)
                        startActivity(intent)
                        finish()

                    } else {
                        progressBar2.visibility = View.GONE
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, task.exception!!.message.toString(),
                            Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(baseContext,"fill sections correctly",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun saveuser(user:Users)= CoroutineScope(Dispatchers.Main).launch{
        try {
            userref.add(user)

        }catch(e:Exception){
            withContext(Dispatchers.Main){
                Toast.makeText(this@signupscreen,e.message,Toast.LENGTH_LONG).show()
            }
        }
    }

}