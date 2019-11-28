package ni.devotion.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.*
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import ni.abril.azb.megaboicotapp.ui.adapters.BusinessAdapter
import ni.devotion.mvvm.adapters.CategoriesAdapter
import ni.devotion.mvvm.viewModel.BusinessViewModel
import ni.devotion.mvvm.viewModel.CategoriesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val businessViewModel: BusinessViewModel by viewModel()
    private val adapter: BusinessAdapter by lazy { BusinessAdapter() }

    private val categoriesViewModel: CategoriesViewModel by viewModel()
    private val catadapter: CategoriesAdapter by lazy { CategoriesAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("ANTES DE ENTRAR EL RECYCLER VIEW")


        rvDepartments.apply {
            layoutManager = LinearLayoutManager(context, VERTICAL,false)
            setHasFixedSize(true)
            adapter = this@MainActivity.adapter
            println("DENTRO DEL RECYCLER")
        }

        rvCategorias.apply {
            layoutManager = LinearLayoutManager(this@MainActivity,HORIZONTAL,false)
            setHasFixedSize(true)
            adapter = this@MainActivity.catadapter
        }

        categoriesViewModel.uiState.observe(this, Observer {
            val dataStateC = it ?: return@Observer
            if (!dataStateC.showProgress){ }
            if (dataStateC.categories != null && !dataStateC.categories.consumed){
                dataStateC.categories.consume()?.let { categories ->
                    catadapter.submitList(categories)
                }
            }
            if (dataStateC.error != null && !dataStateC.error.consumed){
                dataStateC.error.consume()?.let { error ->
                    Toast.makeText(applicationContext, resources.getString(error), Toast.LENGTH_LONG).show()
                }
            }
        })

        println("ANTES DE ENTRAR EL RECYCLER VIEW")
        businessViewModel.uiState.observe(this, Observer {
            val dataState = it ?: return@Observer
            if (!dataState.showProgress){ }
            if (dataState.business != null && !dataState.business.consumed){
                dataState.business.consume()?.let { business ->
                        adapter.submitList(business)
                }
            }
            if (dataState.error != null && !dataState.error.consumed){
                dataState.error.consume()?.let { error ->
                    Toast.makeText(applicationContext, resources.getString(error), Toast.LENGTH_LONG).show()
                }
            }
        })



    }
}
