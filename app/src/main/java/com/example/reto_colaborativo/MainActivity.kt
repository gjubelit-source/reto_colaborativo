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
    private var token: String? = null
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    companion object {
        const val DATOS = "MisDatos"
        const val KEY_ACCESS_TOKEN = "access_token"
        const val KEY_REFRESH_TOKEN = "refresh_token"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences(DATOS, Context.MODE_PRIVATE)

        token = sharedPreferences.getString(KEY_ACCESS_TOKEN, null)
        if (token != null) {
            obtenerUsuario()
        }

        binding.btnLogin.setOnClickListener {
        val username = binding.edtName.text.toString().trim()
        val password = binding.edtPassword.text.toString().trim()
            if(username.isEmpty()){
                binding.edtName.error = "El usuario es obligatorio"
            }
            if (password.isEmpty()){
                binding.edtPassword.error = "La contraseña es obligatoria"
            }
            if (username.isEmpty() || password.isEmpty()){
                return@setOnClickListener
            }
                hacerLogin(username, password)
        }
    }

    private fun hacerLogin(usuario: String, clave: String) {
        lifecycleScope.launch {
            try {
                val resp = RetrofitClient.api.login(
                    LoginRequest(usuario, clave)
                )
                if (resp.isSuccessful) {
                    val datos = resp.body()
                    token = datos?.accessToken
                    Log.d("API", "Token recibido: $token")

                    sharedPreferences.edit()
                        .putString(KEY_ACCESS_TOKEN, datos?.accessToken)
                        .putString(KEY_REFRESH_TOKEN, datos?.refreshToken)
                        .apply()

                    binding.edtMostrar.text = "Sesión iniciada, cargando datos..."
                    obtenerUsuario()
                } else {
                    Log.e("API", "Login falló: ${resp.code()}")
                    binding.edtMostrar.text = "Usuario o contraseña incorrectos (${resp.code()})"
                }
            } catch (e: Exception) {
                Log.e("API", "Error de red: ${e.message}")
                binding.edtMostrar.text = "Error de red: ${e.message}"
            }
        }
    }

    private fun obtenerUsuario() {
        val t = token ?: return
        lifecycleScope.launch {
            try {
                val resp = RetrofitClient.api.getCurrentUser("Bearer $t")
                if (resp.isSuccessful) {
                    val user = resp.body()
                    Log.d("API", "Hola ${user?.firstName} - ${user?.email}")
                    binding.edtMostrar.text = """
                        Hola, ${user?.firstName} ${user?.lastName}
                        Usuario: ${user?.username}
                        Correo: ${user?.email}
                    """.trimIndent()
                } else {
                    Log.e("API", "No se pudieron traer los datos: ${resp.code()}")
                    binding.edtMostrar.text = "No se pudieron traer los datos (${resp.code()})"
                }
            } catch (e: Exception) {
                Log.e("API", "Error: ${e.message}")
                binding.edtMostrar.text = "Error de red: ${e.message}"
            }
        }
    }
}
