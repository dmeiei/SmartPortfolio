package kr.ac.hallym.smart_portfolio.study


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.ac.hallym.smart_portfolio.R
import kr.ac.hallym.smart_portfolio.study.StudyAdapter
import kr.ac.hallym.smart_portfolio.study.StudyAddActivity

private lateinit var viewModel: StudyViewModel
private lateinit var studyRecyclerView: RecyclerView
lateinit var adapter: StudyAdapter

class StudyFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_study, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = view.findViewById<Button>(R.id.studyFab)
        button.setOnClickListener {
            val intent = Intent(context, StudyAddActivity::class.java)
            startActivity(intent)
        }

        studyRecyclerView = view.findViewById(R.id.studyRecyclerView)
        studyRecyclerView.layoutManager = LinearLayoutManager(context)
        studyRecyclerView.setHasFixedSize(true)
        adapter = StudyAdapter()
        studyRecyclerView.adapter = adapter

        viewModel = ViewModelProvider(this).get(StudyViewModel::class.java)

        viewModel.allStudy.observe(viewLifecycleOwner,Observer{
            adapter.updateStudyList(it)
        })

        //detail
        adapter.onItemClick = {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("currentitem", it)
            startActivity(intent)
        }


    }
}

