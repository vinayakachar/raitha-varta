package com.developer.raitha_varta.presentation.screens.otpscreen

import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.developer.raitha_varta.R
import com.developer.raitha_varta.presentation.navigation.Routes
import com.developer.raitha_varta.ui.theme.ForestGreen
import com.developer.raitha_varta.viewmodel.AuthViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider

@Composable
fun OtpVerifyCard(navController: NavController, phoneNumber: String, authViewModel: AuthViewModel) {
    var otpCode by remember { mutableStateOf("") }
    val context = LocalContext.current
    val authViewModel: AuthViewModel = viewModel(
        viewModelStoreOwner = context as ComponentActivity
    )

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(48.dp),
        color = Color.White,
        shadowElevation = 8.dp
    ) {
        Column(
            modifier = Modifier.padding(vertical = 40.dp, horizontal = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.otp_header),
                style = MaterialTheme.typography.headlineMedium,
                color = Color.DarkGray,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "${stringResource(R.string.sent_to_label)} $phoneNumber ",
                color = Color(0xFFA0AEC0),
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                modifier = Modifier.align(Alignment.Start),
                text = stringResource(R.string.enter_otp_label),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(32.dp))

            OtpInputField(
                onOtpComplete = { otp ->
                    otpCode = otp
                }
            )
            Spacer(modifier = Modifier.height(42.dp))

            Button(
                onClick = {
                    val currentVerificationId = authViewModel.verificationId.value

                    if (currentVerificationId.isEmpty()) {
                        Toast.makeText(
                            context,
                            "Session expired. Please go back.",
                            Toast.LENGTH_LONG
                        ).show()
                        return@Button
                    }

                    if (otpCode.length == 6) {

                        try {
                            val credential = PhoneAuthProvider.getCredential(
                                currentVerificationId,
                                otpCode
                            )

                            FirebaseAuth.getInstance()
                                .signInWithCredential(credential)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        navController.navigate(Routes.HomeScreen) {
                                            popUpTo(Routes.LoginScreen) {
                                                inclusive = true
                                            }
                                        }
                                    } else {
                                        val error = task.exception?.localizedMessage ?: "Invalid OTP"
                                        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                                    }
                                }
                        } catch (e: Exception) {
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                enabled = otpCode.length == 6,
                colors = ButtonDefaults.buttonColors(containerColor = ForestGreen),
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.btn_verify_otp),
                        fontSize = 20.sp
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Icon(Icons.Default.ArrowForward, contentDescription = null)
                }
            }
            Spacer(modifier = Modifier.height(24.dp))

            TextButton(onClick = {
                navController.popBackStack()
            }) {
                Text(
                    text = stringResource(R.string.change_number_link),
                    color = Color.Gray,
                    style = MaterialTheme.typography.labelLarge
                )
            }

        }
    }
}
