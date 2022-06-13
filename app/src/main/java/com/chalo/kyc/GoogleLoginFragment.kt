package com.chalo.kyc

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.chalo.kyc.databinding.FragmentLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GoogleLoginFragment : Fragment() {

        lateinit var googleApiClient: GoogleApiClient
        private var _binding: FragmentLoginBinding? = null

        private val binding get() = _binding!!

        private val googleSignInCallback =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
                try {
                    val account = task.getResult(ApiException::class.java)
                    fireBaseAuthWithGoogle(account)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnGoogle.setOnClickListener {
            signInGoogle()
        }
    }

    private fun fireBaseAuthWithGoogle(account: GoogleSignInAccount) {
        val mAuth = FirebaseAuth.getInstance()
        val authCredential = GoogleAuthProvider.getCredential(account.idToken, null)
        mAuth.signInWithCredential(authCredential)
            .addOnSuccessListener { auth ->
                Firebase.firestore.collection("users")
                    .document(auth.user!!.uid)
                    .get()
                    .addOnSuccessListener { res ->
                        if (res.exists()) {
                            val user = User(
                                name = res["name"] as String?,
                                email = res["email"] as String?,
                                photo = res["photo"] as String?,
                                active = res["active"] as Boolean
                            )
                            if (user.active) {
                                Toast.makeText(
                                    requireActivity(),
                                    "you can use the app",
                                    Toast.LENGTH_LONG
                                ).show()
                            }else{
                                Toast.makeText(
                                    requireActivity(),
                                    "you are not active yet please try after some time",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }else{
                            val user = User(name = auth.user!!.displayName,
                                email = auth.user!!.email,
                            photo = auth.user!!.photoUrl.toString())
                            Firebase.firestore.collection("users")
                                .document(auth.user!!.uid)
                                .set(user)
                                .addOnSuccessListener {
                                    Toast.makeText(
                                        requireActivity(),
                                        "you are not active yet please try after some time",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                                .addOnFailureListener {
                                    Toast.makeText(
                                        requireActivity(),
                                        "something went wrong , try again",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                        }
                    }
            }
    }

    private fun signInGoogle() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id_))
            .requestEmail()
            .build()
        val googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
        googleApiClient = googleSignInClient.asGoogleApiClient()
        googleSignInCallback.launch(googleSignInClient.signInIntent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}