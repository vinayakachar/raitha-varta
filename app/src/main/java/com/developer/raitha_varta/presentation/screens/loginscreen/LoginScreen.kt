package com.developer.raitha_varta.presentation.screens.loginscreen

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.developer.raitha_varta.R
import com.developer.raitha_varta.presentation.navigation.Routes
import com.developer.raitha_varta.ui.theme.ForestGreen
import com.developer.raitha_varta.viewmodel.AuthViewModel

@Composable
fun LoginScreen(navController: NavController,authViewModel: AuthViewModel = viewModel()) {
    val context = LocalContext.current
    val activity = context as? android.app.Activity
    val authViewModel: AuthViewModel = viewModel(
        viewModelStoreOwner = context as ComponentActivity
    )
    val scrollState = rememberScrollState()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF7F9F7)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 80.dp)
                .imePadding()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                modifier = Modifier.size(100.dp),
                color = ForestGreen,
                shape = RoundedCornerShape(24.dp),
                shadowElevation = 4.dp
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_sprout),
                        contentDescription = "Raitha Varta Logo",
                        tint = Color.White,
                        modifier = Modifier.size(60.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stringResource(R.string.app_name).uppercase(),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.tagline),
                fontWeight = FontWeight.SemiBold,
                color = ForestGreen

            )
            Spacer(modifier = Modifier.height(80.dp))

            OtpCard(
                isLoading = authViewModel.isLoading.value,
                onOtpSend = { number: String ->
                    if (activity != null) {
                        authViewModel.sendOtp(number, activity) {
                            // This runs ONLY after Firebase confirms the code is sent
                            navController.navigate(Routes.OtpVerifyScreen(phoneNumber = number))
                        }
                    }
                }
            )

        }
    }
}
