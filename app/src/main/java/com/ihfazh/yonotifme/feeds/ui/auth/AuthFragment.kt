package com.ihfazh.yonotifme.feeds.ui.auth

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.Scopes
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Scope
import com.google.android.gms.tasks.Task
import com.ihfazh.yonotifme.BuildConfig
import com.ihfazh.yonotifme.R
import com.ihfazh.yonotifme.databinding.FragmentAuthBinding
import com.ihfazh.yonotifme.databinding.FragmentFeedDetailBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AuthFragment: Fragment() {
    private var _binding: FragmentAuthBinding? = null
    private val binding
        get() = _binding!!
    private val viewModel: AuthViewModel by viewModels()
    private lateinit var signInClient: GoogleSignInClient

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(BuildConfig.SERVER_GOOGLE_CLIENT_ID)
            .requestServerAuthCode(BuildConfig.SERVER_GOOGLE_CLIENT_ID)
            .requestEmail()
            .requestProfile()
            .requestScopes(Scope(Scopes.OPEN_ID))
            .build()
        binding.googleSigninButton.apply {
            setSize(SignInButton.SIZE_WIDE)
            setOnClickListener{
                signIn()
            }
        }
        signInClient = GoogleSignIn.getClient(requireContext(), gso)

        signInClient.silentSignIn()
            .addOnCompleteListener(requireActivity()){
                handleSigninResult(it)
            }
        val account = GoogleSignIn.getLastSignedInAccount(requireContext())
        updateUI(account)
    }

    private val signInResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
        handleSigninResult(task)
    }

    private fun handleSigninResult(task: Task<GoogleSignInAccount>?) {
        try {
            val account = task?.getResult(ApiException::class.java)
            account?.run {
                val token = idToken
                val code = serverAuthCode

                if (token != null && code != null){
                    viewModel.login(token, code)
                }
                Log.d("inside account", "handleSigninResult: token ${token} code $code")

                // todo, use retrofit
            }
            Log.d("auth", "handleSigninResult: hello world ${account}")
        } catch (e: ApiException){
            Log.w("AUTH", "signInResult failed: code = ${e.statusCode}", )
            updateUI(null)
        }

    }

    private fun signIn() {
        signInResult.launch(signInClient.signInIntent)
    }

    private fun updateUI(account: GoogleSignInAccount?) {
        if (account != null){
            val action = AuthFragmentDirections.actionAuthFragmentToFeedListFragment()
            findNavController().navigate(action)
            Log.d("LOGIN", "updateUI: ${account.email}")
            // redirect to home page
        } else {
            Log.d("LOGIN", "GAK DAPAT")
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}