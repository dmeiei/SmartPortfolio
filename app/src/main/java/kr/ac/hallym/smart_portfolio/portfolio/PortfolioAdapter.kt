package kr.ac.hallym.smart_portfolio.portfolio

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kr.ac.hallym.smart_portfolio.R
import kr.ac.hallym.smart_portfolio.study.InfoModel

class PortfolioAdapter : RecyclerView.Adapter<PortfolioAdapter.MyViewHolder>() {

    private val portfolioList = ArrayList<PortfolioModel>()

    var onItemClick : ((PortfolioModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.portfolio_item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val current = portfolioList[position]

        holder.title.text = current.title
        holder.content.text = current.contents

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(current)
        }
    }

    override fun getItemCount(): Int {
        return portfolioList.size
    }

    fun updatePortfolioList(portfolioList: List<PortfolioModel>){
        this.portfolioList.clear()
        this.portfolioList.addAll(portfolioList)
        notifyDataSetChanged()

    }


    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val title : TextView = itemView.findViewById(R.id.portfolio_title)
        val content :TextView = itemView.findViewById(R.id.portfolio_contents)
    }
}