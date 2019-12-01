package ni.devotion.mvvm.fragments
import android.content.Intent
import android.graphics.*
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import coil.api.load
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.business_content.*
import kotlinx.android.synthetic.main.fragment_business.*
import ni.abril.azb.megaboicotapp.ui.adapters.BusinessAdapter
import ni.devotion.mvvm.BuildConfig
import ni.devotion.mvvm.R
import ni.devotion.mvvm.adapters.CategoriesAdapter
import ni.devotion.mvvm.data.network.`interface`.IRecyclerClic
import ni.devotion.mvvm.data.network.`interface`.OnBackPressed
import ni.devotion.mvvm.model.BusinessList
import ni.devotion.mvvm.viewModel.BusinessViewModel
import ni.devotion.mvvm.viewModel.CategoriesViewModel
import ni.devotion.mvvm.view_holder.BusinessViewHolder
import org.koin.androidx.viewmodel.ext.android.viewModel

class BusinessFragment : Fragment(){
    private val businessViewModel: BusinessViewModel by viewModel()
    private val adapter: BusinessAdapter by lazy{
        BusinessAdapter(object: IRecyclerClic{
            override fun onClick(view: View, position: Int, businessList: BusinessList) {
                Toast.makeText(context,position.toString(), Toast.LENGTH_SHORT).show()
                super.onClick(view, position, businessList)
                println("PASA POR AQUI")
                println("Nombre del negocio: ${businessList.nombre}")
                val businessDetails = BusinessDetails()
                val bundle = Bundle()
                bundle.putString("nombre", businessList.nombre)
                bundle.putString("imagen", businessList.imagenes.get(0))
                bundle.putString("descripcion", businessList.descripcion)
                bundle.putString("direccion", businessList.direccion)
                bundle.putString("celular1", businessList.telefonos.get(0))
                bundle.putString("celular2", businessList.telefonos.get(1))
                businessDetails.arguments = bundle

                val fragmentManager: FragmentManager? = fragmentManager
                val fragmentTransaction: FragmentTransaction = fragmentManager!!.beginTransaction()
                fragmentTransaction.replace(R.id.container, businessDetails).addToBackStack("tag")
                fragmentTransaction.commit()
            }
        }) }

    private val categoriesViewModel: CategoriesViewModel by viewModel()
    private val catadapter: CategoriesAdapter by lazy { CategoriesAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("ESTOY EN EL ONCREATE")
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

        //val bitmap = BitmapFactory.decodeResource(resources, R.drawable.imagebusiness)

        //bussiness_image.setImageBitmap(getRoundedCornerBitmap(bitmap, 20))


        img_anuncioclaro.load(BuildConfig.API_URL+"public/uploads/publicidad1.png"){
            placeholder(R.mipmap.ic_launcher)
            println(BuildConfig.API_URL+"public/uploads/publicidad1.png")
            println("IMPRIME PERO NO LLEGA")
        }


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

    fun getRoundedCornerBitmap(bitmap: Bitmap, pixel:Int): Bitmap {
        var output = Bitmap.createBitmap(bitmap.width,bitmap.height, Bitmap.Config.ARGB_8888)

        val canvas = Canvas(output)
        val color = 0xff424242
        val paint = Paint()
        val rect = Rect(0,0,bitmap.width, bitmap.height)
        val rectf = RectF(rect)
        val roundPx: Int = pixel

        paint.isAntiAlias = true
        canvas.drawARGB(0,0,0,0)
        paint.setColor(color)
        canvas.drawBitmap(bitmap, rect, rect, paint)

        return output
    }



    companion object {
        fun newInstance(): BusinessFragment = BusinessFragment()
    }



}

