package com.inmy.products.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import com.inmy.products.HomeActivty

import com.inmy.products.MainActivity
import com.inmy.products.R
import java.util.concurrent.TimeUnit


class LoginFragment : Fragment(){

    companion object {
        const val TAG = "PhoneAuthActivity"
        fun newInstance() = LoginFragment()
    }

    var mPhoneNumberField: EditText? = null
    var mVerificationField:EditText? = null
    var mStartButton: Button? = null
    var mVerifyButton:Button? = null
    var mResendButton:Button? = null


    private var mResendToken: ForceResendingToken? = null
    private var mCallbacks: OnVerificationStateChangedCallbacks? = null
    var mVerificationId: String? = null


    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_login, container, false)

        mPhoneNumberField = root.findViewById(R.id.field_phone_number)
        mVerificationField = root.findViewById(R.id.field_verification_code)

        mStartButton = root.findViewById(R.id.button_start_verification)
        mVerifyButton = root.findViewById(R.id.button_verify_phone)
        mResendButton = root.findViewById(R.id.button_resend)

//        loginViewModel.registerValidationCallback ({
//
//        },{
//
//        },{
//
//        })


        mCallbacks = object : OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                Log.d(
                   TAG,
                    "onVerificationCompleted:$credential"
                )
                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {

                if (e is FirebaseAuthInvalidCredentialsException) {
                    mPhoneNumberField!!.error = "Invalid phone number."
                } else if (e is FirebaseTooManyRequestsException) {
                    Snackbar.make(
                        root.findViewById(android.R.id.content), "Quota exceeded.",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onCodeSent(
                verificationId: String,
                token: ForceResendingToken
            ) {
                Log.d(TAG, "onCodeSent:$verificationId")
                mVerificationId = verificationId
                mResendToken = token
            }
        }

        mStartButton?.setOnClickListener{
            loginViewModel.validatePhoneNumber(mPhoneNumberField!!.text.toString()){
                startPhoneNumberVerification(it)
            }
        }

        mVerifyButton?.setOnClickListener{
            val code = mVerificationField!!.text.toString()

            if (TextUtils.isEmpty(code)) {
                mVerificationField!!.error = "Cannot be empty."
                //return
            }
            val credential = loginViewModel.verifyPhoneNumberWithCode(mVerificationId, code)
            signInWithPhoneAuthCredential(credential)

        }
        mResendButton?.setOnClickListener{
            resendVerificationCode(
                mPhoneNumberField!!.text.toString(),
                mResendToken)
        }

        return root
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        activity?.let {
            loginViewModel.mAuth!!.signInWithCredential(credential)
                .addOnCompleteListener(
                    it
                ) { task ->
                    if (task.isSuccessful) {

                        Log.d(TAG, "signInWithCredential:success")
                        val user = task.result!!.user
                        startActivity(Intent(context, MainActivity::class.java))
                           // finish()
                    } else {
                        Log.w(
                            TAG,
                            "signInWithCredential:failure",
                            task.exception
                        )
                        if (task.exception is FirebaseAuthInvalidCredentialsException) {
                            mVerificationField!!.error = "Invalid code."
                        }
                    }
                }
        }
    }

    private fun startPhoneNumberVerification(phoneNumber: String) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            phoneNumber,  // Phone number to verify
            60,  // Timeout duration
            TimeUnit.SECONDS,  // Unit of timeout
            requireActivity(),  // Activity (for callback binding)
            mCallbacks!!
        ) // OnVerificationStateChangedCallbacks
    }



    private fun resendVerificationCode(
        phoneNumber: String,
        token: ForceResendingToken?
    ) {
        activity?.let {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,  // Phone number to verify
                60,  // Timeout duration
                TimeUnit.SECONDS,  // Unit of timeout
                it,  // Activity (for callback binding)
                mCallbacks!!,  // OnVerificationStateChangedCallbacks
                token
            )
        } // ForceResendingToken from callbacks
    }

     override fun onStart() {
        super.onStart()
        val currentUser = loginViewModel.mAuth!!.currentUser
        if (currentUser != null) {
            val intent : Intent = Intent(context, MainActivity::class.java)
            intent.putExtra("",currentUser)
            startActivity( intent)

           // finish()
        }
    }




}