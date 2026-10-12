package com.example.act3
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.result_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val bmi = intent.getDoubleExtra("bmi", 0.0)

        val tvCategory = findViewById<TextView>(R.id.tv_category)
        val tvBmi = findViewById<TextView>(R.id.tv_bmi)
        val tvDescription = findViewById<TextView>(R.id.tv_description)

        tvBmi.text = String.format("%.2f", bmi)

        val (category, description, colorId) = when {
            bmi < 18.5 -> Triple("UNDERWEIGHT", "Below normal weight", R.color.result_warning)
            bmi < 25.0 -> Triple("NORMAL", "Normal", R.color.result_normal)
            bmi < 30.0 -> Triple("OVERWEIGHT", "Above normal weight", R.color.result_warning)
            else -> Triple("OBESE", "Well above normal weight", R.color.result_danger)
        }

        tvCategory.text = category
        tvCategory.setTextColor(ContextCompat.getColor(this, colorId))
        tvDescription.text = description

        findViewById<TextView>(R.id.tv_back).setOnClickListener { finish() }
        findViewById<MaterialButton>(R.id.btn_recalculate).setOnClickListener { finish() }
    }
}