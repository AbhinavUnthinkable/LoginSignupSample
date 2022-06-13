package com.chalo.kyc

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.chalo.kyc.databinding.FragmentOtpBinding
class VerifyFragment : Fragment(R.layout.fragment_otp) {

    lateinit var binding : FragmentOtpBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOtpBinding.bind(view)
        binding.loginButton.setOnClickListener {
            val id = requireArguments()["id"] as String
          //  firebaseAuthCredential(PhoneAuthProvider.getCredential(id,binding.otp.text.toString()))
        }
    }

//    private fun firebaseAuthCredential(credential: PhoneAuthCredential) {
//        FirebaseAuth.getInstance().signInWithCredential(credential).addOnSuccessListener{ it ->
//            Toast.makeText(requireActivity(),"login successful", Toast.LENGTH_LONG).show()
//        }.addOnFailureListener {
//            it.printStackTrace()
//            Toast.makeText(requireActivity(),"something went wrong try again", Toast.LENGTH_LONG).show()
//        }
//    }
}