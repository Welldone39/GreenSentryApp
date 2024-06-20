import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wildan.greensentry.model.BaseResponse
import com.wildan.greensentry.model.LoginRequest
import com.wildan.greensentry.model.LoginResponse
import com.wildan.greensentry.model.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepo = UserRepository()
    val loginResult: MutableLiveData<BaseResponse<LoginResponse>> = MutableLiveData()
    val ValidationResult: MutableLiveData<EmailValidationState> = MutableLiveData()

    enum class EmailValidationState {
        VALID,
        INVALID_FORMAT,
        NOT_REGISTERED,
        WRONG_PASSWORD
    }

    fun loginUser(email: String, pwd: String) {

        if (!isEmailValid(email)) {
            ValidationResult.value = EmailValidationState.INVALID_FORMAT
            return
        }

        loginResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val loginRequest = LoginRequest(
                    password = pwd,
                    email = email
                )
                val response = userRepo.loginUser(loginRequest = loginRequest)
                if (response?.code() == 200) {
                    loginResult.value = BaseResponse.Success(response.body())
                } else if (response?.code() == 404) { // Assuming 404 means email not registered
                    ValidationResult.value = EmailValidationState.NOT_REGISTERED
                } else if (response?.code() == 401) { // Assuming 401 means wrong password
                    ValidationResult.value = EmailValidationState.WRONG_PASSWORD
                } else {
                    loginResult.value = BaseResponse.Error(response?.message())
                }

            } catch (ex: Exception) {
                loginResult.value = BaseResponse.Error(ex.message)
            }
        }
    }

    private fun isEmailValid(email: String): Boolean {
        // Implement your email validation logic here
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
