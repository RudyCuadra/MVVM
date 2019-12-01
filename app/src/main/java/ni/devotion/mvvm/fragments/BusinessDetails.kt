package ni.devotion.mvvm.fragments

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.*
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import coil.api.load
import kotlinx.android.synthetic.main.fragment_business_details.*
import ni.devotion.mvvm.BuildConfig

import ni.devotion.mvvm.R
import java.util.jar.Manifest

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class BusinessDetails : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var nombre = ""
    private var descipcion = ""
    private var direccion = ""
    private var cel1 = ""
    private var cel2 = ""
    private var imagen = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_business_details, container, false)
        return v
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
            super.onActivityCreated(savedInstanceState)

        if(arguments!!.getString("nombre")!= null){
            nombre = arguments!!.getString("nombre").toString()
            descipcion = arguments!!.getString("descripcion").toString()
            direccion = arguments!!.getString("direccion").toString()
            cel1 = arguments!!.getString("celular1").toString()
            cel2 = arguments!!.getString("celular2").toString()
            imagen = arguments!!.getString("imagen").toString()
            println("NOMBRE::   $nombre")
            txtNombre.text = nombre
            txtDescripcion.text = descipcion
            txtDireccion.text = direccion
            txtCel1.text = cel1
            txtCel2.text = cel2
            viewImageBusiness.load(BuildConfig.API_URL+imagen){
                print("AQUIIIII::   ${BuildConfig.API_URL+imagen}")
                placeholder(R.mipmap.ic_launcher)
            }
        }

        btnLlamar.setOnClickListener {
            val i = Intent()
            i.action = Intent.ACTION_DIAL
            if(cel1.isNotEmpty()){
                i.data = Uri.parse("tel:"+cel1)
                startActivity(i)
                Toast.makeText(context,"Llamando a:- $cel1", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(context,"Invalid Phone Number!" , Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getRoundedCornerBitmap(bitmap: Bitmap,pixel:Int): Bitmap {
        var output = Bitmap.createBitmap(bitmap.width,bitmap.height,Bitmap.Config.ARGB_8888)

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

}
