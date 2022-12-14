package kr.ac.hallym.smart_portfolio

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialog
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import de.hdodenhof.circleimageview.CircleImageView
import kr.ac.hallym.smart_portfolio.databinding.FragmentUserBinding
import java.io.File

class UserFragment : Fragment() {
    companion object{
        lateinit var imageView: CircleImageView
        lateinit var user : UserModel
        lateinit var uid : String
        lateinit var databaseReference: DatabaseReference
        lateinit var auth: FirebaseAuth
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentUserBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment

        //progressDialog
        val progressDialog = ProgressDialog(context)
        progressDialog.setMessage("사용자 정보 로딩중..")
        progressDialog.setCancelable(false)
        progressDialog.show()

        auth = FirebaseAuth.getInstance()
        uid = auth.currentUser?.uid.toString()

        //storage에서 이미지 가져오기
        val storageRef = FirebaseStorage.getInstance().reference.child("users/$uid.jpg")
        val view = inflater.inflate(R.layout.fragment_user,container,false)

        imageView = view.findViewById(R.id.userImage)

        //파일로 다운로드
        val localfile = File.createTempFile("tempImage","jpg")
        storageRef.getFile(localfile).addOnSuccessListener {
            if (progressDialog.isShowing)
                progressDialog.dismiss()

            //bitmap 변환
            val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
            imageView.setImageBitmap(bitmap)
        }.addOnFailureListener{

            if (progressDialog.isShowing)
                progressDialog.dismiss()

            Toast.makeText(context,"이미지를 불러오는데 실패했습니다.",Toast.LENGTH_SHORT).show()
        }

        val uname = view.findViewById<TextView>(R.id.name_edit)
        val uemail = view.findViewById<TextView>(R.id.email_edit)
        val uphone = view.findViewById<TextView>(R.id.phoneNum_edit)
        val uaddress = view.findViewById<TextView>(R.id.address_edit)

        databaseReference = FirebaseDatabase.getInstance().getReference("users")
        if (uid.isNotEmpty()){

            databaseReference.child(uid).addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {

                    user = snapshot.getValue(UserModel::class.java)!!
                    Log.d("kkang","$user")
                    uname.setText(user.name)
                    uemail.setText(user.email)
                    uphone.setText(user.phone)
                    uaddress.setText(user.adress)

                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        }

        return view
    }


}