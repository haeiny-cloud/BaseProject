package com.kyle.luckyfivetest.ui.login

import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.kyle.luckyfivetest.R
import com.kyle.luckyfivetest.databinding.FragmentLoginBinding
import com.kyle.luckyfivetest.ui.base.BaseFragment

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {
    override val layoutResId: Int = R.layout.fragment_login
    override val viewModel: LoginViewModel by viewModels()
    override val fragment: String = "로그인"

    private val googleSignInClient: GoogleSignInClient by lazy { getGoogleClient() }

    private val googleAuthLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)

        try {
            val account = task.getResult(ApiException::class.java)

            // 이름, 이메일 등이 필요하다면 아래와 같이 account를 통해 각 메소드를 불러올 수 있다.
            val userName = account.givenName
            val serverAuth = account.serverAuthCode

            Log.d("TAG", "userName: $userName")
            Log.d("TAG", "authCode: $serverAuth")

            moveMainFragment()

        } catch (e: ApiException) {
            Log.e(LoginFragment::class.java.simpleName, e.stackTraceToString())
        }
    }

    private fun moveMainFragment() {
        view?.findNavController()?.navigate(R.id.action_loginFragment_to_mainFragment)
    }

    override fun onCreate() {
        mViewDataBinding.signInGoogle.setOnClickListener {
            requestGoogleLogin()
        }
    }

    private fun requestGoogleLogin() {
        googleSignInClient.signOut()
        val signInIntent = googleSignInClient.signInIntent
        googleAuthLauncher.launch(signInIntent)
    }

    private fun getGoogleClient(): GoogleSignInClient {
        val googleSignInOption = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestServerAuthCode(getString(R.string.webClientId)) // string 파일에 저장해둔 client id 를 이용해 server authcode를 요청한다.
            .requestEmail() // 이메일도 요청할 수 있다.
            .build()

        return GoogleSignIn.getClient(requireActivity(), googleSignInOption)
    }

}