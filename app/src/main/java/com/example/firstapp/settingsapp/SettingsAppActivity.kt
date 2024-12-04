package com.example.firstapp.settingsapp

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.appcompat.app.AppCompatDialog
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.firstapp.R
import com.example.firstapp.databinding.ActivitySettingsAppBinding
import com.example.firstapp.databinding.ActivitySuperHeroListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")


class SettingsAppActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsAppBinding
    private var firsTime:Boolean= true

    companion object {
        const val VOLUME_LVL: String = "volume_lvl"
        const val KEY_BLUETOOTH: String = "key_bluetooth"
        const val KEY_DARK: String = "key_dark"
        const val KEY_VIBRE: String = "key_vibre"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySettingsAppBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        CoroutineScope(Dispatchers.IO).launch {
            getSettings().filter {firsTime}.collect{ settingModel ->
                if (settingModel != null) {
                    runOnUiThread{
                        binding.swVibrate.isChecked = settingModel.vibration
                        binding.swBluetooth.isChecked = settingModel.bluetooth
                        binding.swDarkMode.isChecked = settingModel.darkMode
                        binding.slideVolume.setValues(settingModel.volume.toFloat())
                        firsTime = !firsTime
                    }

                }

            }
        }


        initUI()

    }

    private fun initUI() {
        binding.slideVolume.addOnChangeListener { _, value, _ ->
            CoroutineScope(Dispatchers.IO).launch {
                saveVolume(value.toInt())
            }
        }
        binding.swBluetooth.setOnCheckedChangeListener { _, value ->
            CoroutineScope(Dispatchers.IO).launch {

                saveChecks(KEY_BLUETOOTH, value)
            }
        }

        binding.swVibrate.setOnCheckedChangeListener { _, value ->
            CoroutineScope(Dispatchers.IO).launch {
                saveChecks(KEY_VIBRE, value)
            }
        }
        binding.swDarkMode.setOnCheckedChangeListener { _, value ->
            if (value){
                enableDarkMode()
            }else{
                disableDarkMode()
            }
            CoroutineScope(Dispatchers.IO).launch {
                saveChecks(KEY_DARK, value)
            }
        }

    }

    private suspend fun saveVolume(value: Int) {

        dataStore.edit { preferences ->
            preferences[intPreferencesKey(VOLUME_LVL)] = value
        }
    }

    private suspend fun saveChecks(key: String, option: Boolean) {
        dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(key)] = option
        }
    }

    private fun getSettings(): Flow<SettingsModel?> {
        return dataStore.data.map { values ->
            SettingsModel(
                volume = values[intPreferencesKey(VOLUME_LVL)] ?: 50,
                bluetooth = values[booleanPreferencesKey(KEY_BLUETOOTH)] ?: true,
                darkMode = values[booleanPreferencesKey(KEY_DARK)] ?: true,
                vibration = values[booleanPreferencesKey(KEY_VIBRE)] ?: true

            )
        }
    }
    private fun enableDarkMode(){
       AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
        delegate.applyDayNight()
    }
    private fun disableDarkMode(){
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
        delegate.applyDayNight()

    }
}