package kr.ac.hallym.smart_portfolio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import kr.ac.hallym.smart_portfolio.databinding.ActivityNavigationHeaderBinding

class NavigationHeader : AppCompatActivity() {
    lateinit var binding: ActivityNavigationHeaderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_navigation_header)
        binding = ActivityNavigationHeaderBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}