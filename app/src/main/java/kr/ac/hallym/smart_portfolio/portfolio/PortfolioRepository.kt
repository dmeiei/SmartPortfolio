package kr.ac.hallym.smart_portfolio.portfolio

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*

class PortfolioRepository {

    private val databaseReference:DatabaseReference = FirebaseDatabase.getInstance().getReference("portfolio")
    private lateinit var portfolioArrayList: ArrayList<PortfolioModel>

    @Volatile private var INSTANCE: PortfolioRepository?= null

    fun getInstance() : PortfolioRepository {

        return INSTANCE ?: synchronized(this){
            val instance = PortfolioRepository()
            INSTANCE = instance
            instance
        }
    }


    fun loadData(portfolioList: MutableLiveData<List<PortfolioModel>>){

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val portfolio_List : List<PortfolioModel> = snapshot.children.map { dataSnapshot ->
                        dataSnapshot.getValue(PortfolioModel::class.java)!!
                    }

                    portfolioList.postValue(portfolio_List)
                }catch (e:Exception){

                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}