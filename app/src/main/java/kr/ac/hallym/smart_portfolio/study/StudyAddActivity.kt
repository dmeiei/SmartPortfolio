package kr.ac.hallym.smart_portfolio.study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kr.ac.hallym.smart_portfolio.databinding.ActivityStudyAddBinding

class StudyAddActivity : AppCompatActivity() {
    private lateinit var binding : ActivityStudyAddBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var uid: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_study_add)

        binding = ActivityStudyAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()

        binding.studyAddBtn.setOnClickListener {
            val studyTitle = binding.studyTitleEdit.text.toString()
            val studyContents = binding.studyContentsEdit.text.toString()

            if (studyTitle.isNotEmpty() && studyContents.isNotEmpty()){
                uploadData()
            }else{
                Toast.makeText(this,"비어있는 칸이 있습니다!", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun uploadData() {
        val studyTitle = binding.studyTitleEdit.text.toString()
        val studyContents = binding.studyContentsEdit.text.toString()
        uid = auth.currentUser?.uid.toString()
        val info = InfoModel(studyTitle,studyContents)
//        val currentDate = DateFormat.getDateTimeInstance().format(Calendar.getInstance().time)
        database = FirebaseDatabase.getInstance().getReference("study")

        database.child(studyTitle)
            .setValue(info)
            .addOnSuccessListener {
                finish()
            }
    }
}