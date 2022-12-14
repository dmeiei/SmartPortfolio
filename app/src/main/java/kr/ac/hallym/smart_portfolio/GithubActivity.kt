package kr.ac.hallym.smart_portfolio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebViewClient
import kr.ac.hallym.smart_portfolio.databinding.ActivityGithubBinding

class GithubActivity : AppCompatActivity() {
    lateinit var binding: ActivityGithubBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_github)
        binding = ActivityGithubBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //custom toolbar 사용
        setSupportActionBar(binding.mainToolbar)
        //툴바 기본 title 사용 x
        supportActionBar?.setDisplayShowTitleEnabled(false)
        //뒤로가기 버튼
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //webview
        binding.webview.webViewClient = WebViewClient()
        binding.webview.loadUrl("https://github.com/dmeiei")

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