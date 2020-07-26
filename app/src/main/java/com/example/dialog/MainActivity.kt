package com.example.dialog

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.lang.System.exit
import java.util.*


class MainActivity : AppCompatActivity() {

    private var firstName: EditText? = null
    private var lastName: EditText? = null
    private var dataBirth: EditText? = null
    private var btnSave: Button? = null

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
        setupListeners()
        CreateDataPickerDialog()
    }

    private fun setupViews() {
        firstName = findViewById(R.id.firstName)
        lastName = findViewById(R.id.lastName)
        dataBirth = findViewById(R.id.dataBirth)
        btnSave = findViewById(R.id.btnSave)
    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun setupListeners() {
        dataBirth?.setOnClickListener {
            CreateDataPickerDialog()
        }

        btnSave?.setOnClickListener {
            createAlertDialog()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun CreateDataPickerDialog() {
        val dialog = DatePickerDialog(this)
        dialog.setOnDateSetListener { view, year, month, dayOfMonth ->
            dataBirth?.setText(getString(R.string.date, dayOfMonth, month, year))
        }
        dialog.datePicker.maxDate = Date().time
        dialog.show()
    }

    private fun createAlertDialog() {
        val preference = getSharedPreferences("prefecence", Context.MODE_PRIVATE)
        AlertDialog.Builder(ContextThemeWrapper(this, R.style.myDialog))
            .setTitle("Сохранить данные")
            .setPositiveButton("ДА") { dialog, which ->
                val login = firstName?.text.toString() 
                val password = lastName?.text.toString()
                preference.edit().putString("text", login).apply()
                preference.edit().putString("text1", password).apply()
                Toast.makeText(this, "Ваши данные сохранились", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Нет") { dialog, which ->

            }
            .setIcon(R.drawable.save)
            .setCancelable(false)
            .show()
    }
}

