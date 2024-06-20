import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.FragmentActivity

class SharedPreference(activity: FragmentActivity) {
    private val LOGIN_STATUS = "login_status"
    private val APP_LANGUAGE = "app_language"
    private val PREF_NAME = "MyAppPrefs"

    private val sharedPreferences: SharedPreferences = activity.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    // Setter untuk status login
    fun setLoginStatus(isLoggedIn: Boolean) {
        sharedPreferences.edit().putBoolean(LOGIN_STATUS, isLoggedIn).apply()
    }

    // Getter untuk status login, default false jika tidak ada
    fun getLoginStatus(): Boolean {
        return sharedPreferences.getBoolean(LOGIN_STATUS, false)
    }

    // Setter untuk bahasa aplikasi
    fun setAppLanguage(language: String) {
        sharedPreferences.edit().putString(APP_LANGUAGE, language).apply()
    }

    // Getter untuk bahasa aplikasi, default "en" jika tidak ada
    fun getAppLanguage(): String {
        return sharedPreferences.getString(APP_LANGUAGE, "en") ?: "en"
    }
}
