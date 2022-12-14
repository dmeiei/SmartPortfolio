package kr.ac.hallym.smart_portfolio

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView

class ProjectFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_project, container, false)

        val view = inflater.inflate(R.layout.fragment_project,container,false)

        val project1 = view.findViewById<CardView>(R.id.projectOne)
        val project2 = view.findViewById<CardView>(R.id.projectTwo)

        //첫번째 프로젝트 클릭
        project1.setOnClickListener {
            val intent = Intent(context,ProjectOne::class.java)
            startActivity(intent)

        }

        //두번째 프로젝트 클릭
        project2.setOnClickListener {
            val intent = Intent(context, ProjectTwo::class.java)
            startActivity(intent)
        }


        return view
    }

}