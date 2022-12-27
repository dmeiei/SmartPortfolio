package kr.ac.hallym.smart_portfolio.portfolio

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.StorageReference
import kr.ac.hallym.smart_portfolio.databinding.ActivityUpdateProfileBinding


class updateProfile : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var binding: ActivityUpdateProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_update_profile)
        binding = ActivityUpdateProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance()


        binding.portfolioUpdateBtn.setOnClickListener {

            updateData()
        }

        val portfolio = intent.getParcelableExtra<PortfolioModel>("current")
        if (portfolio != null){
            binding.portfolioTitleUpdateEdit.setText(portfolio.title)
            binding.portfolioContentsUpdateEdit.setText(portfolio.contents)

        }

    }

    //내용 수정 (제목은 수정 불가능...)
    private fun updateData(){

        val title = binding.portfolioTitleUpdateEdit.text.toString()
        val contents = binding.portfolioContentsUpdateEdit.text.toString()

        val portfolioModel = PortfolioModel(title,contents)

        database.reference.child("portfolio")
            .child(title)
            .setValue(portfolioModel)
            .addOnSuccessListener {
                Toast.makeText(this,"success",Toast.LENGTH_SHORT).show()
                finish()
            }




    }
}