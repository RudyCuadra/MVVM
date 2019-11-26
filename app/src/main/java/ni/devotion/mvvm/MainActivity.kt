package ni.devotion.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import ni.abril.azb.megaboicotapp.ui.adapters.BusinessAdapter
import ni.devotion.mvvm.viewModel.BusinessViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val businessViewModel: BusinessViewModel by viewModel()
    private val adapter: BusinessAdapter by lazy { BusinessAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("ANTES DE ENTRAR EL RECYCLER VIEW")
        rvDepartments.apply {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = this@MainActivity.adapter
            println("DENTRO DEL RECYCLER")
        }
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
