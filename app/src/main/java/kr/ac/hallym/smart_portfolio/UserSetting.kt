package kr.ac.hallym.smart_portfolio

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.FileProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kr.ac.hallym.smart_portfolio.databinding.ActivityUserSettingBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class UserSetting : AppCompatActivity() {
    private lateinit var binding: ActivityUserSettingBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var user : UserModel
    private lateinit var uid: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_user_setting)
        binding = ActivityUserSettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //custom toolbar 사용
        setSupportActionBar(binding.mainToolbar)
        //툴바 기본 title 사용 x
        supportActionBar?.setDisplayShowTitleEnabled(false)
        //뒤로가기 버튼
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        auth = FirebaseAuth.getInstance()
        uid = auth.currentUser?.uid.toString()

        //유저 데이터 가져오기
        databaseReference = FirebaseDatabase.getInstance().getReference("users")
        if (uid.isNotEmpty()){
            getUserData()
        }

        //ProgressDialog
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("사용자 정보 로딩중..")
        progressDialog.setCancelable(false)
        progressDialog.show()

        //이미지 불러오기
        val storageRef = FirebaseStorage.getInstance().reference.child("users/$uid.jpg")

        //이미지 파일로다운
        val localfile = File.createTempFile("tempImage","jpg")
        storageRef.getFile(localfile).addOnSuccessListener {
            //progressDialog 종료
            if (progressDialog.isShowing)
                progressDialog.dismiss()

            //bitmap 변환
            val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
            binding.userImage.setImageBitmap(bitmap)
        }.addOnFailureListener{

            //progressDialog 종료
            if (progressDialog.isShowing)
                progressDialog.dismiss()

            Toast.makeText(this,"이미지를 불러오는데 실패했습니다.", Toast.LENGTH_SHORT).show()
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


    //유저 데이터 가져오기
    private fun getUserData(){
        databaseReference.child(uid).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                user = snapshot.getValue(UserModel::class.java)!!
                binding.nameEdit1.setText(user.name)
                binding.emailEdit1.setText(user.email)
                binding.phoneNumEdit1.setText(user.phone)
                binding.addressEdit1.setText(user.adress)

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

}
