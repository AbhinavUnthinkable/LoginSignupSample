package com.chalo.kyc

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.chalo.kyc.databinding.FragmentLoginBinding
import com.chalo.kyc.databinding.FragmentMobileLoginBinding
import com.google.firebase.FirebaseException
import java.util.concurrent.TimeUnit

class MobileLoginFragment : Fragment(R.layout.fragment_mobile_login) {


    private var _binding: FragmentMobileLoginBinding? = null

    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        _binding = FragmentMobileLoginBinding.bind(view).apply {
//            loginButton.setOnClickListener {
//                val options = PhoneAuthOptions.newBuilder(FirebaseAuth.getInstance())
//                    .setPhoneNumber("+91${binding.mobileNumer.text}")
//                    .setTimeout(30, TimeUnit.SECONDS)
//                    .setActivity(requireActivity())
//                    .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//                        override fun onCodeAutoRetrievalTimeOut(p0: String) {
//                            super.onCodeAutoRetrievalTimeOut(p0)
//
//                        }
//
//                        override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) { //Getting the code sent by SMS
////                            val code: String? = phoneAuthCredential.smsCode
////                            code?.let {
////                                if (it.length > 3) {
////                                    val credential = PhoneAuthProvider.getCredential(verificationId, it)
////                                    firebaseAuthCredential(credential)
////                                } else {
////                                    Toast.makeText(requireActivity(),"something went wrong try again",Toast.LENGTH_LONG).show()
////                                }
////                            }
//                        }
//
//                        override fun onVerificationFailed(e: FirebaseException) {
//                            e.printStackTrace()
//                            Toast.makeText(requireActivity(),"something went wrong try again",Toast.LENGTH_LONG).show()
//                        }
//
//                        override fun onCodeSent(
//                            s: String,
//                            forceResendingToken: PhoneAuthProvider.ForceResendingToken
//                        ) {
//                            super.onCodeSent(s, forceResendingToken)
//                            findNavController().navigate(R.id.action_mobileLoginFragment_to_verifyFragment,
//                                bundleOf("id" to s))
//
//                        }
//                    })
//                    .build()
//
//                PhoneAuthProvider.verifyPhoneNumber(options)
//            }
//        }
    }



}