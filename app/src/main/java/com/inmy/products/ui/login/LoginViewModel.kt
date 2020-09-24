package com.inmy.products.ui.login

import android.text.TextUtils.*
import androidx.lifecycle.ViewModel
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class LoginViewModel : ViewModel() {
    var mAuth: FirebaseAuth? = null
    var callbackHolder : CallBack? = null
    init {
        mAuth = FirebaseAuth.getInstance()
    }

    fun validatePhoneNumber(phoneNumber: String, callback: (String) -> Unit) : Boolean {
        if (!isEmpty(phoneNumber)){
            callback(phoneNumber)
            return true
        }
        return false
    }

    fun registerValidationCallback(success: (PhoneAuthCredential) -> Unit, failure: (FirebaseException) -> Unit, codeSent: (p0: String, p1: PhoneAuthProvider.ForceResendingToken) -> Unit) {
        callbackHolder = CallBack(success, failure)
    }

    class CallBack (val success: (PhoneAuthCredential) -> Unit, val failure: (FirebaseException) -> Unit): PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

        override fun onVerificationCompleted(p0: PhoneAuthCredential) {
            success(p0)
        }

        override fun onVerificationFailed(p0: FirebaseException) {
            failure(p0)
        }

    }

     fun verifyPhoneNumberWithCode(verificationId: String?, code: String) : PhoneAuthCredential{

         return PhoneAuthProvider.getCredential(verificationId!!, code)
    }
}