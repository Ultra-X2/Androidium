package com.example.amotherone.data

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.amotherone.models.User
import com.example.amotherone.navigation.ROUTE_LOGIN
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AuthViewModel (
    var navController: NavController,
    var context: Context

): ViewModel()
{
    var mAuth: FirebaseAuth=FirebaseAuth.getInstance()
    fun SignUp(
        fname:String ,
        lname:String ,
        email:String,pass:String,
        //userid:String
    ){
        if (fname.isEmpty()&&lname.isEmpty()&&email.isEmpty() &&pass.isEmpty()){
            Toast.makeText(
                context,
                "please fill in all details",
                Toast.LENGTH_LONG).show()
        }else{
            mAuth.createUserWithEmailAndPassword(email,pass)
            .addOnCompleteListener{
                if (it.isSuccessful){
                    val userdata= User(
                        fname, email, pass, mAuth.currentUser!!.uid,
                        userid = null.toString()
                    )
                    val regRef=FirebaseDatabase.getInstance().getReference().child("User/"+mAuth.currentUser!!.uid).setValue(userdata).addOnCompleteListener {
                        if (it.isSuccessful){
                            Toast.makeText(
                                context,
                                "Sucessfuly registered",
                                Toast.LENGTH_LONG).show()
                        }else{
                            Toast.makeText(
                                context,
                                "failed to registered",
                                Toast.LENGTH_LONG).show()
                        }

                        }
                    }
                }
        }
    }
    fun login(email: String,pass: String){


        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener {

            if (it.isSuccessful){
                Toast.makeText(context,"Succeffully Logged in",Toast.LENGTH_LONG).show()
                navController.navigate("home")
//                navController.navigate(ROUTE_REGISTER)TO TAKE YOU TO A DIIFFERNT PAGE
            }else{
                Toast.makeText(context,"${it.exception!!.message}",Toast.LENGTH_LONG).show()
                navController.navigate(ROUTE_LOGIN)
            }
        }

    }
    fun logout(){
        mAuth.signOut()
        navController.navigate(ROUTE_LOGIN)
    }
    fun isloggedin():Boolean{
        return mAuth.currentUser !=null
    }

}
