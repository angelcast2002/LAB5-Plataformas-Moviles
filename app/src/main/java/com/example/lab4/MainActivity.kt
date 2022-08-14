package com.example.lab4

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.example.lab4.data.Informacion



class MainActivity : AppCompatActivity() {

    private lateinit var btnToastMessege : MaterialButton
    private lateinit var buttonNavigate : MaterialButton
    private lateinit var buttonDescargar : MaterialButton
    private lateinit var buttonDetalles : MaterialButton
    private lateinit var textNombre : TextView
    private lateinit var textDireccion : TextView
    private lateinit var textHorario : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnToastMessege = findViewById(R.id.button_iniciar)
        initListeners()
        buttonNavigate = findViewById(R.id.bt_directions)
        buttonDescargar = findViewById(R.id.button_descargar)
        buttonDetalles = findViewById(R.id.button_detalles)
        textNombre = findViewById(R.id.textView_res_name)
        textDireccion = findViewById(R.id.textView_res_direccion)
        textHorario = findViewById(R.id.textView_res_horario)

        setListeners()
    }

    private fun initListeners(){
        btnToastMessege.setOnClickListener{
            Toast.makeText(this, "Angel Sebastian Castellanos Pineda", Toast.LENGTH_LONG).show()
        }
    }

    private fun setListeners(){
        buttonNavigate.setOnClickListener {
            // Explicito. Estamos pidiendo que use google maps
            val location = "https://goo.gl/maps/ppt8VHu7LHCKvFV76"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(location))

            startActivity(intent)
        }
        buttonDescargar.setOnClickListener {
            var intent = Intent()
            try {
                intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.kiloo.subwaysurf"))
            } catch (e: ActivityNotFoundException) {
                intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.kiloo.subwaysurf"))
            }

            startActivity(intent)
        }

        buttonDetalles.setOnClickListener {
            val nombre = textNombre.text.toString()
            val direccion = textDireccion.text.toString()
            val horario = textHorario.text.toString()

            val informacion = Informacion(nombre, direccion, horario)

            val intent = Intent(this, DetailsActivity::class.java)

            // O pueden mandar un objeto que extienda de la clase Serializable
            intent.putExtra("informacion_res", informacion)
            startActivity(intent)
        }


    }

}