package com.example.lab4

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import com.example.lab4.data.Informacion
import com.google.android.material.snackbar.Snackbar

class DetailsActivity : AppCompatActivity() {

    private lateinit var textView_nombre : TextView
    private lateinit var textView_direccion : TextView
    private lateinit var textView_horario : TextView
    private lateinit var buttonCall : Button
    private lateinit var buttonInicia_Visita_Camara : Button
    private lateinit var rootLayout: ConstraintLayout

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        rootLayout = findViewById(R.id.root_permissionActivity)
        textView_nombre = findViewById(R.id.textView_res_name_details)
        textView_direccion = findViewById(R.id.textView_res_direccion_details)
        textView_horario = findViewById(R.id.textView_res_horario_details)
        buttonCall = findViewById(R.id.button_llamar)
        buttonInicia_Visita_Camara = findViewById(R.id.button_iniciarVisita)

        setListeners()

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)


        if (requestCode == 0 && grantResults.isNotEmpty()) {
            for (i in grantResults.indices) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("TAG", "${permissions[i]} was granted")
                }
            }
        }
    }

    private fun setListeners() {
        val informacion: Informacion = intent.getSerializableExtra("informacion_res") as Informacion
        textView_nombre.text = informacion.nombre
        textView_direccion.text = informacion.direccion
        textView_horario.text = informacion.horario

        buttonCall.setOnClickListener {
            val phoneNum = "+50225057000"
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNum"))
            startActivity(intent)
        }

        buttonInicia_Visita_Camara.setOnClickListener {
            checkCameraPermission()
        }
    }


    private fun hasCameraPermission() =
        ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED

    private fun checkCameraPermission() {
        if (!hasCameraPermission()) {
            checkRequestRationale()
        } else {
            Toast.makeText(this, "Permiso otorgado, abrir la camara", Toast.LENGTH_LONG).show()
        }
    }

    private fun checkRequestRationale() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {


                val snackbar = Snackbar.make(
                    rootLayout,
                    "El acceso a cámara es necesario para poder tomar fotos, así como parte del lab :)",
                    Snackbar.LENGTH_INDEFINITE
                )

                snackbar.setAction("Ok"){

                    requestCameraPermission()
                }

                snackbar.show()
            } else {
                requestCameraPermission()
            }
        }
    }

    private fun requestCameraPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.CAMERA), // La lista con 1 o mas permisos a solicitar
            0
        )
    }
}