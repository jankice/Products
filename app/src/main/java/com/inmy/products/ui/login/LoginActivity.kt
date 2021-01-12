package com.inmy.products.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.inmy.products.*
import com.inmy.products.databinding.ActivityLoginBinding
import com.inmy.products.ui.home.HomeActivty
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var googleSignInClient: GoogleSignInClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.lifecycleOwner = this
        loginViewModel =
            this.run { ViewModelProvider(this).get(LoginViewModel::class.java) }

        binding.loginViewModel = loginViewModel

        authenticationInitialization()

        signInButton.setOnClickListener {
            signIn()
        }
    }

    private fun authenticationInitialization() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

    }

    override fun onStart() {
        super.onStart()
        // Check if user is
        // in (non-null) and update UI accordingly.
        val currentUser = loginViewModel.mAuth?.currentUser

        if(currentUser != null){
            val intent = Intent(this, HomeActivty::class.java)
            startActivity(intent)
        }
        //updateUI(currentUser)
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, REQ_SIGN_IN)
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        loginViewModel.mAuth?.signInWithCredential(credential)
            ?.addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("TAG", "signInWithCredential:success")
                    val user = loginViewModel.mAuth?.currentUser
                    Log.d("id_token", ""+user?.getIdToken(false)?.getResult()?.token)
                    valueToPreference(this,PREFERENCE_KEY_ID_TOKEN,
                        ""+user?.getIdToken(false)?.getResult()?.token,CONST_SAVE)
                    val intent = Intent(this, HomeActivty::class.java)
                    startActivity(intent)
                } else {
                    Log.w("TAG", "signInWithCredential:failure", task.exception)
                }

            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQ_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                Log.d("TAG", "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {

                Log.w("TAG", "Google sign in failed", e)

            }
        }
    }
}