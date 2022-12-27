package kr.ac.hallym.smart_portfolio.study

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StudyViewModel : ViewModel() {

    private val repository: StudyRepository
    private val all_Study = MutableLiveData<List<InfoModel>>()
    val allStudy : LiveData<List<InfoModel>> = all_Study


    init {
        repository = StudyRepository().getInstance()
        repository.loadStudy(all_Study)
    }
}