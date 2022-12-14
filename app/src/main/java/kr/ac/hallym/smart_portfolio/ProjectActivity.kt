package kr.ac.hallym.smart_portfolio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kr.ac.hallym.smart_portfolio.databinding.ActivityProjectBinding

class ProjectActivity : AppCompatActivity() {
    lateinit var binding: ActivityProjectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_project)

        binding = ActivityProjectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //custom toolbar 사용
        setSupportActionBar(binding.mainToolbar)
        //툴바 기본 title 사용 x
        supportActionBar?.setDisplayShowTitleEnabled(false)
        //뒤로가기 버튼
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //첫번째 프로젝트 클릭
        binding.projectOne.setOnClickListener {
            val intent = Intent(this,ProjectOne::class.java)
            startActivity(intent)
        }

        //두번째 프로젝트 클릭
        binding.projectTwo.setOnClickListener {
            val intent = Intent(this,ProjectTwo::class.java)
            startActivity(intent)
        }

    }

    //뒤로가기 버튼
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
                return true
            }
            else ->{
                return super.onOptionsItemSelected(item)
            }
        }
    }
}