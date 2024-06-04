package team.payedin.android

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import team.payedin.android.databinding.ActivityLoginBinding
import team.payedin.android.gahasung.api.ApiProvider
import team.payedin.android.gahasung.request.LoginRequest

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    companion object {
        var token: String = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnLogin.setOnClickListener {
            Log.d("LoginActivity", "Button clicked")
            CoroutineScope(Dispatchers.IO).launch {
                Log.d("asd", binding.editText.text.toString())
                runCatching {
                    ApiProvider.login().login(
                        LoginRequest(
                            accountId = binding.editText.text.toString(),
                            password = binding.editText2.text.toString(),
                        )
                    )
                }.onSuccess {
                    token = it.token
                    withContext(Dispatchers.Main) {
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }
        }
    }
}
