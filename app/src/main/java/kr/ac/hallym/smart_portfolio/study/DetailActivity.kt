package kr.ac.hallym.smart_portfolio.study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.ac.hallym.smart_portfolio.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_detail)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val study = intent.getParcelableExtra<InfoModel>("currentitem")
        if(study != null){
            binding.sDetailTitle.text = study.title
            binding.sDetailContent.text = study.contents
        }
    }
}