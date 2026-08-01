package com.example.reto_colaborativo

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.reto_colaborativo.databinding.ActivityMainBinding
import com.example.reto_colaborativo.modelos.LoginRequest
import com.example.reto_colaborativo.red.RetrofitClient
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private var token: String? = null   // aquí guardaremos la "manilla"
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    companion object {
        const val DATOS = "MisDatos"
        const val KEY_ACCESS_TOKEN = "username"
        const val KEY_REFRESH_TOKEN = "password"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences(com.example.reto_colaborativo.MainActivity.Companion.DATOS, Context.MODE_PRIVATE)

        binding.btnLogin.setOnClickListener {
        val username = binding.edtName.text.toString().trim()
        val password = binding.edtPassword.text.toString().trim()
            if(username.isEmpty()){
                binding.edtName.error = "..."
            }
            if (password.isEmpty()){
                binding.edtPassword.error = "..."
            }
            if (username.isEmpty() || password.isEmpty()){
                return@setOnClickListener
            }
                hacerLogin(username, password)
        }
    }

    // ---------- PASO A: POST de login ----------
    private fun hacerLogin(usuario: String, clave: String) {
        // lifecycleScope.launch = ejecuta en una corrutina (sin congelar la app)
        lifecycleScope.launch {
            try {
                val resp = RetrofitClient.api.login(
                    LoginRequest(usuario, clave)
                )
                if (resp.isSuccessful) {
                    token = resp.body()?.accessToken   // ← guardamos el token
                    Log.d("API", "Token recibido: $token")
                    obtenerUsuario()                  // seguimos al GET
                } else {
                    Log.e("API", "Login falló: ${resp.code()}")
                }
            } catch (e: Exception) {
                Log.e("API", "Error de red: ${e.message}")
            }
        }
    }

    // ---------- PASO B: GET protegido con el token ----------
    private fun obtenerUsuario() {
        val t = token ?: return              // si no hay token, no seguimos
        lifecycleScope.launch {
            try {
                // ojo: el formato es "Bearer " + token
                val resp = RetrofitClient.api.getCurrentUser("Bearer $t")
                if (resp.isSuccessful) {
                    val user = resp.body()
                    Log.d("API", "Hola ${user?.firstName} - ${user?.email}")
                }
            } catch (e: Exception) {
                Log.e("API", "Error: ${e.message}")
            }
        }
    }
}