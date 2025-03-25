package com.example.drinkingreminder

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.aplikasipertama.R
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry

class HistoryActivity : AppCompatActivity() {

    private lateinit var barChart: BarChart
    private lateinit var btnMonth: Button
    private lateinit var btnYear: Button
    private lateinit var btnAddRecord: Button
    private lateinit var tvWeeklyAvg: TextView
    private lateinit var tvMonthlyAvg: TextView
    private lateinit var tvCompletion: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        // Inisialisasi UI
        barChart = findViewById(R.id.barChart)
        btnMonth = findViewById(R.id.btnMonth)
        btnYear = findViewById(R.id.btnYear)
        btnAddRecord = findViewById(R.id.btnAddRecord)
        tvWeeklyAvg = findViewById(R.id.tvWeeklyAvg)
        tvMonthlyAvg = findViewById(R.id.tvMonthlyAvg)
        tvCompletion = findViewById(R.id.tvCompletion)

        // Load data awal (misalnya default ke Bulanan)
        loadChartData("month")

        // Tombol untuk mengubah tampilan grafik
        btnMonth.setOnClickListener {
            loadChartData("month")
        }
        btnYear.setOnClickListener {
            loadChartData("year")
        }

        // Tambahkan aksi untuk menambah data (bisa diarahkan ke activity lain)
        btnAddRecord.setOnClickListener {
            // TODO: Implementasi tambah data konsumsi air
        }
    }

    private fun loadChartData(type: String) {
        val entries = mutableListOf<BarEntry>()

        // Simulasi data konsumsi air dalam ml
        val data = if (type == "month") {
            listOf(1500, 1800, 2000, 2200, 1900, 2100, 2300)
        } else {
            listOf(1800, 1900, 2000, 2100, 1950, 2050, 2200, 2300, 2400, 2500)
        }

        // Masukkan data ke grafik
        for (i in data.indices) {
            entries.add(BarEntry(i.toFloat(), data[i].toFloat()))
        }

        val dataSet = BarDataSet(entries, "Water Consumption")
        dataSet.color = getColor(android.R.color.holo_blue_light)


        val barData = BarData(dataSet)
        barChart.data = barData

        val description = Description()
        description.text = if (type == "month") "Monthly Consumption" else "Yearly Consumption"
        barChart.description = description
        barChart.invalidate()

        // Hitung rata-rata dan update tampilan
        updateStatistics(data)
    }

    private fun updateStatistics(data: List<Int>) {
        val weeklyAvg = data.average()
        val monthlyAvg = weeklyAvg * 4 // Perkiraan 4 minggu dalam 1 bulan
        val completion = (weeklyAvg / 2000) * 100 // Misalnya target harian 2000ml

        tvWeeklyAvg.text = "Weekly average: ${weeklyAvg.toInt()} ml/day"
        tvMonthlyAvg.text = "Monthly average: ${monthlyAvg.toInt()} ml/day"
        tvCompletion.text = "Average completion: ${completion.toInt()}%"
    }
}
