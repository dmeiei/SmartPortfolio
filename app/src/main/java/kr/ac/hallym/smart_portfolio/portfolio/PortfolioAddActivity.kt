package kr.ac.hallym.smart_portfolio.portfolio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kr.ac.hallym.smart_portfolio.databinding.ActivityPortfolioAddBinding

class PortfolioAddActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPortfolioAddBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var uid: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_portfolio_add)

        binding = ActivityPortfolioAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.portfolioAddBtn.setOnClickListener {
            val portfolioTitle = binding.portfolioTitleEdit.text.toString()
            val portfolioContent = binding.portfolioContentsEdit.text.toString()

            if (portfolioTitle.isNotEmpty() && portfolioContent.isNotEmpty()){
                uploadData()
            }else{
                Toast.makeText(this,"비어있는 칸이 있습니다!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun uploadData(){
        val portfolioTitle = binding.portfolioTitleEdit.text.toString()
        val portfolioContent = binding.portfolioContentsEdit.text.toString()
        uid = auth.currentUser?.uid.toString()

        val portfolio = PortfolioModel(portfolioTitle,portfolioContent)
        database = FirebaseDatabase.getInstance().getReference("portfolio")

        database.child(portfolioTitle)
            .setValue(portfolio)
            .addOnSuccessListener {
                finish()
            }
    }
}