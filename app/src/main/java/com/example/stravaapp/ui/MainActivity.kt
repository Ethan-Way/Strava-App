package com.example.stravaapp.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.lifecycleScope
import com.example.stravaapp.data.viewmodel.StravaViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val viewModel: StravaViewModel by viewModels() // ViewModel injection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            StravaAuthButton { startStravaOAuth(this) }
        }

        // Handle possible OAuth redirect if app was previously opened
        intent?.data?.let { uri ->
            Log.d("MainActivity", "Handling OAuth redirect in onCreate")
            handleOAuthRedirect(uri)
        }
    }

    @Composable
    fun StravaAuthButton(onClick: () -> Unit) {
        Button(onClick = onClick) {
            Text(text = "Authorize with Strava")
        }
    }

    private fun startStravaOAuth(context: Context) {
        // Strava OAuth URL
        val intentUri = Uri.parse("https://www.strava.com/oauth/mobile/authorize")
            .buildUpon()
            .appendQueryParameter("client_id", "136979")
            .appendQueryParameter("redirect_uri", "myapp://callback")
            .appendQueryParameter("response_type", "code")
            .appendQueryParameter("approval_prompt", "auto")
            .appendQueryParameter("scope", "activity:write,read")
            .build()

        val intent = Intent(Intent.ACTION_VIEW, intentUri)
        context.startActivity(intent)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Log.d("MainActivity", "onNewIntent called with data: ${intent.data}")

        intent.data?.let { uri ->
            handleOAuthRedirect(uri)
        }
    }

    private fun handleOAuthRedirect(uri: Uri) {
        val code = uri.getQueryParameter("code")
        if (code != null) {
            Log.d("MainActivity", "OAuth code received: $code")
            lifecycleScope.launch { // Launch coroutine for API call
                viewModel.fetchAccessToken(code)
            }
        } else {
            Log.e("MainActivity", "No auth code found in the redirect URI")
            viewModel.onError("Auth code not found")
        }
    }
}