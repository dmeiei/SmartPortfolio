package kr.ac.hallym.smart_portfolio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kr.ac.hallym.smart_portfolio.databinding.ActivityMainPortfolioBinding

class MainPortfolio : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    lateinit var binding :ActivityMainPortfolioBinding
    lateinit var toggle: ActionBarDrawerToggle

    //fragment
    private val fragmenthome by lazy { homeFragment() }
    private val fragmentproject by lazy { ProjectFragment() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main_portfolio)

        binding = ActivityMainPortfolioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //custom toolbar 사용
        setSupportActionBar(binding.mainToolbar)
        //툴바 버튼생성
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //툴바 기본 title 사용 x
        supportActionBar?.setDisplayShowTitleEnabled(false)

        //하단 네비게이션 바
        initNavigationBar()



        //드로워메뉴
        toggle = ActionBarDrawerToggle(this, binding.drawer, R.string.drawer_opened,
            R.string.drawer_closed)
        toggle.syncState()


        binding.mainDrawerView.setNavigationItemSelectedListener(this)



    }

    //toggle 버튼 클릭
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)

    }

    //navigation menu 클릭
    override fun onNavigationItemSelected(item: MenuItem):Boolean{
        when(item.itemId){
            R.id.profile -> {
                val intent = Intent(this,UserSetting::class.java)
                startActivity(intent)
            }

            R.id.navProject ->{
                val intent = Intent(this,ProjectActivity::class.java)
                startActivity(intent)
            }
            R.id.github ->{
                val intent = Intent(this,GithubActivity::class.java)
                startActivity(intent)
            }

            R.id.logout ->{
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this,login_form::class.java))
                finish()
            }
        }
        return false
    }

    //뒤로가기 버튼 클릭시 앱 종료 방지
    override fun onBackPressed() {
        if(binding.drawer.isDrawerOpen(GravityCompat.START)){
            binding.drawer.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }
    }

    //bottom navigation 바
    private fun initNavigationBar(){
        binding.bnvMain.run{
            setOnNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.first ->{
                        changeFragment(fragmenthome)
                    }
                    R.id.second ->{
                        changeFragment(fragmentproject)
                    }
                    R.id.third ->{
                        changeFragment(UserFragment())
                    }
                }
                true
            }
            selectedItemId = R.id.first
        }
    }

    //fragment 변경
    private fun changeFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fl_container,fragment)
            .commit()
    }

}