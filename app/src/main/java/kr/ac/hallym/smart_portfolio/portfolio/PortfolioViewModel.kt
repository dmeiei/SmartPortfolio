package kr.ac.hallym.smart_portfolio.portfolio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.ac.hallym.smart_portfolio.portfolio.PortfolioModel
import kr.ac.hallym.smart_portfolio.portfolio.PortfolioRepository

class PortfolioViewModel : ViewModel() {

    private val repository : PortfolioRepository
    private val all_portfolio = MutableLiveData<List<PortfolioModel>>()
    val allPortfolio : LiveData<List<PortfolioModel>> = all_portfolio

    init {
        repository = PortfolioRepository().getInstance()
        repository.loadData(all_portfolio)
    }

}