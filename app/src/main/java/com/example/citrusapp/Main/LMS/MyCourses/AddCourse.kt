package com.example.citrusapp.Main.LMS.MyCourses

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.citrusapp.ui.theme.blue_green

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCourseScreen(
    navController: NavHostController
) {
    var courseName by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text("Add a New Course")
                        Button(
                            onClick = {
                                // TODO: handle join logic
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp)
                                .height(40.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = blue_green,
                                contentColor = Color.White // typically white
                            )
                        ) {
                            Text("Join")
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Text + Outlined TextField
            Text("Ask your teacher for the class code, then enter it here.")
            OutlinedTextField(
                value = courseName,
                onValueChange = { courseName = it },
                label = { Text("Class code") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            // Text + Bulleted list
            Text("To sign in with a class code")
            Column(modifier = Modifier.padding(start = 12.dp)) {
                Text("• Use an authorized account", fontSize = 14.sp)
                Text("• Use a class code with 6-8 letters or numbers, and no spaces or symbols", fontSize = 14.sp)
            }

            // Final text section
            Text("If you have trouble joining the class, please contact us.")
        }
    }
}
