package ni.devotion.mvvm

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.*
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import ni.abril.azb.megaboicotapp.ui.adapters.BusinessAdapter
import ni.devotion.mvvm.adapters.CategoriesAdapter
import ni.devotion.mvvm.fragments.BusinessFragment
import ni.devotion.mvvm.fragments.EntertainmentFragment
import ni.devotion.mvvm.fragments.MapFragment
import ni.devotion.mvvm.fragments.OffertsFragment
import ni.devotion.mvvm.viewModel.BusinessViewModel
import ni.devotion.mvvm.viewModel.CategoriesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    /*private val businessViewModel: BusinessViewModel by viewModel()
    private val adapter: BusinessAdapter by lazy { BusinessAdapter() }

    private val categoriesViewModel: CategoriesViewModel by viewModel()
    private val catadapter: CategoriesAdapter by lazy { CategoriesAdapter() }*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("ANTES DE ENTRAR EL RECYCLER VIEW")

        val businessFragment = BusinessFragment.newInstance()
        openFragment(businessFragment)
        val bottomNavigation: BottomNavigationView = findViewById(R.id.navegationView)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when(item.itemId){
            R.id.navigation_busines -> {
                 val businessFragment = BusinessFragment.newInstance()
                openFragment(businessFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_offer -> {
                val offertsFragment = OffertsFragment.newInstance()
                openFragment(offertsFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_entertainment -> {
                val entertainmentFragment = EntertainmentFragment.newInstance()
                openFragment(entertainmentFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_localitation -> {
                val mapFragment = MapFragment.newInstance()
                openFragment(mapFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    private fun openFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        //transaction.addToBackStack(null)
        transaction.commit()
    }
}
