package com.kyle.luckyfivetest.ui.login

import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.kyle.luckyfivetest.R
import com.kyle.luckyfivetest.databinding.FragmentLoginBinding
import com.kyle.luckyfivetest.ui.base.BaseFragment
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.NidOAuthLogin
import com.navercorp.nid.oauth.OAuthLoginCallback
import com.navercorp.nid.profile.NidProfileCallback
import com.navercorp.nid.profile.data.NidProfileResponse

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {
    override val layoutResId: Int = R.layout.fragment_login
    override val viewModel: LoginViewModel by viewModels()
    override val fragment: String = "로그인"

    private fun moveMainFragment() {
        view?.findNavController()?.navigate(R.id.action_loginFragment_to_mainFragment)
    }

    override fun onCreate() {
        mViewDataBinding.signInGoogle.setOnClickListener {
            requestGoogleLogin()
        }
        mViewDataBinding.signInKakao.setOnClickListener {
            requestKakaoLogin()
        }
        mViewDataBinding.signInNaver.setOnClickListener {
            startNaverLogin()
        }

        checkSignIn()
    }

    // 로그인 check
    private fun checkSignIn() {
//        if (AuthApiClient.instance.hasToken()) {
//            UserApiClient.instance.accessTokenInfo { _, error ->
//                if (error == null) {
//                    Toast.makeText(requireContext(), "이미 카카오 계정으로 로그인 되어있습니다.", Toast.LENGTH_SHORT).show()
//                    moveMainFragment()
//                }
//            }
//        }
    }

    // 구글 로그인 시작

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

    // 구글 로그인 끝

    // 카카오 로그인 시작

    private val mCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Log.e("TAG", "로그인 실패 $error")
        } else if (token != null) {
            Log.e("TAG", "로그인 성공 ${token.accessToken}")
            moveMainFragment()
        }
    }

    private fun requestKakaoLogin() {
        UserApiClient.instance.logout { error ->
            if (error != null)
                Log.e("TAG", "로그아웃 실패 $error")
        }

        // 카카오톡 설치 확인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(requireContext())) {
            // 카카오톡 로그인
            UserApiClient.instance.loginWithKakaoTalk(requireContext()) { token, error ->
                // 로그인 실패 부분
                if (error != null) {
                    Log.e("TAG", "로그인 실패 $error")
                    // 사용자가 취소
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }
                    // 다른 오류
                    else {
                        UserApiClient.instance.loginWithKakaoAccount(requireContext(), callback = mCallback) // 카카오 이메일 로그인
                    }
                }
                // 로그인 성공 부분
                else if (token != null) {
                    Log.e("TAG", "로그인 성공 ${token.accessToken}")
                    moveMainFragment()
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(requireContext(), callback = mCallback) // 카카오 이메일 로그인
        }
    }

    // 카카오 로그인 끝

    // 네이버 로그인 시작

    private fun startNaverLogin(){
        var naverToken :String? = ""

        val profileCallback = object : NidProfileCallback<NidProfileResponse> {
            override fun onSuccess(result: NidProfileResponse) {
                val userId = result.profile?.id
                Log.d("TAG","id: $userId \ntoken: $naverToken")

                moveMainFragment()
            }
            override fun onFailure(httpStatus: Int, message: String) {
                val errorCode = NaverIdLoginSDK.getLastErrorCode().code
                val errorDescription = NaverIdLoginSDK.getLastErrorDescription()
                Log.e("TAG", "errorCode: ${errorCode}\n" + "errorDescription: $errorDescription")
            }
            override fun onError(errorCode: Int, message: String) {
                onFailure(errorCode, message)
            }
        }

        /** OAuthLoginCallback을 authenticate() 메서드 호출 시 파라미터로 전달하거나 NidOAuthLoginButton 객체에 등록하면 인증이 종료되는 것을 확인할 수 있습니다. */
        val oauthLoginCallback = object : OAuthLoginCallback {
            override fun onSuccess() {
                // 네이버 로그인 인증이 성공했을 때 수행할 코드 추가
                naverToken = NaverIdLoginSDK.getAccessToken()
//                var naverRefreshToken = NaverIdLoginSDK.getRefreshToken()
//                var naverExpiresAt = NaverIdLoginSDK.getExpiresAt().toString()
//                var naverTokenType = NaverIdLoginSDK.getTokenType()
//                var naverState = NaverIdLoginSDK.getState().toString()

                //로그인 유저 정보 가져오기
                NidOAuthLogin().callProfileApi(profileCallback)
            }
            override fun onFailure(httpStatus: Int, message: String) {
                val errorCode = NaverIdLoginSDK.getLastErrorCode().code
                val errorDescription = NaverIdLoginSDK.getLastErrorDescription()
                Log.e("TAG", "errorCode: ${errorCode}\n" + "errorDescription: $errorDescription")
            }
            override fun onError(errorCode: Int, message: String) {
                onFailure(errorCode, message)
            }
        }

        NaverIdLoginSDK.authenticate(requireContext(), oauthLoginCallback)
    }

    // 네이버 로그인 끝
}