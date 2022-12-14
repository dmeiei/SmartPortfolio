package kr.ac.hallym.smart_portfolio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.airbnb.lottie.LottieAnimationView
import kr.ac.hallym.smart_portfolio.databinding.ActivitySplashBinding
import kotlin.random.Random

class SplashActivity : AppCompatActivity() {
    lateinit var binding:ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_splash)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val splashImage = binding.splashImage as LottieAnimationView
        splashImage.playAnimation()

        //1초에서 3초 랜덤 실행
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainPortfolio::class.java)
            startActivity(intent)
            finish()
        }, 1000L+ Random.nextLong(3000))
    }
}