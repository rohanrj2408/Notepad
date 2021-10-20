package com.rohanrj.notpad
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rohanrj.notpad.util.HomeActivity
import java.util.*
import kotlin.concurrent.schedule

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Timer("SettingUp", false).schedule(500) {
            startActivity<HomeActivity>()
        }
    }

    private fun <T> startActivity() {
        TODO("Not yet implemented")
    }

    override fun setContentView(activitySplash: Int) {
                    TODO("Not yet implemented")
    }
}
