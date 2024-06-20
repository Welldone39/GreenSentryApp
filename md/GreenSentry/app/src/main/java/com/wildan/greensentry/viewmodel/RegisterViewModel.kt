import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wildan.greensentry.model.BaseResponse
import com.wildan.greensentry.model.RegisterRequest
import com.wildan.greensentry.model.RequestResponse
import com.wildan.greensentry.model.UserRepository
import kotlinx.coroutines.launch
import org.json.JSONObject

class RegisterViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepo = UserRepository()
    val registerResult: MutableLiveData<BaseResponse<RequestResponse>> = MutableLiveData()

    fun registerUser(firstname: String, lastname: String, email: String, pwd: String) {
        registerResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val registerRequest = RegisterRequest(
                    first_name = firstname,
                    last_name = lastname,
                    email = email,
                    password = pwd
                )
                val response = userRepo.registerUser(registerRequest)
                if (response?.code() == 200) {
                    registerResult.value = BaseResponse.Success(response.body())
                } else {
                    val errorResponse = response?.errorBody()?.string()
                    val errorMessage = if (errorResponse != null) {
                        val jsonObject = JSONObject(errorResponse)
                        jsonObject.getString("message")
                    } else {
                        response?.message()
                    }
                    registerResult.value = BaseResponse.Error(errorMessage)
                }
            } catch (ex: Exception) {
                registerResult.value = BaseResponse.Error(ex.message)
            }
        }
    }
}
