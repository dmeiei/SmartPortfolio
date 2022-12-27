package kr.ac.hallym.smart_portfolio.study

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kr.ac.hallym.smart_portfolio.R

class StudyAdapter: RecyclerView.Adapter<StudyAdapter.MyViewHolder>() {

    private val studyList = ArrayList<InfoModel>()

    var onItemClick : ((InfoModel) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.study_item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = studyList[position]

        holder.title1.text = currentitem.title
        holder.contents.text = currentitem.contents

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(currentitem)
        }
    }

    override fun getItemCount(): Int {
        return studyList.size
    }

    fun updateStudyList(studyList : List<InfoModel>){

        this.studyList.clear()
        this.studyList.addAll(studyList)
        notifyDataSetChanged()

    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val title1 : TextView = itemView.findViewById(R.id.study_titleCard)
        val contents : TextView = itemView.findViewById(R.id.study_contentCard)



    }
}

