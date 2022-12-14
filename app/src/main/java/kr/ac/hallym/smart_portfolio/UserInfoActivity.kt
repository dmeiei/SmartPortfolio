package kr.ac.hallym.smart_portfolio

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kr.ac.hallym.smart_portfolio.databinding.ActivityUserInfoBinding

class UserInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserInfoBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var storage: FirebaseStorage
    private lateinit var selectedImg : Uri
//    private lateinit var dialog: AlertDialog.Builder

    private lateinit var reference: StorageReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main3)
        binding = ActivityUserInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        dialog = AlertDialog.Builder(this)
//            .setMessage("updating profile...")
//            .setCancelable(false)

        database = FirebaseDatabase.getInstance()
        storage = FirebaseStorage.getInstance()
        auth = FirebaseAuth.getInstance()

        //이미지 클릭
        binding.userImageView.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "image/*"
            startActivityForResult(intent,1)
        }

        //저장 버튼 클릭
        binding.savebutton.setOnClickListener {
            val username = binding.nameEdit.text.toString()
            val userphone = binding.phoneNumEdit.text.toString()
            val useraddress = binding.addressEdit.text.toString()

            if (username.isNotEmpty() && userphone.isNotEmpty() && useraddress.isNotEmpty()){
                uploadData()
            }else if(selectedImg == null){
                Toast.makeText(this,"프로필 이미지를 등록해 주세요", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"비어있는 칸이 있습니다!", Toast.LENGTH_SHORT).show()
            }

        }



    }
    private fun uploadData(){

        reference = FirebaseStorage.getInstance().getReference("users/"+auth.currentUser?.uid+".jpg")
        reference.putFile(selectedImg).addOnCompleteListener{
            if (it.isSuccessful){
                reference.downloadUrl.addOnSuccessListener{ task ->
                    uploadInfo(task.toString())
                }
            }
        }
    }

    private fun uploadInfo(imgUri: String){
        val username = binding.nameEdit.text.toString()
        val userphone = binding.phoneNumEdit.text.toString()
        val useraddress = binding.addressEdit.text.toString()
        val user = UserModel(auth.uid.toString(), username,auth.currentUser!!.email.toString(),userphone,useraddress,imgUri)

        database.reference.child("users")
            .child(auth.uid.toString())
            .setValue(user)
            .addOnSuccessListener {
                val intent = Intent(this,login_form::class.java)
                startActivity(intent)
                finish()
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(data != null){

            if(data.data!=null){
                selectedImg = data.data!!

                binding.userImageView.setImageURI(selectedImg)
            }

        }
    }
}