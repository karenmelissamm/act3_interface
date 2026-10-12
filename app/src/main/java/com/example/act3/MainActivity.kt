package com.example.act3

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.slider.Slider

class MainActivity : AppCompatActivity() {
    var home_selected: Boolean? = null

    var height = 73
    var weight = 170
    var age = 18

    lateinit var card_home: MaterialCardView
    lateinit var card_dona: MaterialCardView
    lateinit var slider: Slider
    lateinit var tvHeight: TextView
    lateinit var tvWeight: TextView
    lateinit var tvAge: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        card_home = findViewById(R.id.seleccio_home)
        card_dona = findViewById(R.id.seleccio_dona)
        slider = findViewById(R.id.slider_height)
        tvHeight = findViewById(R.id.tv_height)
        tvWeight = findViewById(R.id.tv_weight)
        tvAge = findViewById(R.id.tv_age)

        card_home.setOnClickListener {
            home_selected = true
            updateGenderColors()
        }
        card_dona.setOnClickListener {
            home_selected = false
            updateGenderColors()
        }
        slider.addOnChangeListener { _, value, _ ->
            height = value.toInt()
            tvHeight.text = height.toString()
        }
        findViewById<MaterialButton>(R.id.btn_weight_plus).setOnClickListener {
            weight++
            tvWeight.text = weight.toString()
        }
        findViewById<MaterialButton>(R.id.btn_weight_minus).setOnClickListener {
            if (weight > 1) weight--
            tvWeight.text = weight.toString()
        }

        findViewById<MaterialButton>(R.id.btn_age_plus).setOnClickListener {
            age++
            tvAge.text = age.toString()
        }
        findViewById<MaterialButton>(R.id.btn_age_minus).setOnClickListener {
            if (age > 1) age--
            tvAge.text = age.toString()
        }
        findViewById<MaterialButton>(R.id.btn_calculate).setOnClickListener {
            val bmi = 703.0 * weight / (height * height)
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("bmi", bmi)
            startActivity(intent)
        }
    }

    private fun updateGenderColors() {
        val selected = ContextCompat.getColor(this, R.color.card_selected)
        val normal = ContextCompat.getColor(this, R.color.background_card)

        card_home.setCardBackgroundColor(if (home_selected == true) selected else normal)
        card_dona.setCardBackgroundColor(if (home_selected == false) selected else normal)
    }
}