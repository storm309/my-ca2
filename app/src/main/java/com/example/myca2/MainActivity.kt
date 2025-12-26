<<<<<<< HEAD
package com.example.myca2
=======
package com.example.workshop
>>>>>>> e8154a014e7fa0a40121e2183c15d01087aad6b2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
<<<<<<< HEAD
import androidx.navigation.NavHostController
=======
>>>>>>> e8154a014e7fa0a40121e2183c15d01087aad6b2
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController, startDestination = "form") {
                composable("form") {
                    FormScreen(navController)
                }
                composable(
                    "confirm/{title}/{id}/{sessions}",
                    arguments = listOf(
                        navArgument("title") { type = NavType.StringType },
                        navArgument("id") { type = NavType.StringType },
                        navArgument("sessions") { type = NavType.IntType }
                    )
                ) {
                    ConfirmScreen(
                        it.arguments?.getString("title") ?: "",
                        it.arguments?.getString("id") ?: "",
                        it.arguments?.getInt("sessions") ?: 0
                    )
                }
            }
        }
    }
}

@Composable
fun FormScreen(navController: NavHostController) {
    var title by remember { mutableStateOf("") }
    var wid by remember { mutableStateOf("") }
    var sessions by remember { mutableStateOf("") }
    var checked by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(value = title, onValueChange = { title = it }, label = { Text("Workshop Title") })
        OutlinedTextField(value = wid, onValueChange = { wid = it }, label = { Text("Workshop ID") })
        OutlinedTextField(value = sessions, onValueChange = { sessions = it }, label = { Text("Number of Sessions") })
        Row {
            Checkbox(checked = checked, onCheckedChange = { checked = it })
            Spacer(modifier = Modifier.width(8.dp))
            Text("Confirm Details")
        }
        Button(onClick = {
            val s = sessions.toIntOrNull() ?: 0
            if (s > 0 && checked) {
                navController.navigate("confirm/$title/$wid/$s")
            } else {
                Toast.makeText(context, "Please enter valid details and confirm the registration.", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text("Submit")
        }
    }
}

@Composable
fun ConfirmScreen(title: String, wid: String, sessions: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Workshop Title: $title")
        Text("Workshop ID: $wid")
        Text("Number of Sessions: $sessions")
    }
}