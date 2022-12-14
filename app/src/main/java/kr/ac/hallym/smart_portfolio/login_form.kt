package kr.ac.hallym.smart_portfolio

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.auth.FirebaseAuth
import kr.ac.hallym.smart_portfolio.databinding.ActivityLoginFormBinding

class login_form : AppCompatActivity() {
    lateinit var binding : ActivityLoginFormBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
        binding = ActivityLoginFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //night mode 막기
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        firebaseAuth = FirebaseAuth.getInstance()

        //회원가입창으로 이동
        binding.regisLink.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        //로그인 버튼 클릭
        binding.loginBtn.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val pass = binding.passEt.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()){

                firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener{
                    if (it.isSuccessful){
                        val intent = Intent(this,SplashActivity::class.java)
                        startActivity(intent)
                        finish()
                    }else{
                        var ad = AlertDialog.Builder(this)
                        ad.setTitle("로그인 오류")
                        ad.setMessage("이메일 또는 비밀번호가 틀립니다!")
                        ad.setPositiveButton("Ok", null)
                        ad.show()
                    }
                }
            }else{
                Toast.makeText(this,"비어있는 칸이 있습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()

        if (firebaseAuth.currentUser != null){
            val intent = Intent(this,SplashActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
