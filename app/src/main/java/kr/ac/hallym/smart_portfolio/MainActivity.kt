package kr.ac.hallym.smart_portfolio

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.auth.FirebaseAuth
import kr.ac.hallym.smart_portfolio.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_sign_up)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //night mode 막기
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        firebaseAuth = FirebaseAuth.getInstance()

        //회원가입창으로 이동
        binding.loginLink.setOnClickListener {
            val intent = Intent(this,login_form::class.java)
            startActivity(intent)
        }

        //회원가입 버튼 클릭
        binding.btnrgs.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val pass =  binding.passEt.text.toString()
            val configPass = binding.confirmPassEt.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty() && configPass.isNotEmpty()){
                if (pass == configPass){

                    firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener {
                        if (it.isSuccessful){
                            // 회원가입 성공시 이동
                            val intent = Intent(this, UserInfoActivity::class.java)
                            startActivity(intent)
                            finish()
                        }else{
                            //팝업 알림
                            var ad = AlertDialog.Builder(this)
                            ad.setTitle("회원가입")
                            ad.setMessage("회원가입에 실패했습니다.")
                            ad.setPositiveButton("Ok", null)
                            ad.show()

                            //EditText안 text삭제
                            binding.emailEt.text?.clear()
                            binding.passEt.text?.clear()
                            binding.confirmPassEt.text?.clear()
                        }
                    }
                }else{
                    Toast.makeText(this,"비밀번호가 일치하지 않습니다!",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"비어있는 칸이 있습니다!",Toast.LENGTH_SHORT).show()
            }
        }


    }
}