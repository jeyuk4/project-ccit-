import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.aplikasipertama.R

class Settings1Activity : AppCompatActivity() {

    private lateinit var etWeight: EditText
    private lateinit var etWaterTarget: EditText
    private lateinit var switchDarkMode: Switch
    private lateinit var switchReminder: Switch
    private lateinit var spinnerUnit: Spinner
    private lateinit var btnSaveSettings: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings1) // Pastikan ini sesuai dengan nama XML

        // Inisialisasi UI
        etWeight = findViewById(R.id.etWeight)
        etWaterTarget = findViewById(R.id.etWaterTarget)
        switchDarkMode = findViewById(R.id.switchDarkMode)
        switchReminder = findViewById(R.id.switchReminder)
        spinnerUnit = findViewById(R.id.spinnerUnit)
        btnSaveSettings = findViewById(R.id.btnSaveSettings)

        // SharedPreferences untuk menyimpan pengaturan
        sharedPreferences = getSharedPreferences("UserSettings", MODE_PRIVATE)

        // Mengisi Spinner dengan array dari resources
        val units = resources.getStringArray(R.array.water_units)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, units)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerUnit.adapter = adapter

        // Load pengaturan yang sudah tersimpan
        loadSettings()

        // Simpan data saat tombol ditekan
        btnSaveSettings.setOnClickListener {
            saveSettings()
        }
    }

    private fun loadSettings() {
        val weight = sharedPreferences.getFloat("weight", 60.0f)
        val waterTarget = sharedPreferences.getFloat("waterTarget", 2000.0f)
        val isDarkMode = sharedPreferences.getBoolean("darkMode", false)
        val isReminderEnabled = sharedPreferences.getBoolean("reminder", true)
        val unit = sharedPreferences.getString("waterUnit", "ml") ?: "ml"

        etWeight.setText(weight.toString())
        etWaterTarget.setText(waterTarget.toString())
        switchDarkMode.isChecked = isDarkMode
        switchReminder.isChecked = isReminderEnabled

        // Atur Spinner ke posisi yang sesuai
        val unitIndex = resources.getStringArray(R.array.water_units).indexOf(unit)
        if (unitIndex >= 0) {
            spinnerUnit.setSelection(unitIndex)
        }
    }

    private fun saveSettings() {
        val weight = etWeight.text.toString().toFloatOrNull() ?: 60.0f
        val waterTarget = etWaterTarget.text.toString().toFloatOrNull() ?: 2000.0f
        val isDarkMode = switchDarkMode.isChecked
        val isReminderEnabled = switchReminder.isChecked
        val selectedUnit = spinnerUnit.selectedItem.toString()

        val editor = sharedPreferences.edit()
        editor.putFloat("weight", weight)
        editor.putFloat("waterTarget", waterTarget)
        editor.putBoolean("darkMode", isDarkMode)
        editor.putBoolean("reminder", isReminderEnabled)
        editor.putString("waterUnit", selectedUnit)
        editor.apply()

        Toast.makeText(this, "Settings Saved", Toast.LENGTH_SHORT).show()
    }
}
