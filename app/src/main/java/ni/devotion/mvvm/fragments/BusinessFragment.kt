package ni.devotion.mvvm.fragments
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_business.*
import ni.abril.azb.megaboicotapp.ui.adapters.BusinessAdapter
import ni.devotion.mvvm.R
import ni.devotion.mvvm.adapters.CategoriesAdapter
import ni.devotion.mvvm.viewModel.BusinessViewModel
import ni.devotion.mvvm.viewModel.CategoriesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class BusinessFragment : Fragment() {
    private val businessViewModel: BusinessViewModel by viewModel()
    private val adapter: BusinessAdapter by lazy { BusinessAdapter() }

    private val categoriesViewModel: CategoriesViewModel by viewModel()
    private val catadapter: CategoriesAdapter by lazy { CategoriesAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_business, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        rvDepartments.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            setHasFixedSize(true)
            adapter = this@BusinessFragment.adapter
            println("DENTRO DEL RECYCLER")
        }

        rvCategorias.apply {
            layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL,false)
            setHasFixedSize(true)
            adapter = this@BusinessFragment.catadapter
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
                    Toast.makeText(context, resources.getString(error), Toast.LENGTH_LONG).show()
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
                    Toast.makeText(context, resources.getString(error), Toast.LENGTH_LONG).show()
                }
            }
        })
    }


    override fun onDetach() {
        super.onDetach()
    }


    companion object {
        fun newInstance(): BusinessFragment = BusinessFragment()
    }
}
