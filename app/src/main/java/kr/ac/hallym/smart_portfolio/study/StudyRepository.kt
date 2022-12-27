package kr.ac.hallym.smart_portfolio.study

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*

class StudyRepository {

    private val databaseReference :DatabaseReference = FirebaseDatabase.getInstance().getReference("study")

    @Volatile private var INSTANCE: StudyRepository?= null

    fun getInstance() : StudyRepository {

        return INSTANCE ?: synchronized(this){
            val instance = StudyRepository()
            INSTANCE = instance
            instance
        }
    }


    fun loadStudy(studyList:MutableLiveData<List<InfoModel>>){

        databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val study_List : List<InfoModel> = snapshot.children.map { dataSnapshot ->
                        dataSnapshot.getValue(InfoModel::class.java)!!
                    }

                    studyList.postValue(study_List)
                }catch (e:Exception){

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}