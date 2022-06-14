package com.chalo.kyc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.chalo.kyc.R
import com.chalo.kyc.databinding.FragmentLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.Scopes
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.Scope

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GoogleLoginFragment : Fragment() {

    lateinit var googleApiClient: GoogleSignInClient
    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!

    private val googleSignInCallback =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val account = task.getResult(ApiException::class.java)
                val authCode = account.serverAuthCode
                println(authCode)
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



    private fun signInGoogle() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestServerAuthCode(getString(R.string.default_web_client_id_))
            .requestScopes(Scope(Scopes.PROFILE))
            .requestEmail()
            .build()
        val googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
        googleApiClient = googleSignInClient
        googleSignInCallback.launch(googleSignInClient.signInIntent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}