package kr.ac.hallym.smart_portfolio.portfolio

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.ac.hallym.smart_portfolio.R
import kr.ac.hallym.smart_portfolio.portfolio.PortfolioAdapter
import kr.ac.hallym.smart_portfolio.portfolio.PortfolioAddActivity
import kr.ac.hallym.smart_portfolio.portfolio.PortfolioViewModel

private lateinit var viewModel: PortfolioViewModel
private lateinit var portfolioRecyclerView: RecyclerView
lateinit var adapter1: PortfolioAdapter

class homeFragment : Fragment(){


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = view.findViewById<Button>(R.id.portfolioFab)
        button.setOnClickListener {
            val intent = Intent(context, PortfolioAddActivity::class.java)
            startActivity(intent)
        }

        val manager = LinearLayoutManager(context)
        portfolioRecyclerView = view.findViewById(R.id.homeRecyclerview)
        portfolioRecyclerView.layoutManager = manager
        portfolioRecyclerView.setHasFixedSize(true)
        adapter1 = PortfolioAdapter()
        portfolioRecyclerView.adapter = adapter1

        manager.reverseLayout = true
        manager.stackFromEnd = true


        viewModel = ViewModelProvider(this).get(PortfolioViewModel::class.java)

        viewModel.allPortfolio.observe(viewLifecycleOwner, Observer{
            adapter1.updatePortfolioList(it)
        })

//        val editBtn = view.findViewById<ImageView>(R.id.portfolio_edit)
//        editBtn.setOnClickListener {
//            val intent = Intent(context,updateProfile::class.java)
//            startActivity(intent)
//        }
        //update
        adapter1.onItemClick = {
            val intent = Intent(context,updateProfile::class.java)
            intent.putExtra("current",it)
            startActivity(intent)
        }

    }


}